package com.ly.shenjian;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HistoryFenBi {
	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		shoup();
	}
	
	public static void shoup() throws UnsupportedEncodingException{
		String code=URLEncoder.encode("601857","UTF-8");
		String date=URLEncoder.encode("2019-12-02","UTF-8");
		String appid="9808e7db7e6d5bd3333aef63d04c2809";
		String httpUrl = "https://api.shenjian.io/";
		String httpArg = "appid="+appid+"&code="+code+"&date="+date;
		String jsonResult = RequetHttpUtil.request(httpUrl, httpArg);
		System.out.println(jsonResult);
		
	}
	
}
