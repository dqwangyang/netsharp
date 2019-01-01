package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PQueryProject;

@EntityCache(prefix="queryproject")
public interface IPQueryProjectService extends IPersistableService<PQueryProject> {

	
	
}
