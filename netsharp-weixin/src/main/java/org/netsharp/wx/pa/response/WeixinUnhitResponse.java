package org.netsharp.wx.pa.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;

/**
 * 未命中时的微信回复
 * 不需要配置在素材库中和微信回复类型中
 */
public class WeixinUnhitResponse implements IWeixinResponsor {
	
	Log logger=LogFactory.getLog(this.getClass().getSimpleName());
	
    private String key;
    
    public final ResponseMessage response(RequestMessage request)  {
    	
        PublicAccount publicAccount = PublicAccountManager.getInstance().get(request.getToUserName()).getAccount();

        logger.warn(publicAccount.getName());
        //执行跳转
        if (publicAccount.getTurnout()) {
            TurnoutException turnoutException = new TurnoutException("请进行第三方转发");
            turnoutException.setPublicAccount(publicAccount);
            throw turnoutException;
        }

        // 未命中匹配
        String unhintCode = publicAccount.getSubscribeCode();
        if(StringManager.isNullOrEmpty(unhintCode)){
            NReply reply = PublicAccountManager.getInstance().getReply(unhintCode, publicAccount.getOriginalId()) ;
            if (reply != null) {
                return new WeixinReplyResponse().response(reply, request);
            }
        }
        
        // 当客服不在线时；
        TextResponse message = new TextResponse();
        {
        	 message.setToUserName(request.getFromUserName());
             message.setFromUserName(request.getToUserName());
             message.setContent("您好，" + publicAccount.getName() + "欢迎您!");
        }
        
        return message;
    }

    public final String getKey() {
        return key;
    }

    public final void setKey(String value) {
        key = value;
    }

    public final boolean validate(RequestMessage request) {
        // 当客服在线时，转入客服系统；
        return true;
    }
}