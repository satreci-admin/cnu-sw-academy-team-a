package com.example.simplerpa.service.Jsch;

import com.example.simplerpa.repository.RobotRepository;
import com.example.simplerpa.service.TestJobA;
import com.example.simplerpa.service.ToShFile;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class SSHService extends QuartzJobBean {

    private final RobotRepository repository;

    private static final Logger log = LoggerFactory.getLogger(TestJobA.class);

    public SSHService(RobotRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("10초마다 Job 실행");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        int robotId = jobDataMap.getIntValue("robotId");
        int statementId = jobDataMap.getIntValue("statementId");
        String contents = jobDataMap.getString("contents");
        log.info("10초마다 Job 실행");
        log.info(robotId + " " + contents);

        // 쉘 스크립트로 변환
        ToShFile toShFile = new ToShFile();
        toShFile.toFile(statementId, contents);

        // 로봇 원격 제어.
        SSH_Controller server = new SSH_Controller(repository, robotId, statementId);
        server.runSSH();

    }

}