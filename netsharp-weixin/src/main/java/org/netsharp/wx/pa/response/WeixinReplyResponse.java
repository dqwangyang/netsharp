package org.netsharp.wx.pa.response;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.WeixinUrl;
import org.netsharp.wx.pa.entity.NArticle;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.message.response.Article;
import org.netsharp.wx.sdk.mp.message.response.NewsResponse;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;

public class WeixinReplyResponse implements IWeixinResponsor {

	private final ThreadLocal<NReply> localReply = new ThreadLocal<NReply>();
//	private static Log logger = LogFactory.getLog(WeixinReplyResponse.class.getSimpleName());

	public final boolean validate(RequestMessage request) {
		return getReply(request) != null;
	}

	private NReply getReply(RequestMessage request) {
		
		NReply reply = null;
		PublicAccountManager pam = PublicAccountManager.getInstance();
		String origianlId = request.getToUserName();
		
		if (request instanceof TextRequest) {
			
			TextRequest text = (TextRequest) request;
			reply = pam.getReply(text.getContent(), origianlId);
			
		} else if (request instanceof EventRequest) {
			
			EventRequest evt = (EventRequest) request;
			reply = pam.getReply(evt.getEventKey(), origianlId);
			
		}
		
		localReply.set(reply);
		return reply;
	}

	public final ResponseMessage response(RequestMessage request){
		NReply reply = localReply.get();
		return this.response(reply, request);
	}

	public final ResponseMessage response(NReply reply, RequestMessage request){
		if (reply instanceof NTextReply) {
			return this.responseText(request, reply);
		} else if (reply instanceof NGraphicReply) {
			return this.responseGraphics(request, reply);
		} else {
			throw new NetsharpException("不支持的回复类型：" + reply.getClass().getName());
		}
	}

	private ResponseMessage responseText(RequestMessage request, NReply reply) {

		NTextReply textReply = (NTextReply) reply;

		TextResponse message = new TextResponse();
		{
			message.setToUserName(request.getFromUserName());
			message.setFromUserName(request.getToUserName());
			message.setContent(textReply.getContent());
		}

		return message;
	}

	private ResponseMessage responseGraphics(RequestMessage request, NReply reply){
		NGraphicReply textReply = (NGraphicReply) reply;
		
		String openId = request.getFromUserName();
		String originalId = request.getToUserName();

		NewsResponse news = new NewsResponse();
		{
			news.setToUserName(openId);
			news.setFromUserName(originalId);
		}

		for (NArticle warticle : textReply.getArticles()) {
			
			String url = WeixinUrl.getFullUrl(warticle.getUrl(),originalId,openId);
			String image = WeixinUrl.getFullUrl(warticle.getImageUrl(),originalId,openId);

			Article article = new Article();
			{
				article.setTitle(warticle.getHeader());
				article.setDescription(warticle.getDescription());
				article.setPicUrl(image);
				article.setUrl(url);
			}
			news.getArticles().add(article);
		}

		return news;
	}

	public final String getKey() {
		return "";
	}

	public final void setKey(String value) {
		//
	}
}