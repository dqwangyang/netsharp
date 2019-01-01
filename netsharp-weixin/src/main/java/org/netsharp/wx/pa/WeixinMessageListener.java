package org.netsharp.wx.pa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.message.IMessageListener;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class WeixinMessageListener implements IMessageListener {

    private static Log logger = LogFactory.getLog(WeixinMessageListener.class.getSimpleName());

    /*处理请求，得到回复*/
    public ResponseMessage processRequest(RequestMessage request)  {

        List<IWeixinResponsor> responseors = ResponserManager.getResponseors();

        for (IWeixinResponsor responsor : responseors) {
            if (responsor.validate(request)) {
                logger.debug("responsor 命中：" + responsor.getClass().getName());

                ResponserManager.addlog(request, true);

                ResponseMessage responseMessage = responsor.response(request);
                return responseMessage;
            }
        }
        throw new WeixinException("没有匹配的处理器处理微信请求！");
    }

    /*得到公众号的Token*/
    public String getToken(String oid) {
        PublicAccount wcp = PublicAccountManager.getInstance().get(oid).getAccount();

        return wcp.getToken();
    }

    public AccessToken getAccessToken(String oid) {
        PublicAccount wcp = PublicAccountManager.getInstance().get(oid).getAccount();

        AccessToken act = new AccessToken();
        {
            act.setId(wcp.getId());
            act.setAppId(wcp.getAppId());
            act.setAppSecret(wcp.getAppSecret());
            act.setEncryptionKey(wcp.getEncryptionKey());
        }

        return act;
    }
}