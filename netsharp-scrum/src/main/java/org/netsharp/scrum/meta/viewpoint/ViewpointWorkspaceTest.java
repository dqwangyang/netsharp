package org.netsharp.scrum.meta.viewpoint;

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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Viewpoint;

public class ViewpointWorkspaceTest extends WorkspaceCreationBase {
	
	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/viewpoint/list";
		urlForm = "/scrum/viewpoint/form";
		resourceNodeCode = "viewpoint-daily";
		entity = Viewpoint.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;

		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		column = addColumn(datagrid, "createTime", "发表时间", ControlTypes.DATE_BOX, 150);{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		addColumn(datagrid, "owner.name", "作者", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "labels", "标签", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 400, true);
		addColumn(datagrid, "fansDisplay", "粉丝", ControlTypes.TEXT_BOX, 200);
		
		
		
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("观点");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}

		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "owner.name", "作者", Employee.class.getSimpleName());
		addQueryItem(queryProject, "createTime", "发表时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "labels", "标签", ControlTypes.TEXT_BOX);
		
		return queryProject;
	}


	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, "观点表单");
		{
			form.setColumnCount(3);
		}

		PFormField field = null;
		
//		field = addFormField(form, "code", "编码", null , ControlTypes.TEXT_BOX, true, false);
		field = addFormField(form, "name", "名称", null , ControlTypes.TEXT_BOX, true, false);
		field = addFormField(form, "labels", "标签", null , ControlTypes.TEXT_BOX, true, false);
		field = addFormFieldRefrence(form, "owner.name", "作者",null, Employee.class.getSimpleName(), false, false);
		field = addFormField(form, "fansDisplay", "粉丝", null , ControlTypes.TEXT_BOX, false, true);
		
		field = addFormField(form, "content", "内容", null , ControlTypes.TEXTAREA, true, false);
				
		field.setFullColumn(true);
		field.setHeight(500);

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

