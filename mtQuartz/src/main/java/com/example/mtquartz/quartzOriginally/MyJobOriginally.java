package com.example.mtquartz.quartzOriginally;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 原味版本的Quartz的job实现类，并非是spring boot版本的
 */
public class MyJobOriginally implements Job {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static int count = 0;

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
        Thread.sleep(2000);
        count++;
        if (count == 3) {
            Scheduler scheduler = jobExecutionContext.getScheduler();
            scheduler.deleteJob(jobExecutionContext.getJobDetail().getKey());
//            scheduler.shutdown();
            logger.error("满{}次，任务结束", count);
        }
    }
}
