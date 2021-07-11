package com.temp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


//https://www.jb51.net/article/119052.htm
//https://blog.csdn.net/yz2015/article/details/81747068
//https://blog.csdn.net/msj0301zm/article/details/80405590

@Controller
@RequestMapping("publicutil")
public class PublicUtilController {
	private static final Logger logger = Logger.getLogger(PublicUtilController.class);

	@RequestMapping(value = "uploadImage")
	private void uploadImage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam MultipartFile[] upload) {

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			logger.error("response.getWriter()异常=" + e1);
			e1.printStackTrace();
		}
		String callback = request.getParameter("CKEditorFuncNum");

		// 获得response,request
		Map<String, Object> m = new HashMap<String, Object>();

		if (!ServletFileUpload.isMultipartContent(request)) {
			m.put("error", 1);
			m.put("message", "请选择文件!");
			// return m;
			logger.info("请选择文件!");
		}

		String originalFileName = null;// 上传的图片文件名
		String fileExtensionName = null;// 上传图片的文件扩展名
		for (MultipartFile file : upload) {
			if (file.getSize() > 10 * 1024 * 1024) {
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于10M');");
				out.println("</script>");

			}

			originalFileName = file.getOriginalFilename();
			logger.info("上传的图片文件名=" + originalFileName);
			fileExtensionName = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length())
					.toLowerCase();
			logger.info("图片文件扩展名=" + fileExtensionName);

			String[] imageExtensionNameArray = WebsiteConstant.IMAGE_EXTENSION_NAME_ARRAY;

			String allImageExtensionName = "";
			boolean isContain = false;// 默认不包含上传图片文件扩展名
			for (int i = 0; i < imageExtensionNameArray.length; i++) {
				if (fileExtensionName.equals(imageExtensionNameArray[i])) {
					isContain = true;
				}
				if (i == 0) {
					allImageExtensionName += imageExtensionNameArray[i];
				} else {
					allImageExtensionName += " , " + imageExtensionNameArray[i];
				}

			}

			String newFileName = java.util.UUID.randomUUID().toString() + fileExtensionName;
			String uploadPath = WebsiteConstant.PIC_APP_FILE_SYSTEM_CKEDITOR_LOCATION;
			if (isContain) {// 包含
				File pathFile = new File(uploadPath);
				if (!pathFile.exists()) { // 如果路径不存在，创建
					pathFile.mkdirs();
				}
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadPath, newFileName));
					// InputStream is=file.getInputStream();
					// File toFile = new File(uploadPath, newFileName);
					// OutputStream os = new FileOutputStream(toFile);
					// byte[] buffer = new byte[1024];
					// int length = 0;
					// while ((length = is.read(buffer)) > 0) {
					// os.write(buffer, 0, length);
					// }
					// is.close();
					// os.close();
				} catch (IOException e) {
					logger.error("FileUtils.copyInputStreamToFile uploadPath=" + uploadPath + " newFileName ="
							+ newFileName + " exception=" + e);
				}
				String imageUrl = WebsiteConstant.PIC_APP_SERVER_URL + "images/ckeditor/" + newFileName;
				// 返回"图像信息"选项卡并显示图片 ,在对应的文本框中显示图片资源url
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageUrl + "','')");
				out.println("</script>");

			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为"
						+ allImageExtensionName + "文件）');");
				out.println("</script>");
			}

		}
	}

}