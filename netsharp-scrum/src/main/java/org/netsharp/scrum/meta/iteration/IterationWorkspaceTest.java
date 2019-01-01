package org.netsharp.scrum.meta.iteration;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Iteration;

public class IterationWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {
		// 在子类中重定义
		urlList = "/scrum/iteration/list";
		urlForm = "/scrum/iteration/form";
		
		entity = Iteration.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "项目迭代";
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
		
		PRowStyler styler = new PRowStyler();
		styler.setRowCondition("row.isCurrent==true");
		styler.setRowStyle("color:red;");
		styler.toNew();
		datagrid.getStylers().add(styler);
		
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "isCurrent", "当前迭代", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "startTime", "开始时间", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "endTime", "结束时间", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "updateTime", "修改时间", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "updator", "修改人", ControlTypes.TEXT_BOX, 150);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "isCurrent", "当前迭代", ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "startTime", "开始时间", ControlTypes.DATE_BOX, true, false);
		addFormField(form, "endTime", "结束时间", ControlTypes.DATE_BOX, true, false);
		
		PFormField field = addFormField(form, "content", "正文", ControlTypes.TEXTAREA, true, false);
		{
			field.setFullColumn(true);
			field.setHeight(200);
		}
		
		return form;
	}
}
