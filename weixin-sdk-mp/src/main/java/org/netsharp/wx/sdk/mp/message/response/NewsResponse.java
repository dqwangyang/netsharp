package org.netsharp.wx.sdk.mp.message.response;

import java.util.ArrayList;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class NewsResponse extends ResponseMessage
{
	private ArrayList<Article> articles;
	public NewsResponse()
	{
		super("news");
		this.setArticles(new ArrayList<Article>());
	}

	public NewsResponse(RequestMessage message)
	{
		super("news", message);
		this.setArticles(new ArrayList<Article>());
	}
	
	public final ArrayList<Article> getArticles()
	{
		return articles;
	}
	public final void setArticles(ArrayList<Article> value)
	{
		articles = value;
	}

	@Override
	protected String InnerToxml()
	{
		String baseXml = super.InnerToxml();

		String axml = " <ArticleCount>" + getArticles().size() + "</ArticleCount><Articles>";

		for (Article x : getArticles())
		{
			axml += GetArticleXml(x);
		}

		axml += "</Articles>";

		return baseXml + axml;
	}

	private String GetArticleXml(Article ar)
	{
		String xml = 
				"<item>" + "\r\n" 
					+ "<Title><![CDATA["+ar.getTitle()+"]]></Title>" + "\r\n" 
					+ "<Description><![CDATA["+ar.getDescription()+"]]></Description>" + "\r\n" 
					+ "<PicUrl><![CDATA["+ ar.getPicUrl()+"]]></PicUrl>" + "\r\n" 
					+ "<Url><![CDATA["+ar.getUrl()+"]]></Url>" + "\r\n" 
				+ "</item>";

		return xml;

	}

}