package org.netsharp.meta.basebiz.data;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.organization.service.action.login.PasswordHelper;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeDataTest {
	
	@Test
	public void initialize() {
		
		IPersister<Employee> pm = PersisterFactory.create();
		
		int count = pm.queryCount(new Oql().setType(Employee.class));
		if(count>0) {
			return;
		}
		
		// Employee
		Employee e = new Employee();{
			e.toNew();
			e.setName("netsharp");
		    e.setLoginName("netsharp");
		    e.setMobile("186****4375");
		    e.setDisabled(false);
		    e.setPwd(PasswordHelper.md5("123456"));
		}
		pm.save(e);
				
		//query post
		IOrganizationService oservice = ServiceFactory.create(IOrganizationService.class);		
		Oql oql = new Oql();{
			oql.setFilter("organizationType = "+OrganizationType.POST.getValue());
			oql.setSelects("*");
		}
		Organization post = oservice.queryFirst(oql);
		
		//OrganizationEmployee
		IOrganizationEmployeeService oeservice = ServiceFactory.create(IOrganizationEmployeeService.class);		
		OrganizationEmployee oe = new OrganizationEmployee();
		{
			oe.toNew();
			oe.setEmployeeId(e.getId());
			oe.setPostType(PostType.MAINTIME);
			oe.setOrganizationId(post.getId());
		}
		oeservice.save(oe);
		
		//AuthorizationPrincipal
		AuthorizationPrincipal ap = new AuthorizationPrincipal();{
			ap.toNew();
			ap.setPrincipalType(PrincipalType.POST);
			ap.setPrincipalId(post.getId());
			ap.setPrincipalName(post.getName());
		}
		
		IAuthorizationPrincipalService aps = ServiceFactory.create(IAuthorizationPrincipalService.class);
		aps.save(ap);
		
		//授权
		String operaitonTable = MtableManager.getMtable(Operation.class).getTableName();
		String principalOperationTable = MtableManager.getMtable(PrincipalOperation.class).getTableName();
		String resourceNodeTable = MtableManager.getMtable(ResourceNode.class).getTableName();
		String cmdText="insert into "+principalOperationTable + " (id,principal_id,operation_id,operation_code,resourcenode_code)"
				+ " select operaiton.id,"+ap.getId()+",operaiton.id,operaiton.code,resourcenode.code from "+operaitonTable+" operaiton "
						+ "left join "+resourceNodeTable+" resourcenode on operaiton.resource_node_id = resourcenode.id";
		
		pm.executeNonQuery(cmdText, null);
	}
	
}
