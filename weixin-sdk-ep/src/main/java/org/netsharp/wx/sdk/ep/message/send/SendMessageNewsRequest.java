package org.netsharp.wx.sdk.ep.message.send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendMessageNewsRequest extends SendMessageRequest {
	
	private List<Artical> articles = new ArrayList<Artical>();
	
	@Override
	protected Object getInnerObject(){
		
		this.setMsgtype(MessageType.news);
		
		Map<String,Object> item  = new HashMap<String,Object>();
		{
			item.put("articles", this.articles);
		}
		
		return item;
	}

	public List<Artical> getArticles() {
		return articles;
	}

	public void setArticles(List<Artical> articles) {
		this.articles = articles;
	}
}
