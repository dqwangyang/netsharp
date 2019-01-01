package org.netsharp.wx.sdk.mp.pay;

import java.util.Date;

import org.netsharp.util.Encodings;
import org.netsharp.wx.sdk.mp.WeixinException;

public class PayOrder {

    private String body;    // 订单描述 包括商品等信息
    private String attach;  //
    private String mch_id;  // 微信商户号
    private String code;    // 订单编号
    private double amount = 0d; // 订单金额
    private Date   startTime;
    private Date   endTime;
    private String notify_Url;  //支付回调url;
    private String clientIp;
    private String openId;      //粉丝openid;
    private String appId;
    private String paySignKey;  //微信商户api 密钥
    private String trade_type; //
    private String encoding = Encodings.UTF8;

    public final String getBody() {
        return body;
    }

    public final void setBody(String value) {
        body = value;
    }

    public final String getAttach() {
        return attach;
    }

    public final void setAttach(String value) {
        attach = value;
    }

    public final String getMch_id() {
        return mch_id;
    }

    public final void setMch_id(String value) {
        mch_id = value;
    }

    public final String getCode() {
        return code;
    }

    public final void setCode(String value) {
        code = value;
    }

    public final double getAmount() {
        return amount;
    }

    public final void setAmount(double value) {
        amount = value;
    }

    public final String getTotalFee() {

        if (body != null && body.length() > 127) {
            throw new WeixinException("body不能超过127个字符！");
        }

        if (this.getAmount() <= 0) {
            throw new WeixinException("金额超出合法范围");
        }

        // 当精确到分时，需要进行四舍五入处理
        Integer feeint = (int) Math.round(this.amount * 100);

        return feeint.toString();
    }


    public final String getEncoding() {
        return encoding;
    }

    public final void setEncoding(String value) {
        encoding = value;
    }

    public final Date getStartTime() {
        return startTime;
    }

    public final void setStartTime(Date value) {
        startTime = value;
    }

    public final Date getEndTime() {
        return endTime;
    }

    public final void setEndTime(Date value) {
        endTime = value;
    }

    public final String getNotify_Url() {
        return notify_Url;
    }

    public final void setNotify_Url(String value) {
        notify_Url = value;
    }

    public final String getClientIp() {
        return clientIp;
    }

    public final void setClientIp(String value) {
        clientIp = value;
    }

    public final String getOpenId() {
        return openId;
    }

    public final void setOpenId(String value) {
        openId = value;
    }

    public final String getAppId() {
        return appId;
    }

    public final void setAppId(String value) {
        appId = value;
    }

    public final String getPaySignKey() {
        return paySignKey;
    }

    public final void setPaySignKey(String value) {
        paySignKey = value;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}