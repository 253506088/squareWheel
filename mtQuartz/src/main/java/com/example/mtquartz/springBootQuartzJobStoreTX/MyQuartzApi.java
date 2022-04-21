package com.example.mtquartz.springBootQuartzJobStoreTX;

import com.example.mtquartz.springBootQuartzRam.MyJob;
import org.quartz.JobDataMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/quartz")
@RestController
public class MyQuartzApi {

    @GetMapping("/add")
    public void add(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("taskName","api启动的任务");
        jobDataMap.put("count",0);
        QuartzUtils.addJob("JobTest",
                "jobGroup",
                "triggerTest",
                "triggerGroup",
                MyJob.class,
                "*/3 * * * * ?",
                jobDataMap);
    }

    /**
     * 启动所有定时任务
     */
    @GetMapping("/shutdownJobs")
    public void shutdownJobs(){
        QuartzUtils.shutdownJobs();
    }

    /**
     * 关闭所有定时任务
     */
    @GetMapping("/startJobs")
    public void startJobs(){
        QuartzUtils.startJobs();
    }
}
