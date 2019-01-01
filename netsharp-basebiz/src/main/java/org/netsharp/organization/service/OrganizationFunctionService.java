package org.netsharp.organization.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOrganizationFunctionService;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.service.PersistableService;

@Service
public class OrganizationFunctionService extends PersistableService<OrganizationFunction> implements IOrganizationFunctionService {
	public OrganizationFunctionService() {
		super();
		this.type = OrganizationFunction.class;
	}

	@Override
	public OrganizationFunction save(OrganizationFunction entity) {

		super.save(entity);
		this.byCode(entity.getCode());
		return entity;
	}

	@Override
	public OrganizationFunction byCode(String code) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,name,code");
			oql.setFilter("code='" + code + "'");
		}
		List<OrganizationFunction> organizationFunctions = this.pm.queryList(oql);
		if (organizationFunctions.size() > 1) {
			throw new BusinessException("编码重复!" + code);
		}
		if (organizationFunctions.size() == 1) {
			return organizationFunctions.get(0);
		}
		return null;
	}
}
