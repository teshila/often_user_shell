package com.ye.kafka.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/kafka")
public class MsgProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

	@RequestMapping("/send")
	public String send(String msg) {
		kafkaTemplate.send("xiaoai", msg);
		logger.info("生产者 :topic:{}, message:{} ", "xiaoai", msg);
		return "success";
	}
}