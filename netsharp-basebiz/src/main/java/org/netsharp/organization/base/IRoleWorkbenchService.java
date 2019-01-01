package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.RoleWorkbench;

public interface IRoleWorkbenchService extends IPersistableService<RoleWorkbench>{

	RoleWorkbench getDefault();
}
