package com.ye.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


//@Controller
public class UserAction2 {

	
	@RequestMapping(value="/picPrev/{filename}")
	public void showPic(HttpServletRequest request,@PathVariable String filename,HttpServletResponse response){
		
		String fileURL =   "c:/tempImag/"+filename + ".png";
		
		System.out.println(fileURL);
		try {
			File filePath = new File(fileURL);
			if(filePath.exists()){
				//读图片
				FileInputStream inputStream = new FileInputStream(filePath);  
		        int available = inputStream.available();
		        byte[] data = new byte[available];
		        inputStream.read(data);  
		        inputStream.close();  
		        //写图片
		        response.setContentType("image/png");  
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
	
	
	@RequestMapping(value="upload/ckeditImg")
	public void uploadFile(@RequestParam("upload") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		String fileName = multipartFile.getOriginalFilename();
		
		System.out.println(fileName);
		
		String name = null;
		
		String expandedName = fileName.substring(fileName.lastIndexOf("."),fileName.length()) ;
		
		   //文件命名并保存到服务器
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        name = df.format(new Date()) +expandedName;
        String DirectoryName =request.getContextPath()+"/tempImag";
        System.out.println(DirectoryName);
        try {
            File file1 = new File("c:/tempImag/" +name);
            if(!file1.exists()){
            	file1.mkdirs();
            }
            
            System.out.println(file1.getPath());
            multipartFile.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
		
		
//		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		PrintWriter out;
		
		String fileURL =request.getContextPath() + "/picPrev/"+name.substring(0,name.lastIndexOf(".")) + ".do";
		//String fileURL =  "/picPrev/"+name.substring(0,name.lastIndexOf(".")) + ".do";
		
        
		//String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+fileURL+"');</script>";
		String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" +fileURL+"','');</script>";
		try {
			out = response.getWriter();
			out.print(s);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
