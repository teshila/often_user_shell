package com.ly.job;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
public class DataJob {

	@Scheduled(cron = "0/3 * *  * * ? ") // 每10秒执行一次
	public void show() throws UnsupportedEncodingException {
		System.out.println("11");
	}

	
	
}
