package com.demo.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;

@Service
public class UserSerivce {

	@Autowired
	private UserDao userDao;
	
	
	public Integer queryUser(Map map) {
		int i = userDao.findUser(map);
		return i;
	}
}
