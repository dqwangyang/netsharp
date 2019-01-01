package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.log.entity.NLog;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.web.NLogListPart;

public class NLogWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/system/nlog/list";
		urlForm = "/system/nlog/form";
		entity = NLog.class;
		formPartName =listPartName =  "操作日志";
		resourceNodeCode = NLog.class.getSimpleName();
		listPartJsController = NLogListPart.class.getName();
		listPartServiceController = NLogListPart.class.getName();
		this.listPartImportJs = "/panda-bizbase/organization/nlog-list-part.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setShowCheckbox(false);
		datagrid.setAutoQuery(false);
		addColumn(datagrid, "createTime", "操作时间", ControlTypes.DATETIME_BOX, 150,false,null,null,OrderbyMode.DESC);
		addColumn(datagrid, "creator", "操作人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "operaitonName", "操作", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "logType", "类型", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "message", "消息", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject,"logType","日志类型",ControlTypes.ENUM_BOX);
		addQueryItem(queryProject,"code","编码",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"name","名称",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"message","消息内容",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"entityId","entityId",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"operaitonName","操作",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"creator","操作人",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"createTime","操作时间",ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(2);

		addFormField(form, "createTime", "操作时间", ControlTypes.DATETIME_BOX, false, false);
		addFormField(form, "creator", "操作人", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "operaitonName", "操作", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "logType", "类型", ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "associateId", "业务对象Id", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "entityId", "entityId", ControlTypes.TEXT_BOX, false, false);
		PFormField field = addFormField(form, "message", "消息", ControlTypes.TEXTAREA, false, false);
		{
			field.setFullColumn(true);
			field.setHeight(200);
		}

		return form;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}