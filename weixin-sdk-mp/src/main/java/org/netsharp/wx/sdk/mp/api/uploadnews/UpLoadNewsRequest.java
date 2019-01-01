package org.netsharp.wx.sdk.mp.api.uploadnews;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//[Api("上传图文消息")]
public class UpLoadNewsRequest extends Request<UpLoadNewsResponse>
{

	public UpLoadNewsRequest()
	{
		this.setNews(new java.util.ArrayList<UploadNews>());
	}

	private java.util.ArrayList<UploadNews> privateNews;
	public final java.util.ArrayList<UploadNews> getNews()
	{
		return privateNews;
	}
	public final void setNews(java.util.ArrayList<UploadNews> value)
	{
		privateNews = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (this.getNews().isEmpty())
		{
			return WeixinValidation.create(false, "News.count==0");
		}

		for (UploadNews n : getNews())
		{
			if (StringHelper.isNullOrEmpty(n.getTitle()))
			{
				return WeixinValidation.create(false, "News.Title is null");
			}

			if (StringHelper.isNullOrEmpty(n.getContent()))
			{
				return WeixinValidation.create(false, "News.Content is null");
			}

			if (StringHelper.isNullOrEmpty(n.getThumbMediaId()))
			{
				return WeixinValidation.create(false, "News.ThumbMediaId is null");
			}
		}

		return super.doValidate();
	}

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=%2$s", this.getAccessToken());
	}

	@Override
	protected UpLoadNewsResponse doResponse()
	{
        return null;

//		var jsonData = new { articles = getNews().Select(x => new { thumb_media_id = x.ThumbMediaId, author = x.Author, title = x.Title, content_source_url = x.SourceUrl, content = x.Content, digest = x.Digest, show_cover_pic = x.ShowCoverImage ? "1" : "0" }) };
//
//		return this.HttpPost(this.Serialize(jsonData));
	}
}