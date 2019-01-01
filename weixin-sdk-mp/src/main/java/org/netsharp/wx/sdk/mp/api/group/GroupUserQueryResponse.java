package org.netsharp.wx.sdk.mp.api.group;

import org.netsharp.wx.sdk.mp.api.Response;

public class GroupUserQueryResponse extends Response
{
	private String privateGroupId;
	public final String getGroupId()
	{
		return privateGroupId;
	}
	public final void setGroupId(String value)
	{
		privateGroupId = value;
	}
}