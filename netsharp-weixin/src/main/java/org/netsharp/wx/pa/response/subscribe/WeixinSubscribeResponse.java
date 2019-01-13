package org.netsharp.wx.pa.response.subscribe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.PublicAccountContext;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.event.SubscribeEvent;

/**
 * 微信添加关注
 */
public class WeixinSubscribeResponse implements IWeixinResponsor {
	
    protected static Log logger = LogFactory.getLog(WeixinSubscribeResponse.class);
    
    private String key = "subscribe";
    private PublicAccountContext pac;

    public final boolean validate(RequestMessage request) {
    	
    	logger.warn("WeixinSubscribeResponse.validate");
    	
        return request instanceof SubscribeEvent;
    }

    public final ResponseMessage response(RequestMessage request){
    	
    	logger.warn("WeixinSubscribeResponse.response");
    	
        String originalId = request.getToUserName();
        String openId = request.getFromUserName();
        
        // 基本信息及粉丝处理
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        pac = PublicAccountManager.getInstance().get(originalId);

        Long sceneId =  eventRequest.getSceneId();//xufangbo new version test

        // 记录用户最近一次关注所扫码的场景ID
        String event = eventRequest.getEvent();
        event = event == null ? "" : event;
        boolean isSubscribeEvent = key.equalsIgnoreCase(event);
        
        IFansService fansService = ServiceFactory.create(IFansService.class);
        Fans fans = fansService.subscribe(openId, pac.getAccount(), sceneId, isSubscribeEvent);
        for (IWeixinSubscriber subscriber : pac.getSubscriberList()) {
            boolean ishint = subscriber.validate(eventRequest, fans, pac.getAccount());
            if (ishint) {
                logger.warn("微信关注事件，命中：" + eventRequest.getEventKey());
                return subscriber.reply(eventRequest, fans, pac.getAccount(),sceneId);
            }else{
            	logger.warn(subscriber.getClass().getName()+":关注未命中");
            }
        }
        
        return null;
    }


    public final String getKey() {
        return key;
    }

    public final void setKey(String value) {
        key = value;
    }
}