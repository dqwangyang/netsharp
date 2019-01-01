package org.netsharp.wx.sdk.mp.api.customessage;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public abstract class CustomMessageRequest extends Request<Response> {
    private String openId;

    @Override
    public String getUrl() {
        return String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%1$s", this.getAccessToken());
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(getOpenId())) {
            return WeixinValidation.create(false, "Openid is null");
        }

        return super.doValidate();
    }

    @Override
    protected Response doResponse()  {
        Object o = GetMessage();

        String json = this.serialize(o);

        return this.executeHttpPost(json);
    }

    protected abstract Object GetMessage();


    public final String getOpenId() {
        return openId;
    }

    public final void setOpenId(String value) {
        openId = value;
    }
}