package org.netsharp.wx.sdk.mp.mch;

import org.netsharp.wx.sdk.mp.pay.AppPay;
import org.netsharp.wx.sdk.mp.pay.JsPay;
import org.netsharp.wx.sdk.mp.pay.PayHelp;

public class PrePayResponse extends MchResponse {
    private String trade_type;
    private String prepay_id;

    /*生成PrePay之后可以通过此方法得到Jsapi调用的json*/
    public JsPay getJsPay(String partnerKey) {

        JsPay jspay = new JsPay();
        {
            jspay.setAppId(this.getAppid());
            jspay.setTimeStamp(PayHelp.getTs());
            jspay.setNonceStr(PayHelp.getNonceString());
            jspay.setPackage("prepay_id=" + this.prepay_id);
            jspay.setSignType("MD5"); // MD5  // SHA1
        }

        String paySign = PayHelp.getMd5Sign(jspay, partnerKey);

        jspay.setPaySign(paySign);

        return jspay;
    }

    /**
     * 微信支付参数，用于app调用微信支付
     *
     * @return
     */
    public AppPay getAppPayParams(String partnerKey) {
        AppPay config = new AppPay();

        config.setAppId(this.getAppid());
        config.setTimeStamp(PayHelp.getTs());
        config.setNonceStr(PayHelp.getNonceString());
        config.setPackage("Sign=WXPay");
        config.setPrepayId(this.getPrePayId());
        config.setPartnerId(getMch_id()); // 商户ID

        // 计算参数签名
        String sign = PayHelp.getMd5Sign(config, partnerKey);

        config.setSign(sign);

        return config;
    }

    public final String getTradeType() {
        return trade_type;
    }

    public final void setTradeType(String value) {
        trade_type = value;
    }

    public final String getPrePayId() {
        return prepay_id;
    }

    public final void setPrePayId(String value) {
        prepay_id = value;
    }

}