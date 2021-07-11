package com.ye.consumer.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ye.dubbo.dubboapi.pojo.serivce.UserSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference
    private UserSerivce userSerivce;


    @GetMapping("/")
    public String show(){
        userSerivce.getUser();
        return  "11";

    }
}
