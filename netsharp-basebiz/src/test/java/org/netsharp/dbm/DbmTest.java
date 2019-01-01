//package org.netsharp.dbm;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.netsharp.dbm.entity.DbmResult;
//import org.netsharp.dbm.web.DbmController;
//import org.netsharp.organization.entity.Employee;
//import org.netsharp.util.Result;
//import org.netsharp.util.Result.Type;
//
//public class DbmTest {
//	
//	DbmController controller = new DbmController();
//	
//	@Test
//	public void commonSelect() {
//		
//		String cmdText="select * from sys_permission_employee";
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.info);
//		
//		DbmResult dbm = (DbmResult)result.data;
//		Assert.isTrue( dbm.getItems().size()<=100);
//	}
//	
//	@Test
//	public void commonError() {
//		
//		String cmdText="select * from sys_permission_employee32434345";
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.error);
//		Assert.isTrue(result.message!=null);
//	}
//	
//	@Test
//	public void commonSelectCount() {
//		
//		String cmdText="select count(0) from sys_permission_employee";
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.info);
//	}
//	
//	@Test
//	public void commonOql() {
//		
//		String cmdText="select * from " + Employee.class.getName();
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.info);
//		
//		DbmResult dbm = (DbmResult)result.data;
//		Assert.isTrue( dbm.getItems().size()<=100);
//	}
//	
//	@Test
//	public void commonOqlFilter() {
//		
//		String cmdText="select Employee.* from " + Employee.class.getName() + " where Employee.mobile = '{crpt18613804375!crpt}'";
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.info);
//		
//		DbmResult dbm = (DbmResult)result.data;
//		Assert.isTrue( dbm.getItems().size()<=100);
//	}
//	
//	@Test
//	public void commonOqlFilter2() {
//		String cmdText="select Employee.*,organizations.*,organizations.organization.* from " + Employee.class.getName() + " where  organizations.organization.id > 10 and Employee.mobile = '{crpt18613804375!crpt}'";
//		Result<Object> result = controller.execute(cmdText);
//		
//		Assert.isTrue(result.type == Type.info);
//		
//		DbmResult dbm = (DbmResult)result.data;
//		Assert.isTrue( dbm.getItems().size()<=100);
//	}
//}
