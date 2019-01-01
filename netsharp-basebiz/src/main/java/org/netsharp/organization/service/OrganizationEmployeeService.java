package org.netsharp.organization.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

@Service
public class OrganizationEmployeeService extends PersistableService<OrganizationEmployee> implements IOrganizationEmployeeService {
	public OrganizationEmployeeService() {
		super();
		this.type = OrganizationEmployee.class;
	}

	@Override
	public Employee getEmpByPostOrgId(Integer orgid) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrganizationEmployee.*,employee.*");
			oql.setFilter(" organizationId =" + orgid);
		}
		OrganizationEmployee oe = this.pm.queryFirst(oql);
		if (oe != null) {
			return oe.getEmployee();
		}

		return null;
	}

	@Override
	public Boolean deleteByOrganizationId(Integer organizationId) {

		Mtable meta = MtableManager.getMtable(type);
		DeleteBuilder builder = DeleteBuilder.getInstance();
		{
			builder.deleteFrom(meta.getTableName());
			builder.where("organization_id=?");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@organizationId", organizationId, Types.INTEGER);
		return this.pm.executeNonQuery(builder.toSQL(), qps) > 0;
	}

	@Override
	public Boolean deleteByEmploeeyId(Integer emploeeyId) {

		Mtable meta = MtableManager.getMtable(type);
		DeleteBuilder builder = DeleteBuilder.getInstance();
		{
			builder.deleteFrom(meta.getTableName());
			builder.where("employee_id=?");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@emploeeyId", emploeeyId, Types.INTEGER);
		return this.pm.executeNonQuery(builder.toSQL(), qps) > 0;
	}
}
