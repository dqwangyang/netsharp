package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PForm;

@EntityCache(prefix="form")
public interface IPFormService extends IPersistableService<PForm> {
	
	
}
