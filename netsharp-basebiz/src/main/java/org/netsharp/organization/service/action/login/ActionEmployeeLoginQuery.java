package org.netsharp.organization.service.action.login;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.Oql;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

public class ActionEmployeeLoginQuery implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		
		IPersister<Employee> pm = PersisterFactory.create();
		String loginName=(String)ctx.getStatus().get("loginName");
		String pwd=(String)ctx.getStatus().get("pwd");
		
		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("Employee.*,organizations.*,organizations.organization.*");
			oql.setFilter(" loginName=? and pwd=? and disabled !=1");
			oql.getParameters().add("@loginName", loginName, Types.VARCHAR);
			oql.getParameters().add("@pwd", pwd, Types.VARCHAR);
		}

		Employee employee = pm.queryFirst(oql);
		ctx.setItem(employee);
	}

}
