package org.netsharp;

import java.util.List;

import org.junit.Test;
import org.netsharp.core.Oql;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

public class EmployeeTest {
	
	IPersister<Employee> pm = PersisterFactory.create();
	
	@Test
	public void disableInvalidMobiles() {
		
		String sql="update sys_permission_employee set disabled=1 where disabled=0 and length(mobile) !=11";
				
		pm.executeNonQuery(sql, null);
	}
	
	@Test
	public void disableuplicateNames() {
		
		//手机号不是11位的员工停用
		
		String sql="update sys_permission_employee\n" + 
				"set disabled=1\n" + 
				" where disabled=0 and name in (select name from \n" + 
				"(select name from sys_permission_employee  \n" + 
				"where disabled=0\n" + 
				"group by name having count(0)>1 ) a) ";
				
		pm.executeNonQuery(sql, null);
	}
	
	@Test
	public void disableTest() {
		
		String sql="update sys_permission_employee set disabled=1 where disabled=0 and (name like '%test%' or name like '%测试%')";
		
		pm.executeNonQuery(sql, null);
	}
	
	@Test
	public void disableLenth6() {
		
		String sql="update sys_permission_employee set disabled=1 where disabled=0 and length(name)<6";
		
		pm.executeNonQuery(sql, null);
	}
	
	@Test
	public void duplicateNames2() {
		
		Oql oql = new Oql();{
			oql.setType(Employee.class);
			oql.setSelects("id,name");
			oql.setFilter("disabled=0");
		}
		
		List<Employee> list = pm.queryList(oql);
		
		for(Employee emp : list) {
			this.doDuplicateName(emp);
		}
		
	}
	
	private void doDuplicateName(Employee emp) {
		
		
		Oql oql = new Oql();{
			oql.setType(Employee.class);
			oql.setSelects("id,name");
			oql.setFilter("disabled=0 and name like '%"+emp.getName()+"%'");
		}
		
		List<Employee> list = pm.queryList(oql);
		
		if(list.size()<=1) {
			return;
		}
		
		System.out.println("-----------------------------------");
		for(Employee e : list) {
			
			System.out.println(e.getId()+" | "+e.getName());
		}
	}
}
