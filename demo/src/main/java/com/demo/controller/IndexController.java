package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.UserSerivce;


@RestController
public class IndexController {

	@Resource
	private UserSerivce userService;
	
	
	@RequestMapping("/test")
	public String getCode() {
		return "11";
		
	}
	
	@RequestMapping("/query")
	public Map findUser(Map map) {
		//queryMap.put("password", pwd);
		int  i = userService.queryUser(map);
		Map msg = new HashMap();
		if(i>0) {
			msg.put("code", "登录成功");
		}
		
		msg.put("code", "用户名或密码不正确");
		
		return msg;
	}
	
	
	
}
