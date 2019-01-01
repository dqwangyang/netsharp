package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//[Api("发送客服消息,文本")]
public class TextCustomerMessageRequest extends CustomMessageRequest {
    private String content;

    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "text");

        Map<String, String> text = new HashMap<String, String>();
        text.put("content", this.getContent());

        data.put("text", text);

        return data;
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(this.getContent())) {
            return WeixinValidation.create(false, "Content is null");
        }

        return super.doValidate();
    }


    public final String getContent() {
        return content;
    }

    public final void setContent(String value) {
        content = value;
    }

}