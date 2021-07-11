package com.bus.consumer.controller;


import com.bus.consumer.feign.FeignSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private FeignSerivce feignSerivce;

    @GetMapping("/wrap/{id}")
    public String getWarpInfo(@PathVariable String id){

        return  feignSerivce.feignClient(id);
    }
}
