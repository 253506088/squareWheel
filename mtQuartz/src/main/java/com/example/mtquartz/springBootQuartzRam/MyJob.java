package com.example.mtquartz.springBootQuartzRam;

import lombok.SneakyThrows;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Spring Boot的写法，继承QuartzJobBean类
 */
@PersistJobDataAfterExecution
public class MyJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int count = jobDataMap.getInt("count");
        count++;
        logger.error("任务【{}】第【{}】次执行，线程id【{}】",
                jobDataMap.get("taskName"),
                count,
                Thread.currentThread().getId()
        );
        jobDataMap.put("count",count);
        Thread.sleep(1000);
    }
}