package com.ye.kafka.tmp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class MsgConsumer {
	private static Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

	@KafkaListener(topics = { "xiaoai" })
	public void listen(ConsumerRecord<?, ?> record) {
		logger.info("消费者 :topic:{}, message:{},offset:{}", record.topic(), record.value(), record.offset());
	}
}