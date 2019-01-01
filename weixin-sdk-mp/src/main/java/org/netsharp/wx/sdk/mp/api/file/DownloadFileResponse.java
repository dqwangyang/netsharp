package org.netsharp.wx.sdk.mp.api.file;

import org.netsharp.wx.sdk.mp.api.Response;

public class DownloadFileResponse extends Response
{
	private byte[] privateData;
	public final byte[] getData()
	{
		return privateData;
	}
	public final void setData(byte[] value)
	{
		privateData = value;
	}
}