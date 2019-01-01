package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.OrganizationFunction;

public interface IOrganizationFunctionService extends IPersistableService<OrganizationFunction> {

	OrganizationFunction byCode(String code);
}
