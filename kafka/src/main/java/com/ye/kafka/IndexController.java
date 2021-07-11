package com.ye.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ye.kafka.tmp.MsgConsumer;

@Controller
public class IndexController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/send")
	public String send(String msg) {
		System.out.println("12");
		
		kafkaTemplate.send("user", msg);
		logger.info("生产者 :topic:{}, message:{} ", "xiaoai", msg);
		return "success";
	}
}
