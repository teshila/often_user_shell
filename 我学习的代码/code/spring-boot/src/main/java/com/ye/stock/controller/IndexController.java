package com.ye.stock.controller;

import com.ye.stock.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/show")
    public Map hello(){
        Map map = new HashMap();
        map.put("key","我是一只可爱的小花猫");
        List list = userDao.findUser();
        map.put("list",list);
        return map;
    }
}
