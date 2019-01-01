package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.service.PersistableService;

@Service
public class RoleGroupService  extends PersistableService<RoleGroup> implements IRoleGroupService{

	public RoleGroupService() {
		super();
		this.type = RoleGroup.class;
	}

}
