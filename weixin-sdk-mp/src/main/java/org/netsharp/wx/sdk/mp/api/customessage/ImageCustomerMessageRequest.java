package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送客服消息,图片")]
public class ImageCustomerMessageRequest extends CustomMessageRequest {
    private String mediaId;

    public final String getMediaId() {
        return mediaId;
    }

    public final void setMediaId(String value) {
        mediaId = value;
    }

    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "image");

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("media_id", this.getMediaId());

        data.put("image", message);

        return data;
//
//		return new { touser = getOpenId(), msgtype = "image", image = new { media_id = this.getMediaId() } };
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(this.getMediaId())) {
            return WeixinValidation.create(false, "MediaId is null");
        }

        return super.doValidate();
    }
}