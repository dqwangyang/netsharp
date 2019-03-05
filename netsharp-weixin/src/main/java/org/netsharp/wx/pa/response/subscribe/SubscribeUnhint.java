package org.netsharp.wx.pa.response.subscribe;

import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;

public class SubscribeUnhint implements IWeixinSubscriber {

    public boolean validate(EventRequest request,Fans fans,PublicAccount publicAccount,Long sceneId){
        return true;
    }

    public ResponseMessage reply(EventRequest request,Fans fans,PublicAccount publicAccount,Long sceneId){
        TextResponse message = new TextResponse();
        message.setToUserName(request.getFromUserName());
        message.setFromUserName( request.getToUserName());

        String content = "亲，您终于来了，" + publicAccount.getName() + "欢迎你!";

        message.setContent(content);

        return message;
    }

}
