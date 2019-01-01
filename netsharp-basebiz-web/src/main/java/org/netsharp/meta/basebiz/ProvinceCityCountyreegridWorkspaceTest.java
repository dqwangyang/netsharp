package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.pcc.entity.ProvinceCityCounty;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ProvinceCityCountyreegridWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		urlList = "/system/pcc/list";
		entity = ProvinceCityCounty.class;
		formPartName = listPartName = "省市区档案";
		resourceNodeCode = ProvinceCityCounty.class.getSimpleName();
		listPartType = PartType.TREEGRID_PART.getId();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setReadOnly(true);
			datagrid.setFilter(listFilter);
		}
		PDatagridColumn column = null;

		column = addColumn(datagrid, "parentId", "parentId",
				ControlTypes.TEXT_BOX, 40, true);
		{
			column.setSystem(true);
			column.setVisible(false);
		}

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "pathName", "路径", ControlTypes.TEXT_BOX, 400, false);
		datagrid.setPagination(false);
		return datagrid;
	}

	// 创建详细页面
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, "省市区档案");
		form.setColumnCount(1);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		return form;
	}

	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(entity.getSimpleName());
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
