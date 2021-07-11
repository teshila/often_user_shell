package com.ly.cloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class IndexController {


    @Value("${label}")
    private String dataStr;


    @GetMapping("/show")
    public String getData(){

       return  dataStr;
    }
}
