package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送客服消息,音乐")]
public class MusicCustomerMessageRequest extends CustomMessageRequest {
    /**
     * 缩略图的媒体ID
     */
    private String thumbMediaId;
    private String description;
    private String title;
    /**
     * 音乐链接
     */
    private String musicUrl;
    /**
     * 高品质音乐链接，wifi环境优先使用该链接播放音乐
     */
    private String hQMusicUrl;


    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "music");

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("title", this.getTitle());
        message.put("description", this.getDescription());
        message.put("musicurl", this.getMusicUrl());
        message.put("hqmusicurl", this.getHQMusicUrl());
        message.put("thumb_media_id", this.getThumbMediaId());

        data.put("music", message);

        return data;

//		return new { touser = this.getOpenId(), msgtype = "music", music = new { title = this.getTitle(), description = this.getDescription(), musicurl = this.getMusicUrl(), hqmusicurl = this.getHQMusicUrl(), thumb_media_id = this.getThumbMediaId() } };
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(this.getMusicUrl())) {
            return WeixinValidation.create(false, "MusicUrl is null");
        }

        if (StringHelper.isNullOrEmpty(this.getHQMusicUrl())) {
            return WeixinValidation.create(false, "HQMusicUrl is null");
        }

        if (StringHelper.isNullOrEmpty(this.getThumbMediaId())) {
            return WeixinValidation.create(false, "ThumbMediaId");
        }

        return super.doValidate();
    }


    public final String getThumbMediaId() {
        return thumbMediaId;
    }

    public final void setThumbMediaId(String value) {
        thumbMediaId = value;
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

    public final String getMusicUrl() {
        return musicUrl;
    }

    public final void setMusicUrl(String value) {
        musicUrl = value;
    }

    public final String getHQMusicUrl() {
        return hQMusicUrl;
    }

    public final void setHQMusicUrl(String value) {
        hQMusicUrl = value;
    }
}