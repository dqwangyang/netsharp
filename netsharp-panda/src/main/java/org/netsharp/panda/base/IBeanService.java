package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.plugin.bean.Bean;

@EntityCache(prefix="bean")
public interface IBeanService extends IPersistableService<Bean> {
	
}
