package org.netsharp.wx.sdk.mp.api.sendmessage;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送消息到用户.文本")]
public class TextMessageToUserRequest extends SendMessageToUserRequest
{
	private String privateContent;
	public final String getContent()
	{
		return privateContent;
	}
	public final void setContent(String value)
	{
		privateContent = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getContent()))
		{
			return WeixinValidation.create(false, "Content is null");
		}

		return super.doValidate();
	}

	@Override
	protected Object getMessageObject()
	{
		return null;
//		
//		var jsonData = new { touser = this.getOpenIds(), text = new { content = this.getContent() }, msgtype = "text" };
//
//		return jsonData;
	}
}