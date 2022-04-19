package com.example.mtquartz.quartzOriginally;

import lombok.SneakyThrows;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 原味版本的Quartz的job实现类，并非是spring boot版本的
 */

/**
* @DisallowConcurrentExecution，意味着：quartz每一次执行都是同一个的Job实现类对象
* 在默认情况下，quartz每一次执行都会创建一个新的Job实现类对象
*/
@DisallowConcurrentExecution

/**
 * @PersistJobDataAfterExecution，意味着在Job实现类对象中修改JobDetail的JobDataMap里的值，可以在下一次任务里也看到修改后的值。
 * 在默认情况下，在Job实现类对象中修改JobDataMap里的值，可以在下一次任务里是看不到的，因为每一次都会按照初始化时的值传入。
 * 需要注意的是，@PersistJobDataAfterExecution实现的共享JobDetail的JobDataMap是线程不安全的，当Job实例有多个同时执行，就会不安全。
 * Trigger的JobDataMap不会被这个注解共享
 */
@PersistJobDataAfterExecution
public class MyJobOriginally implements Job {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //获取在【创建JobDetail、创建Trigger】时传入的【JobDataMap】，这里相同的key也会被覆盖
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        if (false) {
            //只获取【创建JobDetail】时传入的【JobDataMap】
            jobExecutionContext.getJobDetail().getJobDataMap();
            //只获取【创建Trigger】时传入的【JobDataMap】
            jobExecutionContext.getTrigger().getJobDataMap();
        }
        logger.error(jobExecutionContext.getJobDetail().getKey().getName());
        logger.error(jobExecutionContext.getTrigger().getKey().getName());
        logger.error(
                "任务执行了，我的家乡是【{}】，我的名字是【{}】，线程id:【{}】",
                mergedJobDataMap.get("myName"),
                mergedJobDataMap.get("myHome"),
                Thread.currentThread().getId()
        );
        //对于quartz来说，哪怕【任务的执行时间】大于【任务执行间隔】也是无所谓的
        Thread.sleep(6000);
        int count = jobExecutionContext.getJobDetail().getJobDataMap().getInt("count");
        count++;
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count);

        if (count == 3) {
            //根据key删除掉已经执行过三遍的任务
            Scheduler scheduler = jobExecutionContext.getScheduler();
            scheduler.deleteJob(jobExecutionContext.getJobDetail().getKey());
            logger.error("满{}次，任务结束，新任务开始", count);

            //第一步，创建JobDetail，用于指定具体要执行什么
            JobDetail jobDetail = JobBuilder.newJob(MyJobOriginally.class).
                    usingJobData("count",0).
                    withIdentity("开始亏钱", "股市").
                    build();
            //第二步，创建Trigger，用于指定在何时执行
            Trigger trigger = TriggerBuilder.newTrigger().
                    withIdentity("9.30", "开盘").
                    usingJobData("myName", "韭菜").
                    usingJobData("myHome", "A股").
                    startNow().//立马就启动的任务
                    withSchedule(
                    //之后3秒一次
                    CronScheduleBuilder.cronSchedule("*/3 * * * * ?")
            ).build();
            //第三步，Scheduler调度器，用于调度定时任务
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
