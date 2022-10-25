package com.example.simplerpa.service;

import org.quartz.JobExecutionContext;

public interface TestJobA {
    void executeInternal(JobExecutionContext context);

}
