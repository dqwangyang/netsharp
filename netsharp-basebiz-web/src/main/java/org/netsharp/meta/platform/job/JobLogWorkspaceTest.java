package org.netsharp.meta.platform.job;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.job.entity.JobLog;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class JobLogWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		urlList = "/platform/quartjoblog/list";
		formPartName = "作业日志";
		entity = JobLog.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = entity.getSimpleName();
		formPartName = listPartName = meta.getName();
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setQueryProject(this.createQueryProject(node));
		datagrid.setSortName("createTime desc");
		
		addColumn(datagrid, "name", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "groupName", "分组", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "javaType", "服务全名", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "操作日期", ControlTypes.DATETIME_BOX, 150);
		addColumn(datagrid, "operation", "操作", ControlTypes.TEXT_BOX, 160);
		addColumn(datagrid, "timed", "耗时", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 800);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "操作日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Test
	public void operation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
