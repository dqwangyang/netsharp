package org.netsharp.meta.platform.dbm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.dbm.entity.DbmLog;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DbmLogWorkspace extends WorkspaceCreationBase {
	 
	@Before
	public void setup() {
		
		Mtable meta = MtableManager.getMtable(DbmLog.class);
		
		// 在子类中重定义
		entity = DbmLog.class;
		
		this.formPartName = listPartName =  meta.getName();
		resourceNodeCode = "DBA_Log";
		// 在子类中重定义
		urlList = "/platform/dbm/log/list";
		urlForm = "/platform/dbm/log/form";
	}
	 
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		
		addColumn(datagrid, "creator", "执行人", ControlTypes.TEXT_BOX, 100); 
		PDatagridColumn column = addColumn(datagrid, "createTime", "执行时间", ControlTypes.DATETIME_BOX, 200);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		
		addColumn(datagrid, "content", "执行脚本", ControlTypes.TEXT_BOX, 400); 
		
		return datagrid;
	}
	

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "creator", "执行人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "执行时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
			form.setReadOnly(true);
		}
		
	 
		addFormField(form, "creator", "执行人", "基本信息", ControlTypes.DATETIME_BOX, true);
		addFormField(form, "createTime", "执行时间", "基本信息", ControlTypes.DATETIME_BOX,true, false);
		addFormField(form, "content", "执行脚本","基本信息", ControlTypes.TEXTAREA,true, false);

	 	return form;
	}

	@Test
	public void operation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}