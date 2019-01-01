package org.netsharp.wx.sdk.tp.bean.response.component;


import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class CreatePreAuthCodeResponse extends BaseResponse {
    private String preAuthCode;
    private Integer expiresIn;

    public String getPreAuthCode() {
        return preAuthCode;
    }

    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "CreatePreAuthCodeResponse{"
                + "preAuthCode='" + preAuthCode + '\''
                + ", expiresIn=" + expiresIn
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
