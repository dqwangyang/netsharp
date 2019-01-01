package org.netsharp.scrum.meta.project;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.ActivityPlan;

public class ActivityPlanWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/activityplan/list";
		urlForm = "/scrum/activityplan/form";

		entity = ActivityPlan.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "活动计划";
		resourceNodeCode = ActivityPlan.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			
			datagrid.setPageSize(25);
		}
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);

		PDatagridColumn column = addColumn(datagrid, "activityTime", "上线时间", ControlTypes.TEXT_BOX, 100, true);
		column.setOrderbyMode(OrderbyMode.DESC);
		addColumn(datagrid, "deployTime", "部署时间", ControlTypes.TEXT_BOX, 200, true);

		addColumn(datagrid, "bizman.name", "业务负责人", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "productor.name", "产品经理", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "deployer.name", "技术负责人", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "testor.name", "质量负责人", ControlTypes.TEXT_BOX, 100, true);

		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		PFormField field = null;

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "activityTime", "上线时间", ControlTypes.DATE_BOX, true, false);
		addFormField(form, "deployTime", "部署时间", ControlTypes.DATE_BOX, true, false);

		addFormFieldRefrence(form, "bizman.name", "业务负责人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "productor.name", "产品经理", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "deployer.name", "技术负责人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "testor.name", "质量负责人", null, Employee.class.getSimpleName(), true, false);


		field = addFormField(form, "content", "内容", ControlTypes.EDITOR, false, false);
		field.setHeight(300);
		field.setFullColumn(true);

		return form;
	}

	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
	}
}
