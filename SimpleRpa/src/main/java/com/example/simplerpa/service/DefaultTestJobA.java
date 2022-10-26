package com.example.simplerpa.service;

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
        log.info("10초마다 Job 실행");

    }

}