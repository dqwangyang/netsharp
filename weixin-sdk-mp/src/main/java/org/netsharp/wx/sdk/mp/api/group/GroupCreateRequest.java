package org.netsharp.wx.sdk.mp.api.group;

import java.util.HashMap;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("创建用户分组")]
public class GroupCreateRequest extends Request<CreateGroupResponse>
{
	private String privateGroupName;
	public final String getGroupName()
	{
		return privateGroupName;
	}
	public final void setGroupName(String value)
	{
		privateGroupName = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{

		if (StringHelper.isNullOrEmpty(getGroupName()))
		{
			return WeixinValidation.create(false, "GroupName is null");
		}

		return WeixinValidation.create(true, "");
	}

	@Override
	protected CreateGroupResponse doResponse() 
	{
		HashMap<String,Object> group=new HashMap<String,Object>();
		group.put("name", this.getGroupName());
		
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("group", group);

		String json = this.serialize(data);

		return this.executeHttpPost(json);
	}
}