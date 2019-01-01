package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IRoleWorkbenchService;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.service.PersistableService;

@Service
public class RoleWorkbenchService extends PersistableService<RoleWorkbench> implements IRoleWorkbenchService {

	public RoleWorkbenchService() {
		super();
		this.type = RoleWorkbench.class;
	}

	@Override
	public RoleWorkbench getDefault() {
		
		Oql oql = new Oql();
		{
			oql.setType(RoleWorkbench.class);
			oql.setSelects("id,name,path");
		}
		
		return this.queryFirst(oql);
	}

}
