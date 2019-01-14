package org.netsharp.organization.service.action.login;

import org.netsharp.organization.entity.AuthorizationConfiguration;
import org.netsharp.util.EncrypUtil;

public class PasswordHelper {
	
	public static String md5(String pwd) {
		if(pwd==null) {
			pwd = AuthorizationConfiguration.getInstance().getDefaultPassword();
		}
		return EncrypUtil.md5( pwd + "user!@#123").substring(8,24);
	}

}
