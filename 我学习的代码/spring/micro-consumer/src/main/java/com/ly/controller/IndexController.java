package com.ly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

	
	@Autowired
	private RestTemplate restTemplate;
	
	

    @GetMapping("warp/user/{id}")
    public String getUser(@PathVariable("id") Integer userId) {
        // url必须是rest风格路径
        return restTemplate.getForObject("http://provider/show/"+userId, String.class);
    }
}
