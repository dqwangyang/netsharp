package org.netsharp.wx.sdk.mp.mch.redpack;

import org.netsharp.wx.sdk.mp.mch.MchResponse;

/**
 * @author kxh
 */
public class RedPacketResponse extends MchResponse {

    private String mch_billno;
    private String wxappid;
    private String re_openid;
    private String total_amount;


    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getRe_openid() {
        return re_openid;
    }

    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}
