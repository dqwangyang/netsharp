package org.netsharp.wx.meta.ea;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.ea.entity.WxeaApp;

public class WxeaAppWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		urlList = "/wx/ea/app/list";
		urlForm = "/wx/ea/app/form";
		entity = WxeaApp.class;
		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = WxeaApp.class.getSimpleName();
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = new PDatagrid(node, listPartName);
		datagrid.setFilter(listFilter);
		addColumn(datagrid, "code", "应用编码", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "name", "应用描述", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "corpid", "corpid", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "corpsecret", "corpsecret", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "agentId", "agentId", ControlTypes.NUMBER_BOX, 150);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, "微信应用配置");
		{
			form.setColumnCount(1);
		}
		addFormField(form, "code", "应用编码", "", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "应用描述", "", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "corpid", "corpid", "", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "corpsecret", "corpsecret", "", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "agentId", "agentId", "", ControlTypes.NUMBER_BOX, true, false);

		return form;
	}
	
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
