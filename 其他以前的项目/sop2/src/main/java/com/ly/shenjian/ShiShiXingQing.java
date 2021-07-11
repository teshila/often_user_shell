package com.ly.shenjian;

import java.io.UnsupportedEncodingException;

public class ShiShiXingQing {
	public static String shoup() throws UnsupportedEncodingException{
		String appid="6837bf3c0f62b1b5bb24d09f4a352005";
		String httpUrl = "https://api.shenjian.io/";
		String httpArg = "appid="+appid;
		String jsonResult = RequetHttpUtil.request(httpUrl, httpArg);
		return jsonResult;
	}

	
	
}
