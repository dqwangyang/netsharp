 package org.netsharp.wx.sdk.mp.api.group;

import org.netsharp.wx.sdk.mp.api.Response;

public class GroupResponse extends Response
{
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	private int privateCount;
	public final int getCount()
	{
		return privateCount;
	}
	public final void setCount(int value)
	{
		privateCount = value;
	}
}