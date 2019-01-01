package org.netsharp.wx.sdk.mp.api.uploadvideo;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.uploadnews.UpLoadNewsResponse;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//[Api("上传视频 （用于群发视频消息")]
public class UploadVideoRequest extends Request<UpLoadNewsResponse>
{
	private String privateMediaId;
	public final String getMediaId()
	{
		return privateMediaId;
	}
	public final void setMediaId(String value)
	{
		privateMediaId = value;
	}

	private String privateTitle;
	public final String getTitle()
	{
		return privateTitle;
	}
	public final void setTitle(String value)
	{
		privateTitle = value;
	}

	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getMediaId()))
		{
			WeixinValidation.create(false, "Mediaid is null");
		}

		if (StringHelper.isNullOrEmpty(this.getTitle()))
		{
			return WeixinValidation.create(false, "Title is null");
		}

		return super.doValidate();
	}

	@Override
	protected UpLoadNewsResponse doResponse()
	{
        return null;
//        
//		var jsonobject = new { media_id = this.getMediaId(), title = this.getTitle(), description = this.getDescription() };
//
//		return this.HttpPost(this.Serialize(jsonobject));
	}
}