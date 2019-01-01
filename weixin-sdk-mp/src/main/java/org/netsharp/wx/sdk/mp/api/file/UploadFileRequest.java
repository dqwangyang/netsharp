package org.netsharp.wx.sdk.mp.api.file;

import java.io.File;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("上传多媒体文件")]
public class UploadFileRequest extends Request<UploadFileResponse>
{
	private String privateFilePath;
	public final String getFilePath()
	{
		return privateFilePath;
	}
	public final void setFilePath(String value)
	{
		privateFilePath = value;
	}

	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%1$s&type=%2$s", this.getAccessToken(), this.getType());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(getFilePath()))
		{
			return WeixinValidation.create(false, "filePath is null");
		}

		
		if (!new File(this.getFilePath()).exists())
		{
			return WeixinValidation.create(false, "filepath no exists");
		}

		if (!this.getType().equals(FileTypes.Image) && !this.getType().equals(FileTypes.Voice) && !this.getType().equals(FileTypes.Video) && !this.getType().equals(FileTypes.Thumb))
		{
			return WeixinValidation.create(false, "not support file type" + this.getType());
		}

		return super.doValidate();
	}

	@Override
	protected UploadFileResponse doResponse()
	{
return null;
//		HttpWebClient client = new HttpWebClient();
//		try
//		{
//			byte[] data = client.UploadFile(this.getUrl(), this.getFilePath());
//
//			String json = client.Encoding.GetString(data);
//
//			return this.Deserialize(json);
//		}
//		finally
//		{
//			client.dispose();
//		}
	}
}