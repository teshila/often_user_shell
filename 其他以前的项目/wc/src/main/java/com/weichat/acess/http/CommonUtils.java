package com.weichat.acess.http;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信工具类
 *
 */
public class CommonUtils {

    private static Logger log =  Logger.getLogger(CommonUtils.class);

  /*  public final static String APPID = "wx2de043826bd11f5b";

    public final static String APPSECRET = "eb360c1826a20ebfd576fdb66deb2626"; 
*/
    
    public final static String APPID = "wxf6afeff13ff4a964";

    public final static String APPSECRET = "901bed0fbe1facfa9279eb19bcb22afd"; 
    
    
    //获取用户详细信息
    public final static String SCOPE_INFO = "snsapi_userinfo";

    //近获取用户open_id
    public final static String SCOPE_BASE = "snsapi_base";

    //微信请求接口地址  
    //获取access_token 接口地址   Get
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
   
    
    
    public final static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    
    
    
    //获取网页授权登录access_token 接口地址Get
    public final static String OAUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //获取授权登录用户信息 接口地址Get
    public final static String OAUTH_WCHAT_USER_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public final static String OAUTH_LOGIN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    //access_token有效时间 时间有时间是7200 为了防止延时导致获取access_token的间断
    public static long expires_in = 7100;

    //上次获取access_token时间
    private static Long lastGetAccessTokenTime = 0L;

    //当前access_token值
    private static String access_token = null;

    /***
     * 获取access_token 
     * @return access_token
     * @throws IOException
     */
    public static String getAccessToken() throws IOException{
        if (access_token == null) {//当前没有获取access_token
            access_token = refreshAccessToken();
        }else {//已经获取过access_token
            Long curTime = System.currentTimeMillis();
            if ((curTime-lastGetAccessTokenTime)/1000 >= expires_in) {
                access_token = refreshAccessToken();
            }
        }       
        return access_token;
    }

    /***
     * 刷新access_token 
     * @return access_token
     * @throws IOException
     */
	public static String refreshAccessToken() throws IOException {
		String token = null;
		try {
			lastGetAccessTokenTime = System.currentTimeMillis();
			String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
			String result;
			token = null;
			result = HttpRequestor.doGet(url);
			JSONObject json = JSONObject.parseObject(result);
			token = (String) json.get("access_token");
			System.out.println(result);
			if (token == null) {
				String errorCode = (String) json.get("errcode");
				String errorMsg = (String) json.get("errmsg");
				log.error("获取access_token失败:错误代码：" + errorCode + "错误信息：" + errorMsg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
    
    
    
    
    

    /**
     * 获取授权登录access_token
     * @param code
     * @return
     * @throws IOException
     */
    public static OAuthAccessToken getOAuthAccessToken(String code) throws IOException{
        OAuthAccessToken accessToken = null;
		try {
			String url = OAUTH_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code); 
			String result = HttpRequestor.doGet(url);
			JSONObject json= JSONObject.parseObject(result);
			String token = json.getString("access_token");
			if(token == null){
			    String errorCode = json.getString("errcode");
			    String errorMsg = json.getString("errmsg");
			    System.out.println(json.toString());
			    log.error("获取授权登录access_token失败:错误代码：" + errorCode + "错误信息：" + errorMsg);
			    return null;
			}       

			accessToken = (OAuthAccessToken) JSONObject.parseObject(result, OAuthAccessToken.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return accessToken;
    }

    /**
     * 获取用户信息 
     * @return 
     * @throws IOException
     */
    public static WChartUser getOAuthUserInfo(String code) throws IOException{
        WChartUser user = null;
		try {
			OAuthAccessToken accessToken = CommonUtils.getOAuthAccessToken(code);
			if (accessToken == null) {
			    return null;
			}
			String token = accessToken.getAccess_token();
			String openid = accessToken.getOpenid();
			String url = OAUTH_WCHAT_USER_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
			String result = HttpRequestor.doGet(url);
			JSONObject jsonObject = JSONObject.parseObject(result);
			openid = jsonObject.getString("openid");
			if(openid == null){
			    String errorCode = jsonObject.getString("errcode");
			    String errorMsg = jsonObject.getString("errmsg");
			    System.out.println(jsonObject.toString());
			    log.error("获取access_token失败:错误代码：" + errorCode + "错误信息：" + errorMsg);
			    return null;
			}

			user = JSONObject.parseObject(result, WChartUser.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
    }   

    public static String getOAuthLoginUrl(String redirectUrl, String scope){
        return OAUTH_LOGIN_URL.replace("APPID", APPID).replace("REDIRECT_URI", redirectUrl)
                .replace("SCOPE", scope);
    } 

    public static void main(String []args) throws Exception{
        //WChartUtils.getOAuthUserInfo("123");
//      String result = "{ \"access_token\":\"ACCESS_TOKEN\", \"expires_in\":7200, \"refresh_token\":\"REFRESH_TOKEN\", \"openid\":\"OPENID\", \"scope\":\"SCOPE\" }";
//      System.out.println(result);
//      OAuthAccessToken accessToken = JSONObject.parseObject(result, OAuthAccessToken.class);
//      System.out.println(accessToken.getOpenid());
//      System.out.println(accessToken.getAccess_token());
        System.out.println(CommonUtils.getAccessToken());
    }   
}
