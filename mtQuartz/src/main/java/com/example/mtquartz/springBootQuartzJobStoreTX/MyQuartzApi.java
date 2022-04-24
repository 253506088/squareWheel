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
    public void add() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("taskName", "api启动的任务");
        jobDataMap.put("count", 0);
        QuartzUtils.addJob("JobTest2",
                "jobGroup",
                "triggerTest2",
                "triggerGroup",
                MyJob.class,
                "*/3 * * * * ?",
                jobDataMap);
    }

    /**
     * 恢复任务
     */
    @GetMapping("/resumeJob")
    public void resumeJob() {
        QuartzUtils.resumeJob("JobTest2", "jobGroup");
    }

    /**
     * 暂停任务
     */
    @GetMapping("/pauseJob")
    public void pauseJob() {
        QuartzUtils.pauseJob("JobTest2", "jobGroup");
    }

    /**
     * 关闭所有定时任务
     */
    @GetMapping("/shutdownJobs")
    public void shutdownJobs() {
        QuartzUtils.shutdownJobs();
    }

    /**
     * 启动所有定时任务
     */
    @GetMapping("/startJobs")
    public void startJobs() {
        QuartzUtils.startJobs();
    }
}
