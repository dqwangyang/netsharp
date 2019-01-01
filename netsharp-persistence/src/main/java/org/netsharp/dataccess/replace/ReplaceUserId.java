package org.netsharp.dataccess.replace;

import org.netsharp.session.SessionManager;

/*当前登录用户的Id*/
public class ReplaceUserId extends ReplaceBase {
	
	public ReplaceUserId() {
		this.key = "{userId}";
	}

	public String doExecute(String cmdText) {
		
		Integer userId = SessionManager.getUserId();
		
		if (userId == null) {
			return cmdText;
		} else {
			return cmdText.replace(this.key,userId.toString());
		}
	}
}
