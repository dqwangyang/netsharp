package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送客服消息,视频")]
public class VideoCustomerMessageRequest extends CustomMessageRequest {
    private String mediaId;
    private String description;
    private String title;

    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "video");

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("media_id", this.getMediaId());
        message.put("thumb_media_id", this.getMediaId());
        message.put("title", this.getTitle());
        message.put("description", this.getDescription());

        data.put("video", message);

        return data;
//
//		return new { touser = this.getOpenId(), msgtype = "video", 
//				video = new { media_id = this.getMediaId(), description = this.getDescription(), title = this.getTitle() } };
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


    public final String getDescription() {
        return description;
    }

    public final void setDescription(String value) {
        description = value;
    }


    public final String getTitle() {
        return title;
    }

    public final void setTitle(String value) {
        title = value;
    }

}