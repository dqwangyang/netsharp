//package org.netsharp.authorization;
//
//import org.junit.Test;
//import org.netsharp.base.ICatEntityService;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.dataccess.DataAccessFactory;
//import org.netsharp.dataccess.IDataAccess;
//import org.netsharp.organization.entity.Organization;
//import org.springframework.util.Assert;
//
//public class OrganizationTest {
//
//	@Test
//	public void test() {
//
//		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
//		service.generatePathCode(Organization.class.getName());
//
//		String cmdText = "SELECT pathcode FROM sys_permission_organization "
//				+ " GROUP BY pathcode HAVING COUNT(pathcode)>1";
//
//		IDataAccess dao = DataAccessFactory.create();
//		Integer count = dao.executeInt(cmdText, null);
//
//		Assert.isTrue(count==null || count == 0);
//
//	}
//}
