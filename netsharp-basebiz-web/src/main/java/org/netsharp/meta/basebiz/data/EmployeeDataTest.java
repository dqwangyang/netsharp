package org.netsharp.meta.basebiz.data;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;

public class EmployeeDataTest {
	
	@Test
	public void initialize() {
		
		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		
		Oql oql = new Oql();
		int count = service.queryCount(oql);
		if(count>0) {
			return;
		}
		
		
		Employee e = new Employee();{
			e.toNew();
			e.setName("徐芳波");
		    e.setLoginName("xufangbo");
		    e.setMobile("18613804375");
		    e.setDisabled(false);
		    e.setPwd("123456");
		}
		
		e = service.save(e);
		
		
		IOrganizationEmployeeService oeservice = ServiceFactory.create(IOrganizationEmployeeService.class);
		
		OrganizationEmployee oe = new OrganizationEmployee();
		{
			oe.toNew();
			oe.setEmployeeId(e.getId());
			oe.setPostType(PostType.MAINTIME);
		}
		
		IOrganizationService oservice = ServiceFactory.create(IOrganizationService.class);
		
		oql = new Oql();{
			oql.setFilter("organizationType = "+OrganizationType.POST.getValue());
			oql.setSelects("*");
		}
		
		Organization post = oservice.queryFirst(oql);
		oe.setOrganizationId(post.getId());
		
		oeservice.save(oe);
		
		//授权
	}
	
}
