package com.ly.email;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ly.common.util.NumberToRMB;
import com.ly.common.util.RowColor;


//@Component
public class EmailTemplate2 {
	
	//https://www.cnblogs.com/liuyitian/p/4065654.html
	//https://blog.csdn.net/hj7jay/article/details/56479431
	
	@Autowired
	private MyJavaMailSenderUtil srv;
	
	/*@Autowired
	private PDFUtil pdfutil;*/
	
	
	public static double get2Double(double a){  
	    DecimalFormat df=new DecimalFormat("0.00");  
	    return new Double(df.format(a).toString());  
	}  
	
	
	public static void main(String[] args) {
		System.out.println(get2Double(2.5867));;
	}
	
	
	public  void send(String title,List<?> stockList) throws Exception{
		StringBuffer sb = new StringBuffer();
		String code = "";
		String name  ="";
		String price  = "";
		String hangye = "";
		String diqu ="";
		String zongGuBen = "";
		String liuTongGuBen = "";
		String shiyinglv ="";
		String totalHand ="";
		String zuoritotalHand ="";
		String qianritotalHand ="";
		String yesterPrice = "";
		String qianRiPrice =  "";
		
		sb.append("<table style='width: 100%; '>");
		sb.append("<tbody>");
		sb.append("<tr>");
		sb.append("	<td style='background:url(https://rescdn.qqmail.com/zh_CN/htmledition/images/xinzhi/bg/a_10.jpg) repeat-x #bfdfec; min-height:550px; padding: 100px 55px 200px; '>");
		sb.append("	<div>");
		sb.append("	<table  style='width:100%; border-collapse:collapse; margin:0 0 10px' cellpadding='0' cellspacing='0' border='0'>");
		sb.append("<tr >");
		sb.append("<td "+RowColor.title+">序号</td>");
		sb.append("<td "+RowColor.title+">代码</td>");
		sb.append("<td "+RowColor.title+">名称</td>");
		sb.append("<td "+RowColor.title+">当前价</td>");
		sb.append("<td "+RowColor.title+">日K线</td>");
		sb.append("<td "+RowColor.title+">周K线</td>");
		sb.append("<td "+RowColor.title+">月K线</td>");
		sb.append("<td "+RowColor.title+">行业</td>");
		sb.append("<td "+RowColor.title+">地区</td>");
		sb.append("<td "+RowColor.title+">总股本</td>");
		sb.append("<td "+RowColor.title+">流通股本</td>");
		sb.append("<td "+RowColor.title+">市盈率</td>");
		sb.append("<td "+RowColor.title+">本日成交量</td>");
		sb.append("<td "+RowColor.title+">昨日成交量</td>");
		sb.append("<td "+RowColor.title+">前日成交量</td>");
		sb.append("<td "+RowColor.title+">本日换手</td>");
		sb.append("<td "+RowColor.title+">昨日换手</td>");
		sb.append("<td "+RowColor.title+">前日换手</td>");
		sb.append("</tr>");
		
		if (stockList != null && stockList.size() > 0) {
			
			for (int i = 0; i < stockList.size(); i++) {
				//sb.append("代码【" + stockList.get(i).getCode() + "】,名称【" + stockList.get(i).getName() + "】");
				
				
				//https://www.cnblogs.com/yangyi9343/p/4753675.html
				//https://www.cnblogs.com/a8457013/p/8135650.html
				//https://www.cnblogs.com/jiangyi-uestc/p/5689771.html
				//https://bbs.csdn.net/wap/topics/380039856
				//http://outofmemory.cn/code-snippet/83647/usage-POI-Java-mirror-jizhi-daochu-data-come-excel
				
				Field [] fields = stockList.get(i).getClass().getDeclaredFields();
				//System.out.println(fields);
				for (Field field : fields) {
					field.setAccessible(true); 
					if(field.getName().equals("code")){
						code =  (String) field.get(stockList.get(i));
					}
					
					
					if(field.getName().equals("name")){
						name =  (String) field.get(stockList.get(i));
					}
					
					if(field.getName().equals("closePrice")){
						price =  (String) field.get(stockList.get(i));
					}
					if(field.getName().equals("prevClose")){
						yesterPrice =  (String) field.get(stockList.get(i));
					}
					if(field.getName().equals("qianRiClose")){
						qianRiPrice =  (String) field.get(stockList.get(i));
					}
					
					if(field.getName().equals("hangye")){
						hangye =  (String) field.get(stockList.get(i));
					}
					
					
					if(field.getName().equals("diQu")){
						diqu =  (String) field.get(stockList.get(i));
					}
					
					
					if(field.getName().equals("liuTongGu")){
						liuTongGuBen =  (String) field.get(stockList.get(i));
					}
					
					
					if(field.getName().equals("shiyinglvJing")){
						shiyinglv =  (String) field.get(stockList.get(i));
					}
					
					
					if(field.getName().equals("zongGuBen")){
						zongGuBen =  (String) field.get(stockList.get(i));
					}
					
					if(field.getName().equals("benRiTotalHand")){
						totalHand =  (String) field.get(stockList.get(i));
					}
					
					if(field.getName().equals("zuoRiTotalHand")){
						zuoritotalHand =  (String) field.get(stockList.get(i));
					}
					
					if(field.getName().equals("qianRiTotalHand")){
						qianritotalHand =  (String) field.get(stockList.get(i));
					}
					
				}
				
				String daypicUrl = "<img src='http://image.sinajs.cn/newchart/daily/n/CODE.gif'/>";
				String weekpicUrl = "<img src='http://image.sinajs.cn/newchart/weekly/n/CODE.gif'/>";
				String monthUrl = "<img src='http://image.sinajs.cn/newchart/monthly/n/CODE.gif'/>";
				
				if(code.indexOf("6")==0){
					daypicUrl = daypicUrl.replace("CODE", "sh" +code);
					weekpicUrl = weekpicUrl.replace("CODE", "sh" +code);
					monthUrl = monthUrl.replace("CODE", "sh" +code);
				}else{
					daypicUrl = daypicUrl.replace("CODE", "sz" +code);
					weekpicUrl = weekpicUrl.replace("CODE", "sz" +code);
					monthUrl = monthUrl.replace("CODE", "sz" +code);
				}
			   
			  /* String daypicUrl = "<img src='http://120.78.225.98/soup/api/pic/day/"+code+".do'>";
			   String weekpicUrl = "<img src='http://120.78.225.98/soup/api/pic/week/"+code+".do'>";*/
			   
				if(i%2==0){
					sb.append("<tr>");
					sb.append("<td "+RowColor.evenLine+">"+(i+1)+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+code +"</td>");
					sb.append("<td "+RowColor.evenLine+">"+name+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+price +"</td>");
					sb.append("<td "+RowColor.evenLine+">"+daypicUrl+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+weekpicUrl+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+monthUrl+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+hangye+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+diqu+"</td>");
					sb.append("<td "+RowColor.evenLine+">"+zongGuBen+"【亿】</td>");
					sb.append("<td "+RowColor.evenLine+">"+liuTongGuBen+"【亿】</td>");
					sb.append("<td "+RowColor.evenLine+">"+shiyinglv+"</td>");
					
					
					
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					
					//本日
					Double amout1 = Double.valueOf(totalHand)*Double.valueOf(price)*100;
					String str = nf.format(amout1);
					
					
					//昨日
					Double amout2 = Double.valueOf(zuoritotalHand)*Double.valueOf(yesterPrice)*100;
					String str2 = nf.format(amout2);
					
					//前日
					Double amout3 = Double.valueOf(qianritotalHand)*Double.valueOf(qianRiPrice)*100;
					String str3 = nf.format(amout3);
					
					sb.append("<td "+RowColor.evenLine+">现量"+NumberToRMB.formatNum(str,false)+"</td>");
					sb.append("<td "+RowColor.evenLine+">昨量"+NumberToRMB.formatNum(str2,false)+"</td>");
					sb.append("<td "+RowColor.evenLine+">前量"+NumberToRMB.formatNum(str3,false)+"</td>");
					
					//sb.append("<td "+RowColor.evenLine+">"+((Double.valueOf(totalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					
					double rat01 = (Double.valueOf(totalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					sb.append("<td "+RowColor.evenLine+">"+get2Double(rat01)+"%</td>");
					
					double rat02 = (Double.valueOf(zuoritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					sb.append("<td "+RowColor.evenLine+">"+get2Double(rat02)+"%</td>");
					//sb.append("<td "+RowColor.evenLine+">"+((Double.valueOf(zuoritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					
					
					//sb.append("<td "+RowColor.evenLine+">"+((Double.valueOf(qianritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					double rat03 = (Double.valueOf(qianritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					sb.append("<td "+RowColor.evenLine+">"+get2Double(rat03)+"%</td>");
					
					
					sb.append("</tr>");
				}else{
					sb.append("<tr>");
					sb.append("<td "+RowColor.oddLine+">"+(i+1)+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+code +"</td>");
					sb.append("<td "+RowColor.oddLine+">"+name+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+price +"</td>");
					sb.append("<td "+RowColor.oddLine+">"+daypicUrl+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+weekpicUrl+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+monthUrl+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+hangye+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+diqu+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+zongGuBen+"【亿】</td>");
					sb.append("<td "+RowColor.oddLine+">"+liuTongGuBen+"【亿】</td>");
					sb.append("<td "+RowColor.oddLine+">"+shiyinglv+"</td>");
					
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					
					//本日
					Double amout1 = Double.valueOf(totalHand)*Double.valueOf(price)*100;
					String str = nf.format(amout1);
					
					
					//昨日
					Double amout2 = Double.valueOf(zuoritotalHand)*Double.valueOf(yesterPrice)*100;
					String str2 = nf.format(amout2);
					
					//前日
					Double amout3 = Double.valueOf(qianritotalHand)*Double.valueOf(qianRiPrice)*100;
					String str3 = nf.format(amout3);
					
					
					/*sb.append("<td "+RowColor.oddLine+">"+NumberToRMB.formatNum(str,false)+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+NumberToRMB.formatNum(str2,false)+"</td>");
					sb.append("<td "+RowColor.oddLine+">"+NumberToRMB.formatNum(str3,false)+"</td>");*/
					

					sb.append("<td "+RowColor.oddLine+">现量"+NumberToRMB.formatNum(str,false)+"</td>");
					sb.append("<td "+RowColor.oddLine+">昨量"+NumberToRMB.formatNum(str2,false)+"</td>");
					sb.append("<td "+RowColor.oddLine+">前量"+NumberToRMB.formatNum(str3,false)+"</td>");
					
					//sb.append("<td "+RowColor.oddLine+">"+((Double.valueOf(totalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					double rat01 = (Double.valueOf(totalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					sb.append("<td "+RowColor.oddLine+">"+get2Double(rat01)+"%</td>");
					
					double rat02 = (Double.valueOf(zuoritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					sb.append("<td "+RowColor.oddLine+">"+get2Double(rat02)+"%</td>");
					//sb.append("<td "+RowColor.oddLine+">"+((Double.valueOf(zuoritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					
					
					double rat03 = (Double.valueOf(qianritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000)*100;
					
					sb.append("<td "+RowColor.oddLine+">"+get2Double(rat03)+"%</td>");
					//sb.append("<td "+RowColor.oddLine+">"+((Double.valueOf(qianritotalHand)*100)/(Double.valueOf(liuTongGuBen)*100000000))*100+"%</td>");
					
					sb.append("</tr>");
				}
			}
			
			sb.append("	</table>");
			sb.append("	</div>");
			sb.append("	</td>");
			sb.append("	</tr>");
			sb.append("	</tbody>");
			sb.append("	</table>");
			
			srv.sendHtmlEmail(title, sb.toString());
			
		
		}
		
	}
}
