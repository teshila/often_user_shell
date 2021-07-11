package com.ly.shenjian;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HisttoryXingQing {
	
	public static JSONArray jsoup(String stockCode,String dayOrWeek) throws UnsupportedEncodingException{
		String code=URLEncoder.encode(stockCode,"UTF-8");
		String index=URLEncoder.encode("false","UTF-8");
		String k_type=URLEncoder.encode(dayOrWeek,"UTF-8");
		String fq_type=URLEncoder.encode("qfq","UTF-8");
		String start_date=URLEncoder.encode("2019-11-12","UTF-8");
		String end_date=URLEncoder.encode("2019-12-07","UTF-8");
		String appid="179ad172abc72ffe5ab8d95516544c0c";
		String httpUrl = "https://api.shenjian.io/";
		String httpArg = "appid="+appid+"&code="+code+"&index="+index+"&k_type="+k_type+"&fq_type="+fq_type+"&start_date="+start_date+"&end_date="+end_date;
		String jsonResult = RequetHttpUtil.request(httpUrl, httpArg);
		JSONObject jsonObj = JSONObject.parseObject(jsonResult);
		JSONArray aray = (JSONArray) jsonObj.get("data");
		return aray;
	}


}
