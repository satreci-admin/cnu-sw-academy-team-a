package com.example.simplerpa.configuration;

import com.example.simplerpa.repository.WorkRepository;
import com.example.simplerpa.service.Jsch.SSHService;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Configuration
public class JobConfiguration {

    private final Scheduler scheduler;
    private final WorkRepository workRepository;

    public JobConfiguration(Scheduler scheduler, WorkRepository workRepository) {
        this.scheduler = scheduler;
        this.workRepository = workRepository;
    }

    @PostConstruct
    public void run() {
        int statementId = 1; // workId가 1인 경우만 테스트. 여러 개의 경우 추후 배치 사용.
        int robotId = workRepository.findById(statementId).get().getRobotId(); // robotId 추후 추가. 테이블 수정 중.
        var contents = workRepository.findById(statementId).get().getContents();
        var schedulerCron = workRepository.findById(statementId).get().getSchedulerCron();
        JobDetail detail = runJobDetail(SSHService.class, new HashMap<>());
        JobDataMap jobDataMap = detail.getJobDataMap();
        jobDataMap.put("robotId", robotId);
        jobDataMap.put("statementId", statementId);
        jobDataMap.put("contents", contents);
        try {
            scheduler.scheduleJob(detail, runJobTrigger(schedulerCron));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public Trigger runJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "group1")
                .startNow()
                .build();

        // 크론 스케줄 사용
//        return TriggerBuilder.newTrigger()
//                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail runJobDetail(Class job, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);
        // 스케줄 생성
        return newJob(job).usingJobData(jobDataMap).build();
    }
}
