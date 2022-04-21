package com.example.mtquartz.springBootQuartzRam;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot整合Quartz的关键就在于这个配置文件
 * 只需要创建JobDetail和Trigger，无需Scheduler
 */
@Configuration
public class MyQuartzConfig {
    /**
     * 注册一个JobDetail的bean
     *
     * @return
     */
    @Bean
    public JobDetail myJob001() {
        return JobBuilder.newJob(MyJob.class)//MyJob我们的业务类
                .withIdentity("MyJob-001")//可以给该JobDetail起一个id
                .usingJobData("taskName", "考公吧-001")
                .usingJobData("count", 0)
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    /**
     * 注册一个Trigger的bean
     * @return
     */
    @Bean
    public Trigger myJob001Trigger() {
        //5秒一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(this.myJob001())//关联上述的JobDetail
                .withIdentity("myJob001Trigger")//给Trigger起个id
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
