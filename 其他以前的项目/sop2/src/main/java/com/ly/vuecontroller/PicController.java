package com.ly.vuecontroller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//https://blog.csdn.net/cx15733896285/article/details/79927215
//https://www.jianshu.com/p/5fc5b10fc8dc
//https://blog.csdn.net/LM5463640/article/details/71429762
@Controller
@RequestMapping("api")
public class PicController {
	
	
	@RequestMapping(value="/pic/{type}/{code}")
	public void showPic(HttpServletRequest request,@PathVariable String type,@PathVariable String code,HttpServletResponse response){
		
		if(code.indexOf("6")==0){
			code= "sh" +code;
		}else{
			code = "sz" +code;
		}
		
		String uploadUrl = "c://pic//day//" +code+ ".gif" ;
		if(type.contains("week")){
			uploadUrl = "c://pic//week//" +code + ".gif" ;
		}
		try {
			File filePath = new File(uploadUrl);
			if(filePath.exists()){
				//读图片
				FileInputStream inputStream = new FileInputStream(filePath);  
		        int available = inputStream.available();
		        byte[] data = new byte[available];
		        inputStream.read(data);  
		        inputStream.close();  
		        //写图片
		        response.setContentType("image/gif");  
		        response.setCharacterEncoding("UTF-8");
		        OutputStream stream = new BufferedOutputStream(response.getOutputStream());  
		        stream.write(data);  
		        stream.flush();  
		        stream.close();  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
