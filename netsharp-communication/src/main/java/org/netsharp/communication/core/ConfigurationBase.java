package org.netsharp.communication.core;

import org.netsharp.application.ApplicationConfiguration;
import org.netsharp.application.ConfigItem;
import org.netsharp.cache.base.dict.CacheType;

public class ConfigurationBase extends ApplicationConfiguration {
	
	@ConfigItem(key="org.netsharp.communication.host",defaultValue="127.0.0.1")
    private String host;
	
	@ConfigItem(key="org.netsharp.communication.port",defaultValue="8082")
    private Integer port=8082;
	
	@ConfigItem(key="org.netsharp.communication.cache",defaultValue="none")
    private CacheType cache=CacheType.none;
    
    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

	public CacheType getCache() {
		return cache;
	}
}
