package org.netsharp.wx.sdk.ep.user;

import java.util.List;

import org.netsharp.wx.sdk.ep.Response;

public class UserListResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7220166552687171119L;
	private List<User> userlist;

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
}
