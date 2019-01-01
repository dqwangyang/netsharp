package org.netsharp.wx.sdk.mp.api.group;

import org.netsharp.wx.sdk.mp.api.Response;

public class GroupQueryResponse extends Response
{
	public GroupQueryResponse()
	{
		this.setGroups(new java.util.ArrayList<GroupResponse>());
	}

	private java.util.ArrayList<GroupResponse> privateGroups;
	public final java.util.ArrayList<GroupResponse> getGroups()
	{
		return privateGroups;
	}
	public final void setGroups(java.util.ArrayList<GroupResponse> value)
	{
		privateGroups = value;
	}
}