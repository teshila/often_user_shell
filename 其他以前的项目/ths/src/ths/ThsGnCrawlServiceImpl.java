package ths;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//https://www.jianshu.com/p/925071dcea51
//https://cloud.tencent.com/developer/article/1579729
//https://blog.csdn.net/qq_29817481/article/details/101052012
public class ThsGnCrawlServiceImpl  {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThsGnCrawlServiceImpl.class);

    /**
     * 同花顺全部概念板块url
     */
    private final static String GN_URL = "http://quote.eastmoney.com/center/gridlist.html#hs_a_board";


    public List<HashMap<String, String>> ThsGnCrawlListUrl() {
        System.setProperty("webdriver.chrome.driver", "C:\\google\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //是否启用浏览器界面的参数
        //无界面参数
//        options.addArguments("headless");
        //禁用沙盒 就是被这个参数搞了一天
//        options.addArguments("no-sandbox");
        WebDriver webDriver = new ChromeDriver(options);
        try {
            // 根据网速设置，网速慢可以调低点
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            webDriver.get(GN_URL);
            Thread.sleep(1000L);
            String gnWindow = webDriver.getWindowHandle();
            // 获取同花顺概念页面的HTML
            String thsGnHtml = webDriver.getPageSource();
            LOGGER.info("获取同花顺url:[{}]的html为:/n{}", GN_URL, thsGnHtml);
            System.out.println(thsGnHtml);
        } catch (Exception e) {
            LOGGER.error("获取同花顺概念页面的HTML，出现异常:", e);
        } finally {
            webDriver.close();
            webDriver.quit();
        }
        return null;
    }
    
    public static void main(String[] args) {
    	ThsGnCrawlServiceImpl t = new ThsGnCrawlServiceImpl();
    	t.ThsGnCrawlListUrl();
    			
	}
}
