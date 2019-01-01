package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//[Api("发送客服消息,语音")]
public class VoiceCustomerMessageRequest extends CustomMessageRequest {
    private String mediaId;

    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "voice");

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("media_id", this.getMediaId());

        data.put("voice", message);

        return data;
//
//		return new { touser = this.getOpenId(), msgtype = "voice", voice = new { media_id = this.getMediaId() } };
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(this.getMediaId())) {
            return WeixinValidation.create(false, "MediaId is null");
        }

        return super.doValidate();
    }


    public final String getMediaId() {
        return mediaId;
    }

    public final void setMediaId(String value) {
        mediaId = value;
    }

}