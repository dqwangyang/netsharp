package org.netsharp.wx.sdk.ep.user;

import java.util.List;

import org.netsharp.wx.sdk.ep.Response;

public class UserSimpleListResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6107890169307638243L;
	private List<UserSimple> userlist;

	public List<UserSimple> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserSimple> userlist) {
		this.userlist = userlist;
	}
}

