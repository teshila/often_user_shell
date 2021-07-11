package com.ly.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FileHandleQuartz extends QuartzJobBean{
private Logger logger = LoggerFactory.getLogger(FileHandleQuartz.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务执行啦！");
    }
}