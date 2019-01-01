package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PPartType;

@EntityCache(prefix="partype")
public interface IPPartTypeService extends IPersistableService<PPartType> {

}
