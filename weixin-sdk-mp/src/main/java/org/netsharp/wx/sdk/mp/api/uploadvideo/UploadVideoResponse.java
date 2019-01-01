package org.netsharp.wx.sdk.mp.api.uploadvideo;

import org.netsharp.wx.sdk.mp.api.Response;

public class UploadVideoResponse extends Response
{
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	private String privateMedia_Id;
	public final String getMedia_Id()
	{
		return privateMedia_Id;
	}
	public final void setMedia_Id(String value)
	{
		privateMedia_Id = value;
	}

	private String privateCreated_at;
	public final String getCreated_at()
	{
		return privateCreated_at;
	}
	public final void setCreated_at(String value)
	{
		privateCreated_at = value;
	}
}