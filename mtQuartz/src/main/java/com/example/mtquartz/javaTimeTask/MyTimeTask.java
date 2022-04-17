package com.example.mtquartz.javaTimeTask;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 继承TimerTask类，编写我的定时任务要执行的内容
 */
public class MyTimeTask extends TimerTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public String taskName;

    public MyTimeTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 任务内容
     */
    @SneakyThrows
    @Override
    public void run() {
        logger.info("当前执行任务名称：{}，线程id：{}", this.taskName, Thread.currentThread().getId());
        Thread.sleep(3000);
    }
}
