package org.netsharp.scrum.meta.project;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Deploy;
import org.netsharp.scrum.entity.Product;
import org.netsharp.scrum.entity.Project;

public class ProjectWorkspaceTest  extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/project/list";
		urlForm = "/scrum/project/form";
		resourceNodeCode = Project.class.getSimpleName();
		entity = Project.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "项目";
		
		this.listFilter="project.status !=4";
	}

	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
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
		addColumn(datagrid, "status", "项目状态", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "deploy.name", "发布计划", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "deploy.deployTime", "发布时间", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "bizUser.name", "客户", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "productor.name", "产品负责人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "productorTime", "产品提交时间", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "owner.name", "开发负责人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "developTime", "开发提测时间", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "testor.name", "测试负责人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "testTime", "测试完成时间", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "organization.name", "开发部门", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "csr", "客户满意度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "urgency", "紧急性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "actualHours", "耗时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "taskProperties", "工作性质", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "capacityIndex", "能力指数", ControlTypes.ENUM_BOX, 100);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);

		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("研发项目");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("name");
			item.setHeader("名称");
			item.setControlType(ControlTypes.TEXT_BOX);

			queryProject.getQueryItems().add(item);
		}
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "product.name", "产品", Product.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "deploy.name", "发布计划", Deploy.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "productor.name", "产品负责人", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "owner.name", "开发负责人", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "testor.name", "测试负责人", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "bizUser.name", "客户", Employee.class.getSimpleName());
		addQueryItem(queryProject, "status", "项目状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "csr", "客户满意度", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "importance", "重要性", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "urgency", "紧急性", ControlTypes.ENUM_BOX);
		addRefrenceQueryItem(queryProject, "organization.pathName", "部门", "Organization-Department");
		return queryProject;
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, "销售bd渠道");
		form.setColumnCount(2);

		PFormField field = addFormField(form, "code", "编码", "基本信息", ControlTypes.TEXT_BOX, true, true);
		field = addFormField(form, "name", "名称", "基本信息", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "product.name", "产品", "基本信息", Product.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "deploy.name", "发布计划", "基本信息", Deploy.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "bizUser.name", "客户", "基本信息", Employee.class.getSimpleName(), true, false);
		
		addFormFieldRefrence(form, "productor.name", "产品负责人", "任务安排", Employee.class.getSimpleName(), true, false);
		addFormField(form, "productTime", "产品提交时间", "任务安排", ControlTypes.DATE_BOX, false, false);
		addFormFieldRefrence(form, "owner.name", "开发负责人", "任务安排", Employee.class.getSimpleName(), true, false);

		addFormField(form, "developTime", "提交测试时间", "任务安排", ControlTypes.DATE_BOX, false, false);
		addFormFieldRefrence(form, "testor.name", "测试负责人", "任务安排", Employee.class.getSimpleName(), true, false);
		addFormField(form, "testTime", "测试完成时间", "任务安排", ControlTypes.DATE_BOX, false, false);
		addFormFieldRefrence(form, "organization.pathName", "研发部门", "任务安排", "Organization-Department", true, false);

		field = addFormField(form, "csr", "客户满意度", "工作性质", ControlTypes.ENUM_BOX, false, false);
		field = addFormField(form, "taskProperties", "工作性质", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "capacityIndex", "能力指数", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "importance", "重要性", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "urgency", "紧急性", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "estimateHours", "估时（小时）", "项目内容", ControlTypes.DECIMAL_BOX, true, false);
		field = addFormField(form, "actualHours", "耗时（小时）", "项目内容", ControlTypes.DECIMAL_BOX, false, false);
		field = addFormField(form, "status", "任务状态", "项目内容", ControlTypes.ENUM_BOX, false, false);
		field = addFormField(form, "content", "内容", "项目内容", ControlTypes.TEXTAREA, false, false);
		field.setHeight(200);
		field.setWidth(500);
		return form;
	}
}

