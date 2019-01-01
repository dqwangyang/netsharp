package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.annotation.Cachekey;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PReference;

@EntityCache(prefix="reference")
public interface IPReferenceService extends IPersistableService<PReference> {
	
	@Cacheable(prefix="ref-code")
	PReference byCode(@Cachekey String code);
	
}
