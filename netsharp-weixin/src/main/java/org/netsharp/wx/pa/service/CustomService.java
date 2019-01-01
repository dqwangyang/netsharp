package org.netsharp.wx.pa.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.Service;
import org.netsharp.util.JsonManage;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.api.customessage.CustomMessageRequest;
import org.netsharp.wx.sdk.mp.api.customessage.TextCustomerMessageRequest;
import org.netsharp.wx.sdk.mp.api.customservice.GetOnlineKfListRequest;
import org.netsharp.wx.sdk.mp.api.customservice.GetOnlineKfListResponse;
import org.netsharp.wx.sdk.mp.api.customservice.OnlineKfInfo;

@Service
public class CustomService implements ICustomService {
	
	private static Log logger = LogFactory.getLog(CustomService.class);
	
    @Override
    public List<OnlineKfInfo> getOnlineKfInfo(String originalId) {
    	
    	PublicAccount pa = PublicAccountManager.getInstance().get(originalId).getAccount();
    	
        GetOnlineKfListRequest request = new GetOnlineKfListRequest();
        AccessToken            at      = AccessTokenManage.getTokenByAppId(pa.getAppId());
        request.setTokenInfo(at);

        GetOnlineKfListResponse response = request.getResponse();

        List<OnlineKfInfo> onlines = response.getKf_online_list();
        if (onlines == null) {
            onlines = new ArrayList<OnlineKfInfo>();
        }

        return onlines;
    }

    @Override
    public int getOnlineKfNums(String originalId) {
        return getOnlineKfInfo(originalId).size();
    }
    
    @Override
    public Response sendMessge(CustomMessageRequest messageRequest) {
        Response resp = null;
        try {
            resp = messageRequest.getResponse();
            logger.info(String.format("给用户[%s]发送消息的响应：[%s]", messageRequest.getOpenId(), JsonManage.serialize2(resp)));
        } catch (Exception ex) {
            logger.error(String.format("给用户[%s]发送消息失败：%s", messageRequest.getOpenId(), ex.getMessage()));
        }

        return resp;
    }

    @Override
    public Response sendTextMessage(String content, String openId,String originalId) {
    	
    	PublicAccount pa = PublicAccountManager.getInstance().get(originalId).getAccount();
    	
        AccessToken at = AccessTokenManage.getTokenByAppId(pa.getAppId());

        TextCustomerMessageRequest request = new TextCustomerMessageRequest();
        request.setContent(content);
        request.setOpenId(openId);

        request.setTokenInfo(at);

        return sendMessge(request);
    }
}
