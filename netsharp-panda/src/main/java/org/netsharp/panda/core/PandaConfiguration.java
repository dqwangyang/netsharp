package org.netsharp.panda.core;

import org.netsharp.application.ApplicationConfiguration;
import org.netsharp.application.Configuration;
import org.netsharp.application.ConfigItem;
import org.netsharp.cache.base.dict.CacheType;

@Configuration(file = "conf/configuration.properties")
public class PandaConfiguration extends ApplicationConfiguration {
	
	private static PandaConfiguration instance;
	
	/*panda是否启用权限控制*/
	@ConfigItem(key="org.netsharp.panda.ispermission",defaultValue="true")
	private Boolean isPermission;
	
	/*panda的session缓存类型*/
	@ConfigItem(key="org.netsharp.panda.session.cache",defaultValue="none")
	private CacheType sessionCacheType;
	
	//panda的session时间（分钟）
	@ConfigItem(key="org.netsharp.panda.session.timeout",defaultValue="30")
	private Integer sessionTimtout;
	
	
	public static PandaConfiguration getInstance() {
		
		if(instance==null) {
			instance = new PandaConfiguration();
			instance.deserialize();
		}
		
		return instance;
		
	}


	public Boolean getIsPermission() {
		return isPermission;
	}

	public CacheType getSessionCacheType() {
		return sessionCacheType;
	}


	public Integer getSessionTimtout() {
		return sessionTimtout;
	}
}
