package com.ly.task;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.shenjian.ShiShiXingQing;

public class Test0 {
	public static void main(String[] args) throws UnsupportedEncodingException {
	  JSONObject jsonObj =  JSONObject.parseObject(ShiShiXingQing.shoup());
	 JSONArray aray = (JSONArray) jsonObj.get("data");
	 
	 
	 for (Object obj : aray) {
		System.out.println(obj);
	 }
	 
	}
}
