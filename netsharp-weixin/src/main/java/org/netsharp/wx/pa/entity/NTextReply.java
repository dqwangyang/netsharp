package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="wx_pa_reply_text",header="文本回复")
public class NTextReply extends NReply {
	
	private static final long serialVersionUID = 7884406914351678912L;
	@Column(size=1000)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
