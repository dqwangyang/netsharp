package org.netsharp.wx.sdk.tp.api.pay.sdk;

import java.io.InputStream;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 微信平台支付配置 - (第三方平台)
 * @date 2018/7/5 20:17
 */
public class WxPlatformPayConfig extends WXPayConfig{

    private String appId;
    private String mchId;
    private String key;

    public WxPlatformPayConfig(String appId, String mchId, String key) {
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
    }

    @Override
    String getAppID() {
        return this.appId;
    }

    @Override
    String getMchID() {
        return this.mchId;
    }

    @Override
    String getKey() {
        return this.key;
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainImpl.instance();
    }

}
