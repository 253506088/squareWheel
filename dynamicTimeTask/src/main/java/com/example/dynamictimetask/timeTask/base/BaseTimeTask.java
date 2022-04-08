package com.example.dynamictimetask.timeTask.base;

import com.example.dynamictimetask.database.entity.SpringScheduledCron;
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
        if ("2".equals(springScheduledCron.getStatus())) {
            // 任务是禁用状态
            logger.warn("任务【{}】处于禁用状态，无法执行", springScheduledCron.getTaskName());
            return;
        }
        execute();
    }

    public SpringScheduledCron getSpringScheduledCron() {
        return springScheduledCron;
    }

    public void setSpringScheduledCron(SpringScheduledCron springScheduledCron) {
        this.springScheduledCron = springScheduledCron;
    }
}