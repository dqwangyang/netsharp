package org.netsharp.organization.service.action.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.service.EmployeeService;
import org.netsharp.util.ReflectManager;

public class ActionEmployeeSavePersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Employee entity = (Employee) ctx.getItem();
		Class<?> superType = EmployeeService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Employee> service = (IPersistableService<Employee>) ReflectManager
				.newInstance(superType);

		entity = service.save(entity);

		ctx.setItem(entity);
	}
}
