package org.netsharp.wx.sdk.ep.chat;

public class Chat {
	
	private Integer chatid;      // 会话id。字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z, 如果值内容为64bit无符号整型：要求值范围在[1, 2^63)之间，[2^63, 2^64)为系统分配会话id区间
	private String name;         // 会话标题
	private String owner;        // 管理员userid，必须是该会话userlist的成员之一
	private String[] userlist;   // 会话成员列表，成员用userid来标识。会话成员必须在3人或以上，1000人以下
	
	public Integer getChatid() {
		return chatid;
	}
	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String[] getUserlist() {
		return userlist;
	}
	public void setUserlist(String[] userlist) {
		this.userlist = userlist;
	}
}
