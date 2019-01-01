package org.netsharp.wx.pa.entity;


public abstract class NReply  extends WeixinEntity {
	
	private static final long serialVersionUID = -5309478609533291007L;
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}