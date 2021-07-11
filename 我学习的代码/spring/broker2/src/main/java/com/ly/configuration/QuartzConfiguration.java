package com.ly.configuration;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ly.job.FileHandleQuartz;

//https://jingyan.baidu.com/article/c275f6ba60ab82e33d7567db.html
//https://www.cnblogs.com/gdhzdbh/p/10643856.html
/*
任务(Job):实际要触发的事件
触发器(Trigger):用于设定时间规则
调度器(Scheduler):组合任务与触发器*/


@Configuration
public class QuartzConfiguration {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(FileHandleQuartz.class)
                .withIdentity("fileHandleQuartzJobDetail") //自己取名
                .storeDurably().build();
    }
    
    
    @Bean
    public Trigger trigger() {
       // SimpleScheduleBuilder schedBuilder=SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever(); //时间自己定，根据方法进行修改时间
    	
    	CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/1 * * * * ?"); //替换上面的第一行就好
    	
        return TriggerBuilder.newTrigger().forJob( jobDetail()).withIdentity("fileHandleQuartzJobTriger") //自己取名
                .withSchedule(cronScheduleBuilder).build();
    }
}