package org.netsharp.cache.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_cache_plugin", header="缓存管理")
public class CachePlugin extends Entity {

	private static final long serialVersionUID = -2784552841169473837L;
	
	@Column(name="full_key", header="缓存key", size=1000)
	private String fullKey;
	
	public String getFullKey() {
		return fullKey;
	}

	public void setFullKey(String fullKey) {
		this.fullKey = fullKey;
	}

}
