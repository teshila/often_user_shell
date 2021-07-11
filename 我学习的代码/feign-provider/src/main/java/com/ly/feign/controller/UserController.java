package com.ly.feign.controller;

import com.ly.feign.serivce.FeignSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private FeignSerivce feignSerivce;


    @GetMapping("feign/user/{id}")
    public List getUsers(@PathVariable  String id){
        System.out.println("111");
       List list =  feignSerivce.getUsers(id);
       return  list;
    }

}
