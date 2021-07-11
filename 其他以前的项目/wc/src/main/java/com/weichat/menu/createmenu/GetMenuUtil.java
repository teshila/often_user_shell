package com.weichat.menu.createmenu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.weichat.acess.http.CommonUtils;
import com.weichat.acess.http.HttpRequestor;
import com.weichat.menu.wrap.Button;
import com.weichat.menu.wrap.ClickButton;
import com.weichat.menu.wrap.Menu;
import com.weichat.menu.wrap.ViewButton;

public class GetMenuUtil {
	
	
	public static Menu initMenuList() {

		Menu menu = new Menu();

		ClickButton button11 = new ClickButton();

		button11.setName("了解杰瑞教育");

		button11.setType("click");

		button11.setKey("11");

		ClickButton button12 = new ClickButton();

		button12.setName("加入杰瑞教育");

		button12.setType("click");

		button12.setKey("12");

		ViewButton button21 = new ViewButton();

		button21.setName("杰瑞教育官网");

		button21.setType("view");

		button21.setUrl("http://www.jerehedu.com");

		ViewButton button22 = new ViewButton();

		button22.setName("杰瑞教育新闻网");

		button22.setType("view");

		button22.setUrl("http://www.jredu100.com");

		ClickButton button31 = new ClickButton();

		button31.setName("杰小瑞");

		button31.setType("click");

		button31.setKey("31");

		Button button1 = new Button();

		button1.setName("杰瑞教育"); // 将11/12两个button作为二级菜单封装第一个一级菜单

		button1.setSub_button(new Button[] { button11, button12 });

		Button button2 = new Button();

		button2.setName("相关网址"); // 将21/22两个button作为二级菜单封装第二个二级菜单

		button2.setSub_button(new Button[] { button11, button12 });

		menu.setButton(new Button[] { button1, button2, button31 });// 将31Button直接作为一级菜单

		return menu;

	}
	//https://www.cnblogs.com/zjrodger/p/4630237.html
	//https://blog.csdn.net/CoderBruis/article/details/80499732?utm_source=blogxgwz3
	//https://www.cnblogs.com/jerehedu/p/7058353.html
	//https://blog.csdn.net/fengyan5022/article/details/79399590
	public static int createMenu() throws Exception{
		int result = 0;
		String accessToken = CommonUtils.getAccessToken();
		Menu menu  = GetMenuUtil.initMenuList();
		String menuJson = JSON.toJSONString(menu,SerializerFeature.DisableCircularReferenceDetect);  
		String postURL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		String createMenu = HttpRequestor.doPost(postURL, menuJson);
		 if(createMenu!=null){
			 JSONObject jsonObject = JSON.parseObject(createMenu);
			 result = jsonObject.getInteger("errcode");
		 }
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		int result = GetMenuUtil.createMenu();
		if(result==0){
		System.out.println("菜单创建成功！");
		}else{
		   System.out.println("菜单创建失败");
		}
	}
	
}
