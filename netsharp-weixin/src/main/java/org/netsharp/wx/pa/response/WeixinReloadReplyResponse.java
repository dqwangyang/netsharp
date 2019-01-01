package org.netsharp.wx.pa.response;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;

public class WeixinReloadReplyResponse implements IWeixinResponsor {
    public final String getKey() {
        return "weixinreload";
    }

    public final void setKey(String value) {
    }

    public final boolean validate(RequestMessage request) {
        if (request instanceof TextRequest) {
            TextRequest rt = (TextRequest) request;

            return this.getKey().equalsIgnoreCase(rt.getContent());
        }

        return false;
    }

    public final ResponseMessage response(RequestMessage request){
    	
        String originalId = request.getToUserName();
        PublicAccountManager.getInstance().reload(originalId);
        PublicAccount pa = PublicAccountManager.getInstance().get(originalId).getAccount();
        
        // 清空token
        AccessTokenManage.clearTokenCache(pa.getAppId());
        
    	String name = "管理员";
    	
    	IFansService fansService = ServiceFactory.create(IFansService.class);
    	Fans fans = fansService.byOpenId(request.getFromUserName());
    	if(fans !=null && !StringManager.isNullOrEmpty(fans.getNickname())){
    		name=fans.getNickname();
    	}

        TextResponse message = new TextResponse();
        {
            message.setToUserName(request.getFromUserName());
            message.setFromUserName(request.getToUserName());
            message.setContent(name+"您好，微信配置刷新成功！");
        }

        return message;
    }
}