package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.panda.entity.PDatagrid;

@EntityCache(prefix="datagrid")
public interface IPDatagridService extends IPersistableService<PDatagrid> {

	
}
