package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.controller.EmployeeOnlineListPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeOnLineWorkspaceTest extends WorkspaceCreationBase{


	@Override
	@Before
	public void setup() {

		urlList = "/system/employee/online/list";
		entity = Employee.class;
		listPartName = formPartName = "在线列表";
		resourceNodeCode = Employee.class.getSimpleName() + "-online";
		listPartServiceController = EmployeeOnlineListPart.class.getName();
		listPartJsController = EmployeeOnlineListPart.class.getName();
		listPartImportJs = "/panda-bizbase/organization/org-employee-online-list-part.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setSingleSelect(true);
		datagrid.setPagination(false);
		
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "tenancyName", "公司", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "departmentName", "部门", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "position", "职务", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "loginTime", "登录时间", ControlTypes.DATETIME_BOX, 130,OrderbyMode.DESC);
		addColumn(datagrid, "lastedTime", "最后访问时间", ControlTypes.DATETIME_BOX, 130);
		addColumn(datagrid, "ipAddress", "登录IP", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "explorer", "浏览器信息", ControlTypes.TEXT_BOX, 700);
		return datagrid;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
