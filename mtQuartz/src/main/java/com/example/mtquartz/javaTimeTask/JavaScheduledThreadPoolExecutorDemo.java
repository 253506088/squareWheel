package com.example.mtquartz.javaTimeTask;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java自带的ScheduledThreadPoolExecutor案例
 */
public class JavaScheduledThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //创建定时任务线程池，线程数10
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        /**
         * 添加任务
         * initialDelay-》首次执行需要等待多久
         * period-》之后每次执行需要等待多久
         * unit-》时间单位，这里选择毫秒
         */
        threadPool.scheduleAtFixedRate(new TimeTask("3秒一次的任务"), 0, 3000, TimeUnit.MILLISECONDS);
        threadPool.scheduleAtFixedRate(new TimeTask("1.5秒一次的任务"), 0, 1500, TimeUnit.MILLISECONDS);
    }
}

/**
 * 具体执行的任务
 */
class TimeTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String taskName;

    public TimeTask(String taskName) {
        this.taskName = taskName;
    }

    @SneakyThrows
    @Override
    public void run() {
        logger.info("当前执行任务名称：{}，线程id：{}", this.taskName, Thread.currentThread().getId());
        //当任务的 休眠时间（这里模拟的是执行时间）大于任务循环执行的【预定时间间隔】，就会出现【实际执行时间间隔】大于【预定时间间隔】
//        Thread.sleep(3100);
        Thread.sleep(2000);
    }
}