package org.netsharp.organization.entity;

import org.netsharp.application.ApplicationConfiguration;
import org.netsharp.application.Configuration;
import org.netsharp.application.ConfigItem;

@Configuration(file = "conf/configuration.properties")
public class AuthorizationConfiguration extends ApplicationConfiguration {
	
	private static AuthorizationConfiguration instance;
	private AuthorizationConfiguration() {
		
	}
	
	// 系统默认密码
	@ConfigItem(key="org.netsharp.authorization.defaultpassword",defaultValue="234wer@#$")
	private String defaultPassword ;
	
	//用户密码加密盐
	@ConfigItem(key="org.netsharp.authorization.salt",defaultValue="user!@#123")
	private String salt;
	
	
	public static AuthorizationConfiguration getInstance() {
		
		if(instance==null) {
			instance = new AuthorizationConfiguration();
			instance.deserialize();
		}
		
		return instance;
	}


	public String getDefaultPassword() {
		return defaultPassword;
	}


	public String getSalt() {
		return salt;
	}
}