package com.example.dynamictimetask.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.example.dynamictimetask.database.service.ISpringScheduledCronService;
import com.example.dynamictimetask.timeTask.base.BaseTimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 动态定时任务配置，这里并非是祖传配置，需要根据你的需求进行修改的
 */
@Configuration("timeTaskConfig")
public class TimeTaskConfig implements SchedulingConfigurer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ISpringScheduledCronService springScheduledCronService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //查询库中所有的定时任务
        List<SpringScheduledCron> springScheduledCrons = springScheduledCronService.getBaseMapper().selectList(new QueryWrapper<>());
        for (SpringScheduledCron springScheduledCron : springScheduledCrons) {
            //获取定时任务的处理类和这个类的bean
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
            BaseTimeTask baseTimeTask = (BaseTimeTask) task;
            baseTimeTask.setSpringScheduledCron(springScheduledCron);

            /**
             * 添加定时任务
             * addTriggerTask这里会不停的循环查询最新的定时周日，从而做到数据库里corn表达式变化后，程序也跟着刷新corn表达式
             * 因此在启动后如果新增了定时任务，是需要重启java程序的，因为这里只能不停的查询程序刚启动时库里的定时任务
             */
            taskRegistrar.addTriggerTask((baseTimeTask),
                    triggerContext -> {
                        String cronExpression = null;
                        try {
                            //查询最新的corn表达式
                            SpringScheduledCron newCron = springScheduledCronService.getBaseMapper().selectById(springScheduledCron.getId());
                            if (!springScheduledCron.getCronExpression().equals(newCron.getCronExpression())) {
                                //变动之后每一次执行都会执行这里，因为lambda表达式中的值都会被编译为常量，所以无法修改【springScheduledCron】的值
                                logger.info("定时任务【{}】的corn表达式发生变动，变动前【{}】，变动后【{}】", newCron.getTaskName(), springScheduledCron.getCronExpression(), newCron.getCronExpression());
                            }
                            if (!springScheduledCron.getStatus().equals(newCron.getStatus())) {
                                //变动之后每一次执行都会执行这里，因为lambda表达式中的值都会被编译为常量，所以无法修改【springScheduledCron】的值
                                logger.info("定时任务【{}】的运行状态发生变化，变动前【{}】，变动后【{}】", newCron.getTaskName(), springScheduledCron.getStatus(), newCron.getStatus());
                            }
                            baseTimeTask.setSpringScheduledCron(newCron);
                            cronExpression = newCron.getCronExpression();
                        } catch (Exception e) {
                            //如果查询新corn表达式出现异常，则取程序刚运行时的版本
                            e.printStackTrace();
                            cronExpression = springScheduledCron.getCronExpression();
                        }
                        return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                    }
            );
        }
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}