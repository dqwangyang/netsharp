package org.netsharp.wx.pa.response;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;

/**
 * 微信取消关注,禁止回复消息
 */
public class WeixinUnSubscribeResponse implements IWeixinResponsor {
    private static IFansService service = ServiceFactory.create(IFansService.class);

    private String key = "unsubscribe";

    public final String getKey() {
        return key;
    }

    public final void setKey(String value) {
        key = value;
    }

    public final boolean validate(RequestMessage request) {
        String eventKey = "";
        if (request instanceof EventRequest) {
            EventRequest evt = (EventRequest) request;
            eventKey = evt.getEvent();
        }

        return key.equalsIgnoreCase(eventKey);
    }

    /**
     * 微信取消关注,禁止回复消息（微信放档规定）
     * @param request
     * @return
     * @
     */
    public final ResponseMessage response(RequestMessage request)  {
        PublicAccount publicAccount = PublicAccountManager.getInstance().get(request.getToUserName()).getAccount();
        service.unsubscribe(request.getFromUserName(), publicAccount);

        return null;
    }
}