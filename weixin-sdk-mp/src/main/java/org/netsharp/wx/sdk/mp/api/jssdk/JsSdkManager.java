package org.netsharp.wx.sdk.mp.api.jssdk;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.PaConfiguration;
import org.netsharp.wx.sdk.mp.sdk.AesException;
import org.netsharp.wx.sdk.mp.sdk.SHA1;

/**
 * @author kxh
 */
public class JsSdkManager {
    private static Log logger = LogFactory.getLog(JsSdkManager.class);

    /**
     * 获取 jssdk config
     *
     * @param url
     * @return 格式：json
     * @throws AesException
     * @throws IOException
     */
    public static String getJsonConfig(String url,String originalId) throws AesException, IOException {
        JsSdkConfig config = getConfig(url,originalId);
        return JsonManage.serialize(config);
    }

    /**
     * 获取 jssdk config
     *
     * @param url
     * @return
     * @throws AesException
     */
    public static JsSdkConfig getConfig(String url,String originalId) throws AesException {
    	
    	PAccount pa = PaConfiguration.get(originalId);
    	
        JsSdkConfig config = new JsSdkConfig();
        config.setAppId(pa.getApp_id());
        config.setDebug(false);
        config.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
        // 微信JSAPI签名要求的时间戳，必须以秒为单位
        config.setTimestamp(System.currentTimeMillis() / 1000 + "");

        config.setSignature(getJsApiSignature(config, url));
        return config;
    }

    /**
     * @param config
     * @param url
     * @return
     * @throws AesException
     */
    private static String getJsApiSignature(JsSdkConfig config, String url) throws AesException {
        String jsapiTicket = getJsApiTicket(config.getAppId());

        StringBuilder signStr = new StringBuilder();
        signStr.append("jsapi_ticket=").append(jsapiTicket);
        signStr.append("&noncestr=").append(config.getNonceStr());
        signStr.append("&timestamp=").append(config.getTimestamp());
        signStr.append("&url=").append(url);

        logger.info("JSSDK签名参数：" + signStr.toString());
        if (StringUtils.isEmpty(url)) {
            logger.error("=============由于参与JSAPI签名的参数url为空，将会导致微信JSAPI无法正常运行！==========");
        }

        String[] signParams = new String[]{signStr.toString()};

        return SHA1.getSHA1(signParams);
    }

//    private static ReentrantLock lock = new ReentrantLock();

    /**
     * @return
     */
    private static String getJsApiTicket(String appId) {
    	
    	 throw new NetsharpException("not implements");
    	 
//        WeixinCache         wxCache        = WeixinCache.getInstance();
//        JsApiTicketResponse ticketResponse = wxCache.getJsApiTicket(appId);
//        if (ticketResponse != null && !ticketResponse.isExpired()) {
//            return ticketResponse.getTicket();
//        }
//
//        try {
//            lock.lock();
//
//            ticketResponse = wxCache.getJsApiTicket(appId);
//            if (ticketResponse != null && !ticketResponse.isExpired()) {
//                return ticketResponse.getTicket();
//            }
//            AccessToken token = AccessTokenManage.getTokenByAppId(appId);
//
//            JsApiTicketRequest ticketRequest = new JsApiTicketRequest();
//            ticketRequest.setTokenInfo(token);
//            JsApiTicketResponse response = ticketRequest.getResponse();
//
//            String ticket = response.getTicket();
//
//            logger.info("从微信取到的ticket: " + ticket);
//
//            // 记录获取到ticket的时间
//            response.setCreateTime(new Date());
//
//            // 写入缓存
//            wxCache.setJsApiTicket(appId, response);
//
//            return ticket;
//        } finally {
//            lock.unlock();
//        }
    }
}
