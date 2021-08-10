package com.ye.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
@Component
public class Consumer {
	
	private static Logger logger = LoggerFactory.getLogger(Consumer.class);
	
    @KafkaListener(topics = "user")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        
        logger.info("消费者 :topic:{}, message:{},offset:{}", consumerRecord.topic(), consumerRecord.value(), consumerRecord.offset());
        if(kafkaMassage.isPresent()){
            Object o = kafkaMassage.get();
            System.out.println(o);
        }

    }
}