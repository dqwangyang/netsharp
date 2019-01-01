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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Product;
import org.netsharp.scrum.entity.ProductDemand;

public class ProductDemandWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/product/demand/list";
		urlForm = "/scrum/product/demand/form";
		resourceNodeCode = ProductDemand.class.getSimpleName();
		entity = ProductDemand.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = "产品需求";

		// this.listFilter="product.status != 4";

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
		datagrid.setToolbar("panda/datagrid/row/edit");
		datagrid.setPageSize(25);
		
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "versionNumber", "版本", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "标题", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "type", "类别", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "urgency", "重要等级", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "priority", "优先级", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "progress", "处理进度", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "onlineDate", "上线日期", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "putor.name", "提出人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "owner.name", "处理人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "sender.name", "抄送人", ControlTypes.TEXT_BOX, 100);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);

		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("项目");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}

		addQueryItem(queryProject, "name", "标题", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "product.name", "产品", Product.class.getSimpleName());
		addQueryItem(queryProject, "type", "类别", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "urgency", "重要等级", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "priority", "优先级", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "progress", "处理进度", ControlTypes.ENUM_BOX);

		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, "产品需求");
		form.setColumnCount(3);

		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "versionNumber", "版本号", null, ControlTypes.TEXT_BOX, false, false);
		
		addFormField(form, "name", "标题", null, ControlTypes.TEXT_BOX, true, false);
		
		addFormFieldRefrence(form, "product.name", "产品", null, Product.class.getSimpleName(), true, false);
		addFormField(form, "type", "类别", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "urgency", "重要等级", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "priority", "优先级", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "progress", "处理进度", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "onlineDate", "上线日期", ControlTypes.DATE_BOX, false, false);
		
		addFormFieldRefrence(form, "putor.name", "提出人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "owner.name", "处理人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "sender.name", "抄送人", null, Employee.class.getSimpleName(), true, false);

		PFormField field = addFormField(form, "content", "需求描述", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(400);
			field.setFullColumn(true);
		}

		return form;
	}
}
