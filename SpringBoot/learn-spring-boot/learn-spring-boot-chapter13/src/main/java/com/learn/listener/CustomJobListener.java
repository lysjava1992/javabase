package com.learn.listener;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * job执行日志
 */
@Slf4j
public class CustomJobListener implements JobListener {
    @Override
    public String getName() {
        return "customJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
      log.debug("{}:{}:开始执行",getName(),context.getJobDetail().getJobClass());

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.debug("{}:{}:被否决",getName(),context.getJobDetail().getJobClass());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.debug("{}:{}:执行结束",getName(),context.getJobDetail().getJobClass());
    }
}
