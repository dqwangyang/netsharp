package org.netsharp.dataccess.replace;

import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

/*当前登录用户的所有部门*/
public class ReplaceDepartment extends ReplaceBase {
	
	public ReplaceDepartment() {
		this.key = "{departments}";
	}
	
	public String doExecute(String cmdText) {
		
		String departments = SessionManager.getDepartmentIds();
		if (StringManager.isNullOrEmpty(departments)) {
			return cmdText;
		} else {
			return cmdText.replace(this.key, departments);
		}
	}

}
