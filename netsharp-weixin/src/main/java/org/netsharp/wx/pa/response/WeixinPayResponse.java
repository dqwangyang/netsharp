package org.netsharp.wx.pa.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.response.Article;
import org.netsharp.wx.sdk.mp.message.response.NewsResponse;

public class WeixinPayResponse implements IWeixinResponsor {
	
	Log logger=LogFactory.getLog(this.getClass().getSimpleName());
	
	boolean pay1=false;
	
	@Override
	public boolean validate(RequestMessage request) {
		return false;
//		if(request instanceof TextRequest){
//			TextRequest tr = (TextRequest)request;
//			if(tr.getContent().equals("支付1")){
//				pay1=true;
//				return true;
//			}
//
//			if(tr.getContent().equals("支付2")){
//				pay1=false;
//				return true;
//			}
//		}
	}

	@Override
	public ResponseMessage response(RequestMessage request)
			 {

		NewsResponse news = new NewsResponse();
		{
			news.setToUserName(request.getFromUserName());
			news.setFromUserName(request.getToUserName());
		}

		Article article = new Article();
		{
			article.setTitle( "微信支付测试");
			article.setDescription("微信支付测试");
			article.setPicUrl( null);
			if(pay1){
				article.setUrl( "http://yikuaixiu.com/weixin/pay/jsapi.jsp");
			}
			else{
				article.setUrl( "http://121.40.86.55/weixin/pay/jsapi.jsp");
			}
			
		}
		news.getArticles().add(article);

		return news;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public void setKey(String value) {
	}
}
