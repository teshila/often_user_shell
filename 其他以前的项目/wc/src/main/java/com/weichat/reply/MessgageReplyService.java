package com.weichat.reply;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ly.controller.WechatController;

@Component
public class MessgageReplyService {
	private static Logger logger = Logger.getLogger(MessgageReplyService.class);
	
	
	/**
     * 处理微信发来的请求
     * 
     * @param request
     * @return
     */
    public String weixinPost(HttpServletRequest request) {
        String respMessage = null;
        try {

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.xmlToMap(request);
            logger.debug(requestMap);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息内容
            String content = requestMap.get("Content");
            
            logger.info("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //这里根据关键字执行相应的逻辑，只有你想不到的，没有做不到的
                if(content.equals("0")){
                	TextMessage text = new TextMessage();
                    text.setContent("1.<a href='11'>天气</a>\n2手机号\n3.电脑");
                    text.setToUserName(fromUserName);
                    text.setFromUserName(toUserName);
                    text.setCreateTime(new Date().getTime() + "");
                    text.setMsgType(msgType);
                    
                    respMessage = MessageUtil.textMessageToXml(text);
                }else if(content.equals("1")){
                	
                }
                
                //自动回复
              /*  TextMessage text = new TextMessage();
                text.setContent("the text is " + content);
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime() + "");
                text.setMsgType(msgType);
                
                respMessage = MessageUtil.textMessageToXml(text);*/
               
            } /*else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
                String eventType = requestMap.get("Event");// 事件类型
                
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
                    respContent = "欢迎关注xxx公众号！";
                    return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
                    String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    logger.info("eventKey is:" +eventKey);
                    return xxx;
                }
            }
            //开启微信声音识别测试 2015-3-30
            else if(msgType.equals("voice"))
            {
                String recvMessage = requestMap.get("Recognition");
                //respContent = "收到的语音解析结果："+recvMessage;
                if(recvMessage!=null){
                    respContent = TulingApiProcess.getTulingResult(recvMessage);
                }else{
                    respContent = "您说的太模糊了，能不能重新说下呢？";
                }
                return MessageResponse.getTextMessage(fromUserName , toUserName , respContent); 
            }
            //拍照功能
            else if(msgType.equals("pic_sysphoto"))
            {
                
            }
            else
            {
                return MessageResponse.getTextMessage(fromUserName , toUserName , "返回为空"); 
            }*/
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");// 事件类型
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    
                    TextMessage text = new TextMessage();
                    text.setContent("等了好久，终于等到你了。。。请选择功能");
                    text.setToUserName(fromUserName);
                    text.setFromUserName(toUserName);
                    text.setCreateTime(new Date().getTime() + "");
                    text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    
                    respMessage = MessageUtil.textMessageToXml(text);
                } 
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
                    
                    
                } 
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    if (eventKey.equals("customer_telephone")) {
                        TextMessage text = new TextMessage();
                        text.setContent("0755-86671980");
                        text.setToUserName(fromUserName);
                        text.setFromUserName(toUserName);
                        text.setCreateTime(new Date().getTime() + "");
                        text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                        
                        respMessage = MessageUtil.textMessageToXml(text);
                    }
                }
            }
        }
        catch (Exception e) {
        	logger.error("error......");
        }
        return respMessage;
    }
}
