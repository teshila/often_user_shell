package com.ly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
//@Configuration
public class QuartzAnnotationTest {
    private Logger logger = LoggerFactory.getLogger(QuartzAnnotationTest.class);
    @Scheduled(cron = "*/5 * * * * ?")
    public void testAnnotation(){
       logger.info("注解的方式测试啦！");
    }
}