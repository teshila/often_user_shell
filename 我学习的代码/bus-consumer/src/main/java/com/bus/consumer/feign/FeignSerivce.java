package com.bus.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider")
public interface FeignSerivce {

    @GetMapping("/data/{id}")
    public String feignClient(@PathVariable  String id);
}
