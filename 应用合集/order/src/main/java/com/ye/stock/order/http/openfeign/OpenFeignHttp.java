package com.ye.stock.order.http.openfeign;


import feign.HeaderMap;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Component
@FeignClient(name="servlet", url="https://quote.stock.pingan.com" )
public interface OpenFeignHttp {


    @PostMapping(value = "/restapi/nodeserver/quote/realTimeData?_=1625907475956",
                 consumes = {"application/x-www-form-urlencoded"})
    public String hello(Map<String, ?> formParams,HttpHeaders headers);

}
