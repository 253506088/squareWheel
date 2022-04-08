package com.example.dynamictimetask.timeTask.base;



/**
 * 动态定时任务执行器接口
 */
public interface TimeTask extends Runnable {
    /**
     * 定时任务方法
     */
    void execute();

    /**
     * 实现控制定时任务启用或禁用的功能
     */
    @Override
    default void run() {
    }

}