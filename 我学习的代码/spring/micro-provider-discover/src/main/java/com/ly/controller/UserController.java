package com.ly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ly.dao.UserDao;
import com.ly.pojo.User;

@RestController
public class UserController {

	
	@Autowired
	private UserDao userDao;
	
	
	@GetMapping("show/{id}")
	public List show(@PathVariable String id) throws InterruptedException {
		List  users = userDao.getUsers();
		return users;
	}
}
