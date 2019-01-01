//package org.netsharp.authorization;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.netsharp.base.ICatEntityService;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.organization.base.IOperationTypeService;
//import org.netsharp.organization.controller.authorization.PermissionManager;
//import org.netsharp.organization.controller.authorization.UserPermission;
//import org.netsharp.organization.dic.OperationTypes;
//import org.netsharp.organization.dic.OrganizationType;
//import org.netsharp.organization.dic.PrincipalType;
//import org.netsharp.organization.entity.AuthorizationPrincipal;
//import org.netsharp.organization.entity.Employee;
//import org.netsharp.organization.entity.FieldGeteway;
//import org.netsharp.organization.entity.Operation;
//import org.netsharp.organization.entity.OperationType;
//import org.netsharp.organization.entity.Organization;
//import org.netsharp.organization.entity.OrganizationEmployee;
//import org.netsharp.organization.entity.Position;
//import org.netsharp.panda.core.HttpContext;
//import org.netsharp.panda.core.comunication.IRequest;
//import org.netsharp.panda.core.comunication.TestRequest;
//import org.netsharp.resourcenode.entity.ResourceNode;
//import org.netsharp.resourcenode.IResourceNodeService;
//import org.springframework.util.Assert;
//
//public class PermissionTest {
//
//	@Before
//	public void setup() {
//
//		Employee employee = new Employee();
//		{
//			employee.setId(1L);
//			employee.setName("test");
//			employee.setPwd("123456");
//		}
//
//		AuthorizationPrincipal authorizationPrincipal = new AuthorizationPrincipal();
//		authorizationPrincipal.setPrincipalId(1L);
//		authorizationPrincipal.setPrincipalType(PrincipalType.Post);
//		// 创建资源节点 职位
//		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
//		ResourceNode node = resourceService.byEntityId(Position.class.getName());
//
//		// 创建一个组织，创建一个岗位
//		Position p = new Position();
//		{
//			p.setId(5L);
//			p.setCode("005");
//			p.setName("员工");
//			p.setAuthorizationPrincipal(authorizationPrincipal);
//		}
//		
//		// 创建组织
//		Organization o = new Organization();
//		{
//			o.setId(4L);
//			o.setPosition(p);
//			o.setCode("4000");
//			o.setPathCode("101020");
//			o.setName("员工");
//			o.setOrganizationType(OrganizationType.Post);
//		}
//		
//		// 组织员工关联
//		OrganizationEmployee oe = new OrganizationEmployee();
//		{
//			oe.setId(1L);
//			oe.setEmployee(employee);
//			oe.setOrganization(o);
//		}
//		
//
//		// 操作类型
//		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
//		OperationType operationType = operationTypeService.byCode(OperationTypes.delete);
//		operationType.setResourceNode(node);
//
//		OperationType operationType1 = operationTypeService.byCode(OperationTypes.view);
//		operationType1.setResourceNode(node);
//
//		// 操作类型
//		Operation op = new Operation();
//		{
//			op.setId(1L);
//			op.setCode("10");
//			op.setFieldGeteway(true);
//			op.setOperationType(operationType);
//			op.setResourceNode(node);
//		}
//		
//		// 操作
//		Operation operation1 = new Operation();
//		{
//			operation1.setId(2L);
//			operation1.setCode("20");
//			operation1.setFieldGeteway(true);
//			operation1.setOperationType(operationType1);
//			operation1.setResourceNode(node);
//		}
//		// 字段
//		FieldGeteway fieldGeteway = new FieldGeteway();
//		fieldGeteway.setOperation(op);
//		fieldGeteway.setEntityId(Position.class.getName());
//		// 用户权限
//		UserPermission up = new UserPermission();
//		{
//			up.setEmployee(employee);
//			List<Operation> operations = new ArrayList<Operation>();
//			operations.add(op);
//			operations.add(operation1);
//			up.setOperations(operations);
//			Map<String, List<String>> fieldGeteways = new HashMap<String, List<String>>();
//			List<String> fields = new ArrayList<String>();
//			fields.add("createTime");
//			fieldGeteways.put(Position.class.getName(), fields);
//			up.setFieldGeteways(fieldGeteways);
//			up.setPermission(true);
//		}
//		// Session
//		Map<String, String> pars = new HashMap<String, String>();
//		HttpContext ctx = new HttpContext();
//		{
//			ctx.setRequest(new TestRequest(pars));
//			HttpContext.setCurrent(ctx);
//		}
//
//		IRequest request = HttpContext.getCurrent().getRequest();
//
//		request.setSession("panda_user", up);
//		
//		// 生成组织结构的pathcode和pathName
//		ICatEntityService catService = ServiceFactory.create(ICatEntityService.class);
//		catService.generatePathCode(Organization.class.getName());
//
//	}
//
//	@Test
//	public void operation1() {
//
//		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
//
//		ResourceNode node = resourceService.byEntityId(Position.class.getName());
//		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
//		OperationType ot = operationTypeService.byCode(OperationTypes.view);
//
//		boolean isPermission = PermissionManager.isPermission(node, ot);
//		Assert.isTrue(isPermission);
//
//		ot = operationTypeService.byCode(OperationTypes.operation);
//		isPermission = PermissionManager.isPermission(node, ot);
//		Assert.isTrue(!isPermission);
//	}
//
//	@Test
//	public void operation2() {
//
//		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
//
//		ResourceNode node = resourceService.byEntityId(Position.class.getName());
//		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
//		OperationType ot1 = operationTypeService.byCode(OperationTypes.delete);
//		OperationType ot2 = operationTypeService.byCode(OperationTypes.view);
//		boolean isPermission = PermissionManager.isPermission(node, ot1, ot2);
//		Assert.isTrue(isPermission);
//
//		//
//		ot1 = operationTypeService.byCode(OperationTypes.excel);
//		ot2 = operationTypeService.byCode(OperationTypes.add);
//		isPermission = PermissionManager.isPermission(node, ot1, ot2);
//
//		Assert.isTrue(!isPermission);
//	}
//
//	@Test
//	public void operation3() {
//
//		Object operationId = 1;
//
//		boolean isPermission = PermissionManager.isPermission(operationId);
//		Assert.isTrue(isPermission);
//
//		//
//		operationId = 3;
//		isPermission = PermissionManager.isPermission(operationId);
//
//		Assert.isTrue(!isPermission);
//	}
//
//	@Test
//	public void fieldGeteway() {
//		
//		String entityId = Position.class.getName();
//		String propertyName = "createTime";
//		boolean isFieldGetway = PermissionManager.isFieldGetway(entityId, propertyName);
//		Assert.isTrue(isFieldGetway);
//
//		entityId = Position.class.getName();
//		propertyName = "name";
//		isFieldGetway = PermissionManager.isFieldGetway(entityId, propertyName);
//		Assert.isTrue(!isFieldGetway);
//		// pathName可能为多级属性，如entityId:org.netsharp.test.SalesOrder,propertyName:customer.phoneNumber,实际上控制的是客户的手机号权限
//		// isFieldGetway(String entityId, String propertyName)
//		// 从session中取用户字段权限信息
//		// 给定一个字段路径
//		// 验证用户该字段的权限路径中是否存在此路径
//		
//	}
//}
