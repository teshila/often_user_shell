package com.ly.shenjian;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ShiShiFengBi {
	
	public static void shoup() throws UnsupportedEncodingException{
		String codes=URLEncoder.encode("601857,603999","UTF-8");
		String appid="b4625dd4d6b0bd79e00a24d160250b2b";
		String httpUrl = "https://api.shenjian.io/";
		String httpArg = "appid="+appid+"&codes="+codes;
		String jsonResult = RequetHttpUtil.request(httpUrl, httpArg);
		System.out.println(jsonResult);
		
	}
	
}
