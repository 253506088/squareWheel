package com.example.mtquartz.javaTimeTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Timer;

/**
 * java的Timer演示
 */
public class JavaTimerDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        //启动定时任务
        Timer timer = new Timer();
        //添加定时任务
        Date date = new Date();
        timer.schedule(new MyTimeTask("1.5秒一次的任务"),date,1500);
        timer.schedule(new MyTimeTask("3秒一次的任务"),date,3000);
    }
}
