package com.gwssi.rodimus.doc.v2.core.util;

import java.io.File;
import java.io.FileOutputStream;

import com.gwssi.rodimus.exception.RodimusException;
import com.gwssi.rodimus.util.FileUtil;
import com.gwssi.rodimus.util.StringUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDF 工具类。
 * 
 * @author liuhailong(liuhailong2008#foxmail.com)
 */
public class PdfUtil {
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		addPageNo("H:\\gwssi\\setup_1100.pdf",1);
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start) + "毫秒");
	}

	
	/** 
	 * 给PDF文件加页码。
	 * 
	 * 	292毫秒。
	 * 
	 * @param sourcePhyPath 源PDF文件物理路径
	 * @param skipCoverCnt 需略过的封面页数
	 */
	public static void addPageNo(String sourcePhyPath,int skipCoverCnt) {
		String destPhyPath = sourcePhyPath + ".temp.pdf";
		addPageNo(sourcePhyPath,destPhyPath,skipCoverCnt);
		File destFile = new File(destPhyPath);
		if(destFile.exists() && destFile.length()>0){
			FileUtil.cleanDir(new File(sourcePhyPath));
			destFile.renameTo(new File(sourcePhyPath));
		}
	}
	/**
	 * 给PDF文件加页码。
	 * 
	 * @param sourcePhyPath 源PDF文件物理路径
	 * @param destPhyPath 目标PDF文件路径
	 * @param skipCoverCnt 需略过的封面页数
	 */
	public static void addPageNo(String sourcePhyPath,String destPhyPath,int skipCoverCnt) {
		if(StringUtil.isBlank(sourcePhyPath)){
			throw new RodimusException("源PDF文件物理路径不能为空。");
		}
		if(StringUtil.isBlank(destPhyPath)){
			throw new RodimusException("目标PDF文件路径不能为空。");
		}
		sourcePhyPath = sourcePhyPath.trim();
		destPhyPath = destPhyPath.trim();
		if(sourcePhyPath.equals(destPhyPath)){
			throw new RodimusException("“源PDF文件物理路径”和“目标PDF文件路径”不能相同。");
		}
		if(skipCoverCnt<0){
			skipCoverCnt = 0;
		}
		try	{
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			PdfReader reader = new PdfReader(sourcePhyPath);
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(destPhyPath));
			document.open();
			PdfContentByte canvas = writer.getDirectContent();
			PdfImportedPage page;
			int pageCnt = reader.getNumberOfPages() - skipCoverCnt;
			if(pageCnt<=0){
				return ;
			}
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				String text = "";
				if(i>skipCoverCnt){
					text = "第 "+ (i-1) + " 页     共 " + pageCnt + " 页" ;
				}
				page = writer.getImportedPage(reader, i);
				canvas.addTemplate(page, 1f, 0, 0, 1, 0, 0);
				canvas.beginText();
				canvas.setFontAndSize(bf, 10);
				canvas.showTextAligned(Element.ALIGN_CENTER, text, 300, 50, 0);
				canvas.endText();
				document.newPage();
			}
			document.close();
		}catch(Exception e){
			throw new RodimusException(e.getMessage(),e);
		}
	}
}
