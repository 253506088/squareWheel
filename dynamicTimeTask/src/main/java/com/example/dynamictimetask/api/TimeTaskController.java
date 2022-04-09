package com.example.dynamictimetask.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dynamictimetask.config.TimeTaskConfig;
import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.example.dynamictimetask.database.service.ISpringScheduledCronService;
import com.example.dynamictimetask.timeTask.base.BaseTimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 动态定时任务控制器
 *
 * @author 黑白大彩电
 * @since 2020-07-20
 */
@CrossOrigin
@RestController
@RequestMapping("/timeTask")
public class TimeTaskController {

    /**
     * 日志实体
     */
    private final Logger logger = LoggerFactory.getLogger(TimeTaskController.class);
    @Autowired
    private ISpringScheduledCronService springScheduledCronService;
    @Autowired
    private ApplicationContext applicationContext;
    private ScheduledExecutorService threadPool;
    private Map<Long, BaseTimeTask> baseTimeTaskCache = new HashMap<>();
    /**
     * 可重入读写锁
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 获取全部动态定时任务列表
     *
     * @return
     */
    @GetMapping(value = "/allList", produces = "application/json; charset=utf-8")
    public Object allList() {
        List<SpringScheduledCron> allList = springScheduledCronService.getBaseMapper().selectList(new QueryWrapper<>());
        return allList;
    }

    /**
     * 根据id更新定时任务
     *
     * @param springScheduledCron
     * @return
     */
    @PostMapping(value = "/updateById", produces = "application/json; charset=utf-8")
    public Object updateById(SpringScheduledCron springScheduledCron) {
        springScheduledCronService.updateById(springScheduledCron);
        return springScheduledCron;
    }

    /**
     * 根据id立即执行某一个定时任务，不过这个依然会受到状态限制，如果状态为禁止执行则无法执行
     *
     * @return
     */
    @GetMapping(value = "/execute")
    public Object execute(Long id) {
        SpringScheduledCron springScheduledCron = springScheduledCronService.getBaseMapper().selectById(id);
        if (springScheduledCron != null) {
            BaseTimeTask baseTimeTask = this.getBaseTimeTask(springScheduledCron);
            baseTimeTask.setSpringScheduledCron(springScheduledCron);
            this.getScheduledExecutorService().execute(baseTimeTask);
            return springScheduledCron.getTaskName() + "已开始执行任务，如果状态为禁止执行则无法运行其中的业务逻辑";
        }
        return "未找到任务";
    }


    /**
     * 杀死bean
     * @return
     */
    @GetMapping(value = "/unRegistryBean", produces = "application/json; charset=utf-8")
    public Object unRegistryBean() {
        String beanName = "timeTaskConfig";
        this.unRegistryBean(beanName);
        return null;
    }

    /**
     * 创建bean
     * @return
     */
    @GetMapping(value = "/registryBean", produces = "application/json; charset=utf-8")
    public Object registryBean() {
        new Thread(() -> {
            String beanName = "timeTaskConfig";
            this.registryBean(beanName);
            TimeTaskConfig timeTaskConfig = (TimeTaskConfig)applicationContext.getBean(beanName);
            timeTaskConfig.configureTasks(new ScheduledTaskRegistrar());
            Object taskExecutor = applicationContext.getBean("taskExecutor");
        }).start();
        return null;
    }

    /**
     * 获取线程池
     *
     * @return
     */
    private ScheduledExecutorService getScheduledExecutorService() {
        if (this.threadPool == null) {
            synchronized (this) {
                if (this.threadPool == null) {
                    this.threadPool = Executors.newScheduledThreadPool(10);
                }
            }
        }
        return this.threadPool;
    }

    /**
     * 因为BaseTimeTask的子类被我设计成原型模式，每一次获取bean都会新建一个，为了减少内存开支，我在这里对手动执行的bean做一个缓存
     *
     * @param springScheduledCron
     * @return
     */
    private BaseTimeTask getBaseTimeTask(SpringScheduledCron springScheduledCron) {
        BaseTimeTask baseTimeTask = null;
        if (this.baseTimeTaskCache.get(springScheduledCron.getId()) == null) {
            synchronized (this) {
                if (this.baseTimeTaskCache.get(springScheduledCron.getId()) == null) {
                    Class<?> clazz;
                    Object task;
                    try {
                        //根据类名称获取具体的类
                        clazz = Class.forName(springScheduledCron.getCronKey());
                        //根据类获取bean
                        task = applicationContext.getBean(clazz);
                    } catch (ClassNotFoundException e) {
                        throw new IllegalArgumentException("spring_scheduled_cron表数据" + springScheduledCron.getCronKey() + "有误", e);
                    } catch (BeansException e) {
                        throw new IllegalArgumentException(springScheduledCron.getCronKey() + "未纳入到spring管理", e);
                    }
                    //断言校验
                    Assert.isAssignable(BaseTimeTask.class, task.getClass(), "定时任务类必须实现ScheduledOfTask接口");
                    baseTimeTask = (BaseTimeTask) task;
                    this.baseTimeTaskCache.put(springScheduledCron.getId(), baseTimeTask);
                } else {
                    baseTimeTask = this.baseTimeTaskCache.get(springScheduledCron.getId());
                }
            }
        } else {
            baseTimeTask = this.baseTimeTaskCache.get(springScheduledCron.getId());
        }
        return baseTimeTask;
    }

    /**
     * 根据bean名称注册bean
     * @param beanName
     */
    private void registryBean(String beanName) {
        logger.info("开始注册[{}]。", beanName);
        lock.writeLock().lock();
        try {
            DefaultListableBeanFactory beanFactory = getBeanFactory();
            //注册DataSourceBean
            GenericBeanDefinition dataSourceBeanDefinition = this.buildBeanDefinition(beanName);
            beanFactory.registerBeanDefinition(beanName, dataSourceBeanDefinition);
            logger.info("注册[{}]成功！", beanName);
        } finally {
            lock.writeLock().unlock();
        }
        ScheduledTaskRegistrar scheduledTaskRegistrar = new ScheduledTaskRegistrar();
    }

    /**
     * 根据bean名称销毁bean
     *
     * @param beanName
     */
    private void unRegistryBean(String beanName) {
        logger.info("开始注销bean[{}]。", beanName);
        lock.writeLock().lock();
        try {
            DefaultListableBeanFactory beanFactory = getBeanFactory();
            if (beanFactory.containsBeanDefinition(beanName)) {
                beanFactory.destroySingleton(beanName);
                beanFactory.removeBeanDefinition(beanName);
                logger.info("注销[{}]成功！", beanName);
            } else {
                logger.info("不存在[{}]，不需要注销！", beanName);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private GenericBeanDefinition buildBeanDefinition(String beanName) {
        GenericBeanDefinition definition = null;
        switch (beanName) {
            case "timeTaskConfig":
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(TimeTaskConfig.class);
                definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                definition.setBeanClass(TimeTaskConfig.class);
                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
               /*  依赖其他bean,这个要先注册
               definition.getPropertyValues().add("dataSource", this.applicationContext
                        .getBean(otherBeanName), OtherBeanName.class));*/

//                // 属性赋值
//                definition.getPropertyValues().add("id", "1")
//                        .add("name", "张三")
//                        .add("password", "123")
//                        .add("mail", "zhangsan@qq.com");


                break;
            default:
                throw new RuntimeException("创建bean异常");
        }
        return definition;
    }

    /**
     * 获取SpringBean注册器
     *
     * @return BeanDefinitionRegistry
     */
    private DefaultListableBeanFactory getBeanFactory() {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
    }
}
