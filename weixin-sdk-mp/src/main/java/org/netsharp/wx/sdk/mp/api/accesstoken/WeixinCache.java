package org.netsharp.wx.sdk.mp.api.accesstoken;

import java.util.concurrent.locks.ReentrantLock;

import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.wx.sdk.mp.api.jssdk.JsApiTicketResponse;

public class WeixinCache {
	
    private static WeixinCache instance = new WeixinCache();
    private static ICacheCommand cacheCommand = null;
    private ReentrantLock lock = new ReentrantLock();

    private WeixinCache() {

    }

    public static WeixinCache getInstance() {
        return instance;
    }

    public AccessToken getToken(String appId) {
        AccessToken token   = null;
        String      realKey = WeixinCacheKey.accessToken + appId;
        cacheCommand = getCacheCommand();
        if (cacheCommand.exists(realKey)) {
            token = (AccessToken) cacheCommand.get(realKey);
        }
        return token;
    }

    public void setToken(String appId, AccessToken token) {
        String realKey = WeixinCacheKey.accessToken + appId;
        getCacheCommand().set(realKey, token);
    }

    public JsApiTicketResponse getJsApiTicket(String appId) {
        String realKey = WeixinCacheKey.jsApiTicket + appId;
        if (getCacheCommand().exists(realKey)) {
            return (JsApiTicketResponse) getCacheCommand().get(realKey);
        }
        return null;
    }

    public void setJsApiTicket(String appId, JsApiTicketResponse ticketResponse) {
        String realKey = WeixinCacheKey.jsApiTicket + appId;
        getCacheCommand().set(realKey, ticketResponse);
    }

    public void clearToken(String appId) {
        String realKey = WeixinCacheKey.accessToken + appId;
        if (getCacheCommand().exists(realKey)) {
            getCacheCommand().del(realKey);
        }
    }

    private ICacheCommand getCacheCommand() {
        if (cacheCommand != null) {
            return cacheCommand;
        }
        try {
            lock.lock();
            if (cacheCommand != null) {
                return cacheCommand;
            }
            cacheCommand = CacheCommandFactory.create(CacheType.db,"session");
        } finally {
            lock.unlock();
        }
        return cacheCommand;
    }
    
    public class WeixinCacheKey {
    	
        public static final String separator = ":";

        // 微信
        public final static String accessToken = "weixin.token" + separator;
        public final static String jsApiTicket = "weixin.ticket" + separator;

        // APP
        public final static String appToken = "weixin.app.token" + separator;
    }
}
