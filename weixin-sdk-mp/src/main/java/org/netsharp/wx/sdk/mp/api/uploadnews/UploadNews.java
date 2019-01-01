package org.netsharp.wx.sdk.mp.api.uploadnews;

public class UploadNews
{
	//[Required]
	private String privateThumbMediaId;
	public final String getThumbMediaId()
	{
		return privateThumbMediaId;
	}
	public final void setThumbMediaId(String value)
	{
		privateThumbMediaId = value;
	}

	//[Required]
	private String privateTitle;
	public final String getTitle()
	{
		return privateTitle;
	}
	public final void setTitle(String value)
	{
		privateTitle = value;
	}

	private String privateAuthor;
	public final String getAuthor()
	{
		return privateAuthor;
	}
	public final void setAuthor(String value)
	{
		privateAuthor = value;
	}

	//[Required]
	private String privateContent;
	public final String getContent()
	{
		return privateContent;
	}
	public final void setContent(String value)
	{
		privateContent = value;
	}

	private String privateSourceUrl;
	public final String getSourceUrl()
	{
		return privateSourceUrl;
	}
	public final void setSourceUrl(String value)
	{
		privateSourceUrl = value;
	}

	private String privateDigest;
	public final String getDigest()
	{
		return privateDigest;
	}
	public final void setDigest(String value)
	{
		privateDigest = value;
	}

	/** 
	 正文显示封面图片
	 
	*/
	private boolean privateShowCoverImage;
	public final boolean getShowCoverImage()
	{
		return privateShowCoverImage;
	}
	public final void setShowCoverImage(boolean value)
	{
		privateShowCoverImage = value;
	}
}