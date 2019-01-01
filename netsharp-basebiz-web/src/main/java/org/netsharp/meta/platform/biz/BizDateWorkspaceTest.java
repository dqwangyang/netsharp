package org.netsharp.meta.platform.biz;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.platform.entity.BizDate;
import org.netsharp.resourcenode.entity.ResourceNode;

public class BizDateWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		urlList = "/platform/date/list";
		urlForm = "/platform/date/form";
		entity = BizDate.class;
		formPartName = listPartName = "日期档案";
		resourceNodeCode = "Platform_Biz_Date";
	}


	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, node.getName());
		{
			form.setColumnCount(1);
		}
		addFormField(form, "year", "年", "", ControlTypes.NUMBER_BOX, true);
		addFormField(form, "month", "月", "", ControlTypes.NUMBER_BOX, true);
		addFormField(form, "day", "日", "", ControlTypes.NUMBER_BOX, true);
		addFormField(form, "week", "周", "", ControlTypes.ENUM_BOX, true);
		addFormField(form, "holidays", "节假日", "", ControlTypes.CHECK_BOX, false);
		PFormField field = addFormField(form, "memoto", "备注", "", ControlTypes.TEXTAREA, false);{
			field.setFullColumn(true);
			field.setHeight(100);
		}
		return form;
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setReadOnly(true);
			datagrid.setFilter(listFilter);
			
			PRowStyler styler = new PRowStyler();
			{
				styler.toNew();
				styler.setDatagridId(datagrid.getId());
				styler.setRowCondition("row.holidays");
				styler.setRowStyle("color:red");
				datagrid.getStylers().add(styler);
			}
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "year", "年", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		column = addColumn(datagrid, "month", "月", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		column = addColumn(datagrid, "day", "日", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		column = addColumn(datagrid, "week", "周", ControlTypes.ENUM_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "holidays", "节假日", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("if(val){return '是';}return '否';");
		}
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("日期档案");
			queryProject.setResourceNode(node);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("year");
			item.setHeader("年");
			item.setControlType(ControlTypes.NUMBER_BOX);
			item.setInterzone(false);
		}
		queryProject.getQueryItems().add(item);

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("month");
			item.setHeader("月");
			item.setControlType(ControlTypes.NUMBER_BOX);
			item.setInterzone(false);
		}
		queryProject.getQueryItems().add(item);
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("day");
			item.setHeader("日");
			item.setControlType(ControlTypes.NUMBER_BOX);
			item.setInterzone(false);
		}
		queryProject.getQueryItems().add(item);
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("week");
			item.setHeader("周");
			item.setControlType(ControlTypes.ENUM_BOX);
		}
		queryProject.getQueryItems().add(item);
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("holidays");
			item.setHeader("节假日");
			item.setControlType(ControlTypes.BOOLCOMBO_BOX);
		}
		queryProject.getQueryItems().add(item);
		queryProject.setColumnCount(3);
		return queryProject;
	}

	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		if (node == null) {
			node = resourceService.byCode(entity.getSimpleName());
		}
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}