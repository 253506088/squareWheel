package com.example.dynamictimetask.timeTask;

import com.example.dynamictimetask.database.entity.SpringScheduledCron;
import com.example.dynamictimetask.timeTask.base.BaseTimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
//原型模式，每一次获取bean都是新的原型
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class MyTimeTask1 extends BaseTimeTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    @Override
    public void execute() {
        SpringScheduledCron springScheduledCron = this.getSpringScheduledCron();
        logger.error("任务名称：{}，corn：{}，thread id:{}，第{}次执行",
                springScheduledCron.getTaskName(),
                springScheduledCron.getCronExpression(),
                Thread.currentThread().getId(),
                ++i
        );
    }

}