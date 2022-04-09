package com.example.dynamictimetask.timeTask.base;

import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.example.dynamictimetask.database.service.ISpringScheduledCronService;
import com.example.dynamictimetask.tool.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态定时任务基础类
 */
public class BaseTimeTask implements TimeTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private SpringScheduledCron springScheduledCron;

    @Override
    public void execute() {
        //在子类里实现
    }

    /**
     * 实现控制定时任务启用或禁用的功能
     */
    @Override
    public void run() {
        if ("2".equals(this.getSpringScheduledCron().getStatus())) {
            // 任务是禁用状态
            logger.warn("任务【{}】处于禁用状态，无法执行", springScheduledCron.getTaskName());
            return;
        }
        execute();
    }

    public SpringScheduledCron getSpringScheduledCron() {
        //每次获取都更新一下
        if (this.springScheduledCron != null) {
            ISpringScheduledCronService springScheduledCronService = SpringContextHolder.getBean(ISpringScheduledCronService.class);
            SpringScheduledCron newCron = springScheduledCronService.getBaseMapper().selectById(this.springScheduledCron.getId());
            this.setSpringScheduledCron(newCron);
        }
        return springScheduledCron;
    }

    public void setSpringScheduledCron(SpringScheduledCron springScheduledCron) {
        this.springScheduledCron = springScheduledCron;
    }
}