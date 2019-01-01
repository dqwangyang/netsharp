package org.netsharp.plugin.base;

import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.annotation.Cachekey;
import org.netsharp.plugin.entity.Pathable;

public interface IPathServcie {
	
	@Cacheable(prefix="path")
	Pathable byPath(@Cachekey String path,@Cachekey String typeName);
}
