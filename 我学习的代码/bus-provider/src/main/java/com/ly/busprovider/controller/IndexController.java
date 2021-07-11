package com.ly.busprovider.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {



    @GetMapping("/data/{id}")
    public String show(@PathVariable  String id){

        return id;
    }
}
