package org.netsharp.wx.sdk.mp.api.group;

import org.netsharp.wx.sdk.mp.api.Response;

public class CreateGroupResponse extends Response
{
	private GroupResponse privateGroup;
	public final GroupResponse getGroup()
	{
		return privateGroup;
	}
	public final void setGroup(GroupResponse value)
	{
		privateGroup = value;
	}
}