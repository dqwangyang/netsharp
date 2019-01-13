package org.netsharp.panda.session;

import java.io.Serializable;
import java.util.Date;

import org.netsharp.session.NetsharpSession;

public class PandaSession implements Serializable {
	
	private static final long serialVersionUID = -8910280642010239722L;
	
	public PandaSession() {}
	
	public PandaSession(String sessionId,Long userId,String userName,String mobiles,String userImg) {
		
		this.setSession(new NetsharpSession());
		
		this.session.setSessionId(sessionId);
		this.session.setUserId(userId);
		this.session.setUserName(userName);
		this.session.setMobiles(mobiles);
		this.session.setUserImg(userImg);
		this.session.setLoginTime(new Date());
	}
	
	private NetsharpSession session;
	private UserPermission permission;
	private UserClient userClient;
	
	public NetsharpSession getSession() {
		return session;
	}
	public void setSession(NetsharpSession session) {
		this.session = session;
	}
	public UserPermission getPermission() {
		return permission;
	}
	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}
	public UserClient getUserClient() {
		return userClient;
	}
	public void setUserClient(UserClient userClient) {
		this.userClient = userClient;
	}
}
