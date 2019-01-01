package org.netsharp.panda.session;

import java.io.Serializable;

public class UserClient implements Serializable {

	private static final long serialVersionUID = 5798098687514465301L;
	
	private String ip;
	private String userAgent;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
