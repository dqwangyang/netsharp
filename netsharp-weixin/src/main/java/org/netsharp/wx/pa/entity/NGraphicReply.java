package org.netsharp.wx.pa.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

@Table(name="wx_pa_reply_graphic",header="图文回复")
public class NGraphicReply extends NReply {
	
	private static final long serialVersionUID = -7219633724183409869L;
	
	@Subs(foreignKey="replyId",subType=NArticle.class)
    private List<NArticle> articles;

	public List<NArticle> getArticles() {
		if(this.articles==null){
			this.articles=new ArrayList<NArticle>();
		}
		return articles;
	}

	public void setArticles(List<NArticle> articles) {
		this.articles = articles;
	}
}
