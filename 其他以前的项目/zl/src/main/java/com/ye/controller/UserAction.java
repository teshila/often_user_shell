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


@Controller
public class UserAction {

	
	@RequestMapping(value="/picPrev/{expandedName}/{filename}")
	public void showPic(HttpServletRequest request,@PathVariable String expandedName,@PathVariable String filename,HttpServletResponse response){
		
		//String fileURL =   "c:/tempImag/"+filename + ".png";
		String fileURL =   "c:/tempImag/"+filename + expandedName;
		
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
		PrintWriter out =  response.getWriter();;
		System.out.println(fileName);
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		String name = null;
		
		//String expandedName = fileName.substring(fileName.lastIndexOf("."),fileName.length()) ;
		
		//https://www.cnblogs.com/MrSaver/p/6597278.html	
		
		
		
		 String uploadContentType = multipartFile.getContentType();
		 
		 
		 String expandedName = "";
	        if (uploadContentType.equals("image/pjpeg")
	                || uploadContentType.equals("image/jpeg")) {
	            // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
	            expandedName = ".jpg";
	        } else if (uploadContentType.equals("image/png")
	                || uploadContentType.equals("image/x-png")) {
	            // IE6上传的png图片的headimageContentType是"image/x-png"
	            expandedName = ".png";
	        } else if (uploadContentType.equals("image/gif")) {
	            expandedName = ".gif";
	        } else if (uploadContentType.equals("image/bmp")) {
	            expandedName = ".bmp";
	        } else {
	            //文件格式不符合，返回错误信息
	            out.println("<script type=\"text/javascript\">");
	            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
	                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
	            out.println("</script>");
	        }
	        
	        
	        
	        
        if (multipartFile.getSize() > 60000 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
					+ ",''," + "'文件大小不得大于60000k');");
			out.println("</script>");
		}
        
	        
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
	
		
		
		String fileURL =request.getContextPath() + "/picPrev/"+expandedName+"/"+name.substring(0,name.lastIndexOf(".")) + ".do";
		
        
		//String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+fileURL+"');</script>";
		String s = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" +fileURL+"','');</script>";
		out.print(s);
		out.flush();
	
		
	}
}
