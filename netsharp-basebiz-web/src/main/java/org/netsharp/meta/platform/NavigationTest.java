package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.autoencoder.entity.Encoder;
import org.netsharp.job.entity.Job;
import org.netsharp.job.entity.JobLog;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "平台工具";
		this.treePath = "panda/navigation/platform";
		this.resourceNode = "platform";
	}

	@Override
	public void createAccodions() {

		this.doCreateAccodions("platform", "平台工具","fa fa-window-restore fa-fw", 999);

	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		this.createPTreeNode(tree, null, "fa fa-columns fa-fw", "UI_Tool", "UI工具", null, 1);
		{
			createPTreeNode(tree, "UI_Tool", null, Plugin.class.getSimpleName(), "插件列表", "/platform/plugin/list", 3);
			createPTreeNode(tree, "UI_Tool", null, ResourceNode.class.getSimpleName(), "资源节点", "/platform/resourceNode/tree", 4);
			createPTreeNode(tree, "UI_Tool", null, PAccordion.class.getSimpleName(), "模块管理", "/platform/accordion/list", 5);
			createPTreeNode(tree, "UI_Tool", null, PNavigation.class.getSimpleName(), "导航菜单", "/platform/ptree/list", 6);
			createPTreeNode(tree, "UI_Tool", null, PToolbar.class.getSimpleName(), "工具栏", "/platform/toolbar/list", 8);
			createPTreeNode(tree, "UI_Tool", null, PWorkspace.class.getSimpleName(), "工作区", "/platform/workspace/list", 9);
			createPTreeNode(tree, "UI_Tool", null, PDatagrid.class.getSimpleName(), "表格管理", "/platform/datagrid/list", 10);
			createPTreeNode(tree, "UI_Tool", null, PForm.class.getSimpleName(), "表单管理", "/platform/form/list", 11);
			createPTreeNode(tree, "UI_Tool", null, PQueryProject.class.getSimpleName(), "查询方案", "/platform/queryproject/list", 12);
			createPTreeNode(tree, "UI_Tool", null, PReference.class.getSimpleName(), "参照管理", "/platform/reference/list", 13);
			createPTreeNode(tree, "UI_Tool", null, Operation.class.getSimpleName(), "操作管理", "/platform/operation/list", 14);
			createPTreeNode(tree, "UI_Tool", null, Encoder.class.getSimpleName(), "编码规则", "/platform/encoder/list", 15);
			// createPTreeNode(tree, "UI_Tool", PPads.class.getSimpleName(),
			// "工作台面板", "/platform/pads/list", 16);
			createPTreeNode(tree, "UI_Tool", null, BeanPath.class.getSimpleName(), "Action管理", "/platform/bean/path/list", 17);
			createPTreeNode(tree, "system-authorization", null, OperationType.class.getSimpleName(), "操作类型", "/system/operationType/list");
		}
		createPTreeNode(tree, null, "fa fa-clock-o fa-fw", "JobManager", "作业管理", null, 2);
		{
			createPTreeNode(tree, "JobManager", null, Job.class.getSimpleName(), "作业列表", "/platform/quartjob/list", 1);
			createPTreeNode(tree, "JobManager", null, JobLog.class.getSimpleName(), "作业日志", "/platform/quartjoblog/list", 2);
		}

		this.createPTreeNode(tree, null, "fa fa-database fa-fw", "DBA_Tool", "DBA工具", null, 3);
		{
			this.createPTreeNode(tree, "DBA_Tool", null, "DBA_Query", "查询分析器", "/platform/dbm/list", 1);
			this.createPTreeNode(tree, "DBA_Tool", null, "DBA_Log", "查询日志", "/platform/dbm/log/list", 2);
		}

		this.createPTreeNode(tree, null, "fa fa-calendar fa-fw", "Platform_Biz", "基础配置", null, 3);
		{
			this.createPTreeNode(tree, "Platform_Biz", null, "Platform_Biz_Date", "日期档案", "/platform/date/list", 1);
		}
	}
}
