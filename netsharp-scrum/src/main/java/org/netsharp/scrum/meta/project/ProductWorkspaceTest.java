package org.netsharp.scrum.meta.project;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Product;

public class ProductWorkspaceTest  extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/product/list";
		urlForm = "/scrum/product/form";
		resourceNodeCode = Product.class.getSimpleName();
		entity = Product.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "产品";
		
		this.listFilter="product.status != 4";

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
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "organization.name", "部门", ControlTypes.TEXT_BOX, 100);
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
			queryProject.setName("项目");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, "项目");
		form.setColumnCount(3);

		addFormField(form, "code", "编码",null, ControlTypes.TEXT_BOX, true, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "organization.pathName","部门", null, "Organization-Department", false, false);
		addFormField(form, "creator", "创建人",null, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", null, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "updator", "修改人", null, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "upDATE_BOX", "修改时间", null, ControlTypes.DATE_BOX, false, true);

		return form;
	}
}

