package com.example.simplerpa.service.Jsch;

import com.example.simplerpa.repository.RobotRepository;
import com.example.simplerpa.service.Jsch.SSH_Controller;
import com.example.simplerpa.service.TestJobA;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class DefaultTestJobA extends QuartzJobBean {

    private final RobotRepository repository;

    private static final Logger log = LoggerFactory.getLogger(TestJobA.class);

    public DefaultTestJobA(RobotRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("10초마다 Job 실행");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        int robotId = jobDataMap.getIntValue("robotId");
        String contents = jobDataMap.getString("contents");
        log.info("10초마다 Job 실행");
        log.info(robotId + " " + contents);

        SSH_Controller server = new SSH_Controller(repository, robotId);
        server.runSSH();

        /*
            ssh 부분 구현 필요
             1) .sh 파일로 변환
             2) 로봇 원격 제어
         */

    }

}