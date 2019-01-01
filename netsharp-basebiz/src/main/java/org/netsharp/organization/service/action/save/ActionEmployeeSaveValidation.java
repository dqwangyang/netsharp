package org.netsharp.organization.service.action.save;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

public class ActionEmployeeSaveValidation implements IAction {
	
	private Employee entity;
	private IPersister<Employee> pm = PersisterFactory.create();

	@Override
	public void execute(ActionContext ctx) {
		
		entity = (Employee)ctx.getItem();
		
		this.valdiateMobiles();
		this.validateName();
	}
	
	public void validateName() {
		
		List<String> filters = new ArrayList<String>();
		QueryParameters qps = new QueryParameters();

		filters.add("name=?");
		qps.add("name", entity.getName(), Types.VARCHAR);

		if (entity.getId() != null) {
			filters.add("id != ?");
			qps.add("id", entity.getId(), Types.INTEGER);
		}

		String cmdText = "select count(0) from " + MtableManager.getMtable(Employee.class).getTableName() + " where " + StringManager.join(" and ", filters);

		int count = pm.executeInt(cmdText, qps);

		if (count > 0) {
			throw new BusinessException("同名员工["+entity.getName()+"]已经存在!");
		}
	}
	
	public void valdiateMobiles(){
		
		List<String> filters = new ArrayList<String>();
		QueryParameters qps = new QueryParameters();
		
		if(StringManager.isNullOrEmpty(entity.getMobile())){
			throw new BusinessException("新增员工时手机号必须录入");
		}
		
		if(entity.getMobile().charAt(0)!='1') {
			throw new BusinessException("手机格式不正确");
		}
		
		if(entity.getMobile().length()!=11) {
			throw new BusinessException("手机格式不正确");
		}

		filters.add("mobile=?");
		qps.add("mobile", entity.getMobile(), Types.VARCHAR);

		if (entity.getId() != null) {
			filters.add("id != ?");
			qps.add("id", entity.getId(), Types.INTEGER);
		}

		String cmdText = "select count(0) from " + MtableManager.getMtable(Employee.class).getTableName() + " where " + StringManager.join(" and ", filters);

		int count = pm.executeInt(cmdText, qps);

		if (count > 0) {
			throw new BusinessException("手机号码["+entity.getMobile()+"]已经存在!");
		}
	}

}
