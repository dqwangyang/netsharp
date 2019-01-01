package org.netsharp.wx.sdk.mp.api.user;

import java.util.ArrayList;

import org.netsharp.wx.sdk.mp.api.Response;

public class UserListResponse extends Response
{
	private int total;
	private int count;
	private OpenIdCollection data;
	private String next_openid;
	
	public final int getTotal()
	{
		return total;
	}
	public final void setTotal(int value)
	{
		total = value;
	}
	
	public final int getCount()
	{
		return count;
	}
	public final void setCount(int value)
	{
		count = value;
	}
	
	public final OpenIdCollection getData()
	{
		return data;
	}
	public final void setData(OpenIdCollection value)
	{
		data = value;
	}
	
	public final String getNext_openid()
	{
		return next_openid;
	}
	public final void setNext_openid(String value)
	{
		next_openid = value;
	}

	public static class OpenIdCollection
	{
		public OpenIdCollection()
		{
			setOpenId(new ArrayList<String>());
		}
		private ArrayList<String> privateOpenId;
		public final ArrayList<String> getOpenId()
		{
			return privateOpenId;
		}
		public final void setOpenId(ArrayList<String> value)
		{
			privateOpenId = value;
		}
	}
}