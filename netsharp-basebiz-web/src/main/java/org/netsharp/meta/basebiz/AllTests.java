package org.netsharp.meta.basebiz;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.meta.basebiz.authorization.AuthorizationWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.ModifyPasswordWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.OperationAuthorizationLogWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.ResourcePositionWorkspace;
import org.netsharp.meta.basebiz.authorization.RoleAuthorizationWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.RoleGroupWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.RoleWorkbenchWorkspaceTest;
import org.netsharp.meta.basebiz.authorization.RoleWorkspaceTest;
import org.netsharp.meta.basebiz.data.EmployeeDataTest;
import org.netsharp.meta.basebiz.data.OrganizationDataTest;
import org.netsharp.meta.basebiz.organization.AppconfigTest;
import org.netsharp.meta.basebiz.organization.EmployeeActionTest;
import org.netsharp.meta.basebiz.organization.EmployeeOnLineWorkspaceTest;
import org.netsharp.meta.basebiz.organization.EmployeeWorkspaceTest;
import org.netsharp.meta.basebiz.organization.OrganizationActionTest;
import org.netsharp.meta.basebiz.organization.OrganizationFunctionWorkspaceTest;
import org.netsharp.meta.basebiz.organization.OrganizationWorkspaceTest;
import org.netsharp.meta.basebiz.organization.PositionWorkspaceTest;
import org.netsharp.meta.basebiz.ref.EmployeeReferenceTest;
import org.netsharp.meta.basebiz.ref.PositionFilterReferneceTest;
import org.netsharp.meta.basebiz.ref.PositionReferneceTest;
import org.netsharp.meta.basebiz.ref.PostFilterReferenceTest;
import org.netsharp.meta.basebiz.ref.PostNameFilterReferenceTest;
import org.netsharp.meta.basebiz.ref.PostReferenceTest;
import org.netsharp.meta.basebiz.ref.RoleGroupReferenceTest;
import org.netsharp.meta.basebiz.ref.RoleReferenceTest;
import org.netsharp.meta.basebiz.ref.RoleWorkbenchReferenceTest;
import org.netsharp.meta.basebiz.ref.organization.OrganizationFilterReferenceTest;
import org.netsharp.meta.basebiz.ref.organization.OrganizationFunctionReferenceTest;
import org.netsharp.meta.basebiz.ref.organization.OrganizationReferenceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		AppconfigTest.class,
		OrganizationActionTest.class,
		EmployeeActionTest.class,
		
		PositionReferneceTest.class, 
		PositionFilterReferneceTest.class,		
		EmployeeReferenceTest.class,
		OrganizationFunctionReferenceTest.class,
		PostReferenceTest.class, 
		PostNameFilterReferenceTest.class,
		PostFilterReferenceTest.class,
		OrganizationReferenceTest.class,
		OrganizationFilterReferenceTest.class,
		RoleGroupReferenceTest.class,
		RoleReferenceTest.class,
		RoleWorkbenchReferenceTest.class,
		
		
		PositionWorkspaceTest.class,
		ModifyPasswordWorkspaceTest.class,
		OrganizationWorkspaceTest.class, 
		AuthorizationWorkspaceTest.class, 
		OperationAuthorizationLogWorkspaceTest.class,
		ResourcePositionWorkspace.class,
		EmployeeWorkspaceTest.class, 
		EmployeeOnLineWorkspaceTest.class,
		NLogWorkspaceTest.class,
		AppconfigWorkspaceTest.class,
		OrganizationFunctionWorkspaceTest.class,
		AttachmentWorkspaceTest.class,
		ProvinceCityCountyreegridWorkspaceTest.class,
		
		RoleGroupWorkspaceTest.class,
		RoleWorkspaceTest.class,
		RoleAuthorizationWorkspaceTest.class,
		RoleWorkbenchWorkspaceTest.class,
		
		OrganizationDataTest.class,
//		EmployeeDataTest.class,
		
		NavigationTest.class
		})
		
public class AllTests {

}
