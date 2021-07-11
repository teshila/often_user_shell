package com.ly.provider.controller;

import com.ly.provider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/show/{id}")
    public List getUser(@PathVariable String id){


        return userDao.getUsers(id);
    }
}
