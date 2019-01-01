package org.netsharp.wx.sdk.mp.api.sendmessage;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送消息到用户组.文本")]
public class TextMessageToGroupRequest extends SendMessageToGroupRequest
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
	protected Object GetMessageObject()
	{
		return null;
//		var jsonData = new { filter = new { group_id = this.getGroupId() }, text = new { content = this.getContent() }, msgtype = "text" };
//
//		return jsonData;
	}
}