package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.service.PersistableService;

@Service
public class RoleEmployeeService extends PersistableService<RoleEmployee> implements IRoleEmployeeService {

	public RoleEmployeeService() {
		super();
		this.type = RoleEmployee.class;
	}
}
