package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationAuthorizationLog;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class OperationAuthorizationLogWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		urlList = "/system/authorization/log/list";
		urlForm = "/system/authorization/log/form";
		entity = OperationAuthorizationLog.class;

		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
		resourceNodeCode = OperationAuthorizationLog.class.getSimpleName();
	}

	@Override
	public void createListWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);

		PDatagrid datagrid = this.createDatagrid(node);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setCode(this.resourceNodeCode);
			workspace.setResourceNode(node);
			workspace.setOperationTypeId(workspaceOperationType1Id);
			workspace.setName("授权日志");
			workspace.setUrl(urlList);

		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode(this.resourceNodeCode);
			part.setResourceNode(node);
			part.setName("授权日志");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setUrl(urlForm);
			if (!StringManager.isNullOrEmpty(listPartJsController)) {
				part.setJsController(listPartJsController);
			}

			if (!StringManager.isNullOrEmpty(listPartServiceController)) {
				part.setServiceController(listPartServiceController);
			}

			if (!StringManager.isNullOrEmpty(listPartImportJs)) {
				part.setImports(listPartImportJs);
			}
		}
		workspace.getParts().add(part);
		workspaceService.save(workspace);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PQueryProject queryProject = this.createQueryProject(node);
		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setFilter(listFilter);
			datagrid.setQueryProject(queryProject);
			datagrid.setSortName("createTime DESC");
			datagrid.setNowrap(false);
		}

		addColumn(datagrid, "creator", "授权人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "createTime", "授权时间", ControlTypes.TEXT_BOX, 125);
		addColumn(datagrid, "positionName", "岗位", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "operationType", "操作类型", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "operationAuthorizationText", "授权功能", ControlTypes.TEXTAREA, 650);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject,"creator","授权人",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"positionName","岗位名称",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"operationAuthorizationText","功能名称",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"createTime","授权时间",ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = super.createForm(node);
		form.setReadOnly(true);
		addFormField(form, "creator", "授权人", null, ControlTypes.TEXT_BOX, false);
		addFormField(form, "createTime", "授权时间", null, ControlTypes.TEXT_BOX, false);
		addFormField(form, "positionName", "岗位", null, ControlTypes.TEXT_BOX, false);
		addFormField(form, "operationType", "操作类型", null, ControlTypes.TEXT_BOX, false);
		PFormField field = addFormField(form, "operationAuthorizationText", "授权功能", null, ControlTypes.TEXTAREA, false);
		{
			field.setHeight(150);
		}
		return form;
	}

	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}