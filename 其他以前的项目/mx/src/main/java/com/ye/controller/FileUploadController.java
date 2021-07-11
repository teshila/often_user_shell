package com.ye.controller;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//https://blog.csdn.net/yz2015/article/details/81747068
/**
 * @description: 图片上传
 * @author: yz
 * @create: 2018/8/16 11:09
 */
@Controller
@RequestMapping("/common")
public class FileUploadController {

    /*
     * 图片命名格式
     */
    private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMddHHmmss";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH = "/upload/hximg/";

    /*
     * 上传图片
     */
    @RequestMapping("/uploadImg")
    public void uplodaImg(@RequestParam("upload") MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
        FileResponse fileResponse = new FileResponse();
        try {
            PrintWriter out = response.getWriter();
            logger.info("fileSize: "+file.getSize());
            // 图片大小不超过500K
            if (file.getSize() > 1024*5022000) {
                String error = fileResponse.error(0, "图片大小超过500K");
                out.println(error);
                return;
            }
            String proPath = request.getSession().getServletContext().getRealPath("/");
            String proName = request.getContextPath();
            String path = proPath + UPLOAD_PATH;
            
            File fils = new File(path);
            if(!fils.exists()){
            	
            	fils.mkdirs();
            }
            
            String fileName = file.getOriginalFilename();
            String uploadContentType = file.getContentType();
            String fileSuffix = StringUtils.substring(fileName, fileName.lastIndexOf(".") + 1);
            String expandedName = "." +fileSuffix;
            
          /*  String expandedName = "";
            if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
                expandedName = ".jpg";
            } else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
                expandedName = ".png";
            } else if (uploadContentType.equals("image/gif")) {
                expandedName = ".gif";
            } else if (uploadContentType.equals("image/bmp")) {
                expandedName = ".bmp";
            } else {
                String error = fileResponse.error(0, "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）");
                out.println(error);
                return;
            }
            */
            
            
            
            
            DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
            fileName = df.format(new Date()) + expandedName;
            FileUtil.uploadFile(file.getBytes(), path, fileName);
            String success = fileResponse.success(1, fileName, proName + "/upload/hximg/" + fileName, null);
            out.println(success);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            String error = fileResponse.error(0, "系统异常");
            try {
                response.getWriter().println(error);
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
