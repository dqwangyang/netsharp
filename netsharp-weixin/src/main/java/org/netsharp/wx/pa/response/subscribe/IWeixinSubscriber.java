package org.netsharp.wx.pa.response.subscribe;

import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;

public interface IWeixinSubscriber {

    boolean validate(EventRequest request,Fans fans,PublicAccount publicAccount,Long sceneId);

    ResponseMessage reply(EventRequest request,Fans fans,PublicAccount publicAccount,Long sceneId);

}
