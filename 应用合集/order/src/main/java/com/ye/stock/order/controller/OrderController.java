package com.ye.stock.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.ye.stock.order.http.HttpReqWrap;
import com.ye.stock.order.http.openfeign.OpenFeignHttp;
import com.ye.stock.order.dao.StockOrderDao;
import com.ye.stock.order.pojo.StockOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Resource
    private StockOrderDao stockOrderDao;


    @Resource
    private HttpReqWrap httpReqWrap;

    @RequestMapping("/add")
    public String add(String code){
        StockOrder st = new StockOrder();
        st.setCode(code);
        try {
            stockOrderDao.add(st);
        }catch ( Exception e){
            e.printStackTrace();
        }
        return  code;
    }

    @RequestMapping("find")
    public StockOrder findStockOrder(StockOrder st){
        StockOrder sts = stockOrderDao.getStockOrder(st);
        return sts;
    }


    @RequestMapping("del")
    public int delStockOrder(StockOrder st){
          stockOrderDao.del(st);
        return 1;
    }



    @RequestMapping("get")
    public JSONObject getFei(){
        String url ="https://quote.stock.pingan.com/restapi/nodeserver/quote/realTimeData?_=1625916388420";
         Map<String, String> headerMap = new HashMap<>();



        headerMap.put("Accept","application/json");
        headerMap.put("Accept-Encoding","gzip, deflate, br");
        headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
        headerMap.put("Connection","keep-alive");
        headerMap.put("Content-Length","66666");
        headerMap.put(" Content-Type","application/x-www-form-urlencoded");
        //headerMap.put("Cookie","Qs_lvt_316157=1625907411; WEBTRENDS_ID=cf9a4e9d-516c-9710-bd65-cf29dd5aab25; Qs_pv_316157=2623675471273765400%2C2014795303228802300; pa_stock_client_id=cnsz045161|102383406539231500|8aed4a95-9dc3-414c-bb0b-434c5543fad3; WT-FPC=id=2a2e3c441ebc2be1c141625907411944:lv=1625916384137:ss=1625916357873:fs=1625907411944:pn=4:vn=2; connect.sid=s%3A17DT1AMiUnBNViCI1mjD9CVNkoMnrR4E.AqLjvhwKPe3TIZoAsvq7GiCerKJYZRaNqHGBZ%2BktIPM");
        headerMap.put("Host","quote.stock.pingan.com");
        headerMap.put("Origin","https://m.stock.pingan.com");
        headerMap.put("Referer","https://m.stock.pingan.com/");
        headerMap.put("sec-ch-ua","Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
        headerMap.put("sec-ch-ua-mobile"," ?0");
        headerMap.put("Sec-Fetch-Dest","empty");
        headerMap.put("Sec-Fetch-Mode","cors");
        headerMap.put("Sec-Fetch-Site","same-site");
        headerMap.put(" User-Agent","");
        headerMap.put("Referer","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");




        JSONObject jsonObject = new JSONObject();
        
       // jsonObject.put("name", "孙悟空");


        jsonObject.put("version","2.0");
        jsonObject.put("channel","MobileH5");
        jsonObject.put(" requestId"," 18835c23-c660-4c1a-dd93-ec1c4fdfa476");
        jsonObject.put("cltplt"," h5");
        jsonObject.put("cltver"," 1.0");
        jsonObject.put("aid","");
        jsonObject.put("sid","");
        jsonObject.put(" ouid","");
        jsonObject.put("source","");
        //jsonObject.put("body%5Bcode%5D"," BI994105");
        jsonObject.put("body[code]",  "SZ000725");


        JSONObject obj  =  httpReqWrap.post(url,headerMap,jsonObject);
        return obj;
    }
}
