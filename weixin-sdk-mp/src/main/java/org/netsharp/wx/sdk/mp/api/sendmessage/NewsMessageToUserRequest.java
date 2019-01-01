package org.netsharp.wx.sdk.mp.api.sendmessage;

import java.util.HashMap;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送消息到用户.图文")]
public class NewsMessageToUserRequest extends SendMessageToUserRequest
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

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getMediaId()))
		{
			return WeixinValidation.create(false, "MediaId is null");
		}

		return super.doValidate();
	}

	@Override
	protected Object getMessageObject()
	{
		HashMap<String,Object> jsonData=new HashMap<String,Object>();
		jsonData.put("touser", this.getOpenIds());
		
		HashMap<String,Object> mpnews=new HashMap<String,Object>();
		mpnews.put("media_id", this.getMediaId());
		
		jsonData.put("mpnews", mpnews);

		return jsonData;
	}
}