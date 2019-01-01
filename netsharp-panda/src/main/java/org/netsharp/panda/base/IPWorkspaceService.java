package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.annotation.Cachekey;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PWorkspace;

@EntityCache(prefix="workbench",property="url")
public interface IPWorkspaceService extends IPersistableService<PWorkspace> {
	
	@Cacheable(prefix="workbench")
	PWorkspace byUrl(@Cachekey String url);
	
	int removeByResourceCode(String code);
	int queryCount();
	
}
