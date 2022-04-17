package com.example.mtquartz.quartzOriginally;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 原味版本的Quartz的运行案例
 */
public class Test {
    public static void main(String[] args) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("myHome", "山东德州");
        //第一步，创建JobDetail，用于指定具体要执行什么
        JobDetail jobDetail = JobBuilder.newJob(MyJobOriginally.class).
                withIdentity("任务名称", "任务所属组").
                setJobData(jobDataMap).
                build();

        //第二步，创建Trigger，用于指定在何时执行
        Trigger trigger = null;
        if (false) {
            //简单明了的指定时间
            trigger = TriggerBuilder.newTrigger().
                    withIdentity("触发器名称", "触发器所属组").
                    usingJobData("myName", "大彩电").
                    usingJobData("myHome", "中国山东德州").
                    startNow().//立马就启动的任务
                    withSchedule(
                    //之后每1秒运行一次，运行到永远
                    SimpleScheduleBuilder.simpleSchedule().
                            withIntervalInMilliseconds(1000).
                            repeatForever()
            ).build();
        } else {
            //使用cron表达式
            trigger = TriggerBuilder.newTrigger().
                    withIdentity("触发器名称", "触发器所属组").
                    usingJobData("myName", "大彩电").
                    usingJobData("myHome", "地球中国山东德州").
                    startNow().//立马就启动的任务
                    withSchedule(
                    //之后五秒一次
                    CronScheduleBuilder.cronSchedule("*/5 * * * * ?")
            ).build();
        }

        //第三步，创建Scheduler调度器，这里获取一个默认的，用于调度定时任务
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //第四步将JobDetail、Trigger添加到调度器中，并且开始执行
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        if (false) {
            //暂停触发器的计时
            scheduler.pauseTrigger(trigger.getKey());
            //移除触发器中的任务
            scheduler.unscheduleJob(trigger.getKey());
            //删除任务
            scheduler.deleteJob(jobDetail.getKey());
        }
    }
}