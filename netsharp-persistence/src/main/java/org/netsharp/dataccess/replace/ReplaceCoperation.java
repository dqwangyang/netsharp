package org.netsharp.dataccess.replace;

import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

/*当前登录用户的所有部门*/
public class ReplaceCoperation  extends ReplaceBase {
	
	public ReplaceCoperation() {
		this.key = "{cId}";
	}
	
	public String doExecute(String cmdText) {
	
		Integer cid = SessionManager.getTenancyId();
		if (StringManager.isNullOrEmpty(cid.toString())) {
			return cmdText;
		} else {
			return cmdText.replace(this.key, cid.toString());
		}
	}
}
