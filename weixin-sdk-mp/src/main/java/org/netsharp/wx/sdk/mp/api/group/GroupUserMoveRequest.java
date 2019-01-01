package org.netsharp.wx.sdk.mp.api.group;

import java.util.HashMap;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("移动用户分组")]
public class GroupUserMoveRequest extends Request<Response>
{

	private String privateOpenId;
	public final String getOpenId()
	{
		return privateOpenId;
	}
	public final void setOpenId(String value)
	{
		privateOpenId = value;
	}

	private String privateToGroupId;
	public final String getToGroupId()
	{
		return privateToGroupId;
	}
	public final void setToGroupId(String value)
	{
		privateToGroupId = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getOpenId()))
		{
			return WeixinValidation.create(false, "OpenId is null");
		}

		if (StringHelper.isNullOrEmpty(this.getToGroupId()))
		{
			return WeixinValidation.create(false, "ToGroupId is null");
		}

		return super.doValidate();
	}

	@Override
	protected Response doResponse() 
	{
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("openid",  this.getOpenId());
		data.put("to_groupid",  this.getToGroupId());

		String json = this.serialize(data);

		return this.executeHttpPost(json);

	}
}