package com.ye.stock.order.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTask {


    @Scheduled(cron = "0/5 * * * * ? ")
    public void taskSendOrder(){
        System.out.println("1234");

    }

}
