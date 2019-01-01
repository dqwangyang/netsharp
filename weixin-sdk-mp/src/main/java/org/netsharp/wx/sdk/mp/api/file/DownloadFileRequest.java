package org.netsharp.wx.sdk.mp.api.file;

import org.netsharp.wx.sdk.mp.api.Request;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("下载多媒体文件")]
public class DownloadFileRequest extends Request<DownloadFileResponse>
{
	private String privateMedia_id;
	public final String getMedia_id()
	{
		return privateMedia_id;
	}
	public final void setMedia_id(String value)
	{
		privateMedia_id = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%1$s&media_id=%2$s", this.getAccessToken(), this.getMedia_id());
	}


	@Override
	protected DownloadFileResponse doResponse()
	{
		return null;
//
//		HttpWebClient client = new HttpWebClient();
//		try
//		{
//			DownloadFileResponse dr = new DownloadFileResponse();
//
//			byte[] data = client.DownloadData(this.getUrl());
//
//			if (data.length < 50)
//			{
//				String json = client.Encoding.GetString(data);
//
//				dr = this.Deserialize(json);
//			}
//
//
//			dr.setData(data);
//
//			return dr;
//		}
//		finally
//		{
//			client.dispose();
//		}
	}
}