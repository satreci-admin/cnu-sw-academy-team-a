package com.example.simplerpa.service;

import com.example.simplerpa.model.Work;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class DefaultTestJobA extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(TestJobA.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        int robotId = jobDataMap.getIntValue("robotId");
        String contents = jobDataMap.getString("contents");
        log.info("10초마다 Job 실행");
        log.info(robotId + " " + contents);

        /*
            ssh 부분 구현 필요
             1) .sh 파일로 변환
             2) 로봇 원격 제어
         */

    }

}

