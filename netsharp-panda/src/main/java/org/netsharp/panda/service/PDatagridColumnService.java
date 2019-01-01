package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPDatagridColumnService;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.service.PersistableService;

@Service
public class PDatagridColumnService extends PersistableService<PDatagridColumn> implements IPDatagridColumnService {

	public PDatagridColumnService() {
		super();
		this.type = PDatagridColumn.class;
	}
}
