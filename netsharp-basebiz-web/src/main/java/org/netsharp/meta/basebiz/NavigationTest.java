package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.netsharp.appconfig.Appconfig;
import org.netsharp.attachment.Attachment;
import org.netsharp.log.entity.NLog;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OperationAuthorizationLog;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.pcc.entity.ProvinceCityCounty;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "系统管理";
		this.treePath = "panda/navigation/system";
		this.resourceNode = "basebiz";
	}

	public void createAccodions() {

		this.doCreateAccodions("system", "系统设置", "fa fa-cog fa-fw", 10000);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "system-organization", "组织机构", "");
		{
			createPTreeNode(tree, "system-organization", null, "Employee", "员工管理", "/system/employee/list");
			createPTreeNode(tree, "system-organization", null, "Organization", "组织机构", "/system/organization/tree");
			createPTreeNode(tree, "system-organization", null, OrganizationFunction.class.getSimpleName(), "业务类型","/system/organizationfunction/list");
			createPTreeNode(tree, "system-organization", null, "Position", "职务管理", "/system/position/list");
			createPTreeNode(tree, "system-organization", null, Employee.class.getSimpleName() + "-online", "在线列表", "/system/employee/online/list");
		}

		createPTreeNode(tree, null,null, "system-authorization", "权限管理", "");
		{
			createPTreeNode(tree, "system-authorization", null, "OrganizationEmployee", "岗位授权", "/system/authorization/tree");
			createPTreeNode(tree, "system-authorization", null, RoleGroup.class.getSimpleName(), "角色分组", "/system/rolegroup/list");
			createPTreeNode(tree, "system-authorization", null, Role.class.getSimpleName(), "角色管理", "/system/role/list");
			createPTreeNode(tree, "system-authorization", null, RoleWorkbench.class.getSimpleName(), "工作台配置", "/system/workbench/list");
			createPTreeNode(tree, "system-authorization", null, "ResourcePosition", "权限查看", "/system/resource/position/tree");
			createPTreeNode(tree, "system-authorization", null, OperationAuthorizationLog.class.getSimpleName(), "授权日志","/system/authorization/log/list");
		}

		createPTreeNode(tree, null, null, "system-options", "系统选项", "");
		{
			createPTreeNode(tree, "system-options", null, Appconfig.class.getSimpleName(), "系统选项", "/system/appconfig/list");
			createPTreeNode(tree, "system-options", null, NLog.class.getSimpleName(), "操作日志", "/system/nlog/list");
			createPTreeNode(tree, "system-options", null, Attachment.class.getSimpleName(), "文件管理", "/system/attachment/list");
			createPTreeNode(tree, "system-options", null, ProvinceCityCounty.class.getSimpleName(), "省市区", "/system/pcc/list");
		}
	}
}
