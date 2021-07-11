package com.ly.feign.serivce;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "provider")
public interface FeignSerivce {


    @GetMapping("/show/{id}")
    public List getUsers(@PathVariable String id);
}
