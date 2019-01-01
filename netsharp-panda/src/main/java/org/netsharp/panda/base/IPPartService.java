package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PPart;

@EntityCache(prefix="part")
public interface IPPartService extends IPersistableService<PPart> {
	
}
