package com.ye.kafka;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

	/*
	 * @Autowired private Product product;
	 * 
	 * @PostConstruct public void init(){ for(int i=0;i<10;i++){
	 * product.send("afs"+i); } }
	 */
	 
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
