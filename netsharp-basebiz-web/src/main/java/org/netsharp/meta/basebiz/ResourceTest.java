package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.netsharp.appconfig.Appconfig;
import org.netsharp.appconfig.IAppconfigService;
import org.netsharp.attachment.Attachment;
import org.netsharp.attachment.IAttachmentService;
import org.netsharp.log.base.INLogService;
import org.netsharp.log.entity.NLog;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOperationAuthorizationLogService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationFunctionService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.base.IPositionService;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.base.IRoleWorkbenchService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OperationAuthorizationLog;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.organization.entity.Position;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.pcc.base.IProvinceCityCountyService;
import org.netsharp.pcc.entity.ProvinceCityCounty;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "系统管理";
		parentNodeCode = "basebiz";
		pluginName = "系统管理";
		seq = 100;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("组织机构", "system-organization", node.getId());{

			createResourceNodeVoucher(OrganizationFunction.class.getName(), "业务类型", OrganizationFunction.class.getSimpleName(), IOrganizationFunctionService.class.getName(), node1.getId());
			createResourceNodeVoucher(Position.class.getName(), "职务管理", Position.class.getSimpleName(), IPositionService.class.getName(), node1.getId());
			createResourceNodeVoucher(Organization.class.getName(), "组织机构", Organization.class.getSimpleName(), IOrganizationService.class.getName(), node1.getId());
			createResourceNodeVoucher(Employee.class.getName(), "员工管理", Employee.class.getSimpleName(), IEmployeeService.class.getName(), node1.getId());
			createResourceNodeVoucher(Employee.class.getName(), "在线列表", Employee.class.getSimpleName() + "-online", IEmployeeService.class.getName(), node1.getId());
			
		}
		
		node1 = this.createResourceNodeCategory("权限管理", "system-authorization", node.getId());{

			createResourceNodeVoucher(OrganizationEmployee.class.getName(), "岗位授权", "OrganizationEmployee", IOrganizationEmployeeService.class.getName(), node1.getId());
			createResourceNodeVoucher(RoleGroup.class.getName(), "角色分组", RoleGroup.class.getSimpleName(), IRoleGroupService.class.getName(), node1.getId());
			createResourceNodeVoucher(Role.class.getName(), "角色管理",  Role.class.getSimpleName(), IRoleService.class.getName(), node1.getId());
			createResourceNodeVoucher(RoleWorkbench.class.getName(), "工作台配置",  RoleWorkbench.class.getSimpleName(), IRoleWorkbenchService.class.getName(), node1.getId());
			createResourceNodeVoucher(ResourceNode.class.getName(), "权限查看", "ResourcePosition", IResourceNodeService.class.getName(), node1.getId());
			createResourceNodeVoucher(OperationAuthorizationLog.class.getName(), "授权日志",OperationAuthorizationLog.class.getSimpleName(), IOperationAuthorizationLogService.class.getName(), node1.getId());
		
			createResourceNodeVoucher(RoleEmployee.class.getName(), "角色员工", "RoleEmployee", IRoleEmployeeService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("系统选项", "system-options", node.getId());{
			
			createResourceNodeVoucher(Appconfig.class.getName(), "系统选项", Appconfig.class.getSimpleName(), IAppconfigService.class.getName(), node1.getId());
			createResourceNodeVoucher(NLog.class.getName(), "操作日志", NLog.class.getSimpleName(), INLogService.class.getName(), node1.getId());
			createResourceNodeVoucher(Attachment.class.getName(), "文件管理", Attachment.class.getSimpleName(), IAttachmentService.class.getName(), node1.getId());
			createResourceNodeVoucher(ProvinceCityCounty.class.getName(), "省市区", ProvinceCityCounty.class.getSimpleName(), IProvinceCityCountyService.class.getName(), node1.getId());
			createResourceNodeVoucher(Employee.class.getName(), "修改密码", "ChangePassword", IEmployeeService.class.getName(), node1.getId());
		}

	}
}
