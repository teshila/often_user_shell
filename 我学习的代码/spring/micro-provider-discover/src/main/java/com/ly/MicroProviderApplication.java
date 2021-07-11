package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ly.dao")
public class MicroProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProviderApplication.class, args);
	}

}
