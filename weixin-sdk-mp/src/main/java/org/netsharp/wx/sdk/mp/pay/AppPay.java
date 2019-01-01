package org.netsharp.wx.sdk.mp.pay;

import java.util.TreeMap;

/**
 * app支付参数
 */
public class AppPay extends TreeMap<String, String> {

    private static final long serialVersionUID = 1L;

    public String getAppId() {
        return get("appid");
    }

    public void setAppId(String appId) {
        put("appid", appId);
    }

    public String getTimeStamp() {
        return get("timestamp");
    }

    public void setTimeStamp(String timeStamp) {
        put("timestamp", timeStamp);
    }

    public String getNonceStr() {
        return get("noncestr");
    }

    public void setNonceStr(String nonceStr) {
        put("noncestr", nonceStr);
    }

    public String getPartnerId() {
        return get("partnerid");
    }

    public void setPartnerId(String partnerId) {
        put("partnerid", partnerId);
    }

    public String getSign() {
        return get("sign");
    }

    public void setSign(String sign) {
        put("sign", sign);
    }

    public String getPackage() {
        return get("package");
    }

    public void setPackage(String p) {
        put("package", p);
    }

    public String getPrepayId() {
        return get("prepayid");
    }

    public void setPrepayId(String prepayId) {
        put("prepayid", prepayId);
    }
}