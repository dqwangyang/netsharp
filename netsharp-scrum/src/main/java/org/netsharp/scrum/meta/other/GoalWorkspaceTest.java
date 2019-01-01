package org.netsharp.scrum.meta.other;

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
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Goal;

public class GoalWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {		
		// 在子类中重定义
		urlList = "/scrum/goal/list";
		urlForm = "/scrum/goal/form";
		
		entity = Goal.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "目标管理";
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
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "startTime", "开始时间", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "endTime", "结束时间", ControlTypes.TEXT_BOX, 100);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "startTime", "开始时间", ControlTypes.DATE_BOX, false, false);
		addFormField(form, "endTime", "结束时间", ControlTypes.DATE_BOX, false, false);
		
		PFormField field = addFormField(form, "content", "正文", ControlTypes.TEXTAREA, false, false);
		{
			field.setFullColumn(true);
			field.setHeight(200);
		}
		
		return form;
	}
}
