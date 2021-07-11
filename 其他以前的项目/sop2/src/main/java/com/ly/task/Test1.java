package com.ly.task;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;
import com.ly.shenjian.HisttoryXingQing;

public class Test1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		JSONArray aray = HisttoryXingQing.jsoup("603991", "day");
		for (Object obj : aray) {
			System.out.println(obj);
		}

	}
}
