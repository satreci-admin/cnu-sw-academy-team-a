package com.example.simplerpa.configuration;

import com.example.simplerpa.service.DefaultTestJobA;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Configuration
public class JobConfiguration {

    private final Scheduler scheduler;

    public JobConfiguration(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void run(){
        JobDetail detail = runJobDetail(DefaultTestJobA.class, new HashMap<>());
        String testCron="0/10 * * * * ?"; //여기에 데이터베이스를 넣어야함
        try{
            scheduler.scheduleJob(detail, runJobTrigger(testCron));
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public Trigger runJobTrigger(String scheduleExp){
        // 크론 스케줄 사용
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }
    public JobDetail runJobDetail(Class job, Map params){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);
        // 스케줄 생성
        return newJob(job).usingJobData(jobDataMap).build();
    }
}
