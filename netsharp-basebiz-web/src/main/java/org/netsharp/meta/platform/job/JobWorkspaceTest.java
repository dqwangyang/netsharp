package org.netsharp.meta.platform.job;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.job.controller.JobListPart;
import org.netsharp.job.entity.Job;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class JobWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		
		urlList = "/platform/quartjob/list";
		urlForm = "/platform/quartjob/form";
		listToolbarPath = "platform/quartjob/list/toolbar";
		listPartServiceController = JobListPart.class.getName();
		listPartJsController = JobListPart.class.getName();
		listPartImportJs = "/panda-platform/job/jobListPart.js";
		entity = Job.class;
		resourceNodeCode = Job.class.getSimpleName();
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
	}

	@Test
	public void run() {
		this.createListToolbar();
		this.createListWorkspace();
		this.createFormWorkspace();
	}

	private void createListToolbar() {
		
		ResourceNode node = this.resourceService.byCode(this.resourceNodeCode);		
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("quartjob");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar,"start","启动", "fa-play-circle-o", "start()",null,95);
		addToolbarItem(toolbar,"pause","暂停", "fa-pause-circle", "pause()",null,96);
		addToolbarItem(toolbar,"resume","恢复", "fa-repeat", "resume()",null,97);
		addToolbarItem(toolbar,"run","执行", "fa-check", "run()",null,98);
		addToolbarItem(toolbar,"createCron","生成", "fa-code", "createCron()",null,99);

		toolbarService.save(toolbar);
	}


	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "groupName", "分组", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "cron", "cron表达式", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "resourceNode.pathName", "资源", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "cronDescription", "时间描述", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "javaType", "服务全名", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "description", "备注", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}


	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		{
			form.setColumnCount(1);
		}

		PFormField formField = null;

		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "groupName", "分组", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		formField = addFormField(form, "javaType", "服务全名", ControlTypes.TEXT_BOX, true, false);
		{
			formField.setFullColumn(true);
			formField.setMaxLength(500);
		}
		formField = addFormField(form, "cron", "cron表达式",ControlTypes.TEXT_BOX, true, false);{
			formField.setFullColumn(true);
			formField.setMaxLength(500);
		}
		formField = addFormField(form, "cronDescription", "时间描述",ControlTypes.TEXT_BOX, true, false);{
			formField.setFullColumn(true);
			formField.setMaxLength(500);
		}
		formField = addFormField(form, "description", "备注", ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
			formField.setMaxLength(500);
		}

		return form;
	}

	@Test
	public void operation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}
