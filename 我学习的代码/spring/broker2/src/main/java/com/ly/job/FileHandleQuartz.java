package com.ly.job;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        
        File fileDemo1=new File("c:\\hello\\hello\\demo.txt");
        //System.out.println(fileDemo1.getParentFile());
        if(!(fileDemo1.getParentFile().exists())){
            fileDemo1.getParentFile().mkdirs();
        }
        if(fileDemo1.exists()){       //如果存在这个文件就删除，否则就创建
            fileDemo1.delete();        
        }else{
        try {
			System.out.println(fileDemo1.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
        }
        
    }
        
}