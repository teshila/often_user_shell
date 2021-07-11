package com.ly.shenjian;

import java.io.UnsupportedEncodingException;

public class DapAnZhiShu {

	public static void shoup() throws UnsupportedEncodingException {
		String appid = "db3f2cb59c87cbec776701791a4090a0";
		String httpUrl = "https://api.shenjian.io/";
		String httpArg = "appid=" + appid;
		String jsonResult = RequetHttpUtil.request(httpUrl, httpArg);
		System.out.println(jsonResult);

	}
}
