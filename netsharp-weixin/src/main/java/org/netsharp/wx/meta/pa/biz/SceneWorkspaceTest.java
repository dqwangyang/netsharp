package org.netsharp.wx.meta.pa.biz;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.Scene;

public class SceneWorkspaceTest extends WorkspaceCreationBase{
	
	@Override
	@Before
	public void setup() {

		urlList = "/wx/pa/scene/list";
		urlForm = "/wx/pa/scene/form";

		entity = Scene.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = Scene.class.getSimpleName();
	}
	

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(10);
			datagrid.setOrderby("createTime desc");
		}

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "codeType", "是否永久", ControlTypes.BOOLCOMBO_BOX, 100, false);
		addColumn(datagrid, "weixinSceneId", "参数值[永久]", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "code", "参数值[临时]", ControlTypes.TEXT_BOX, 150, false);
		addColumn(datagrid, "qrCodeUrl", "二维码", ControlTypes.TEXT_BOX, 150, false);
		addColumn(datagrid, "memoto", "备注", ControlTypes.BOOLCOMBO_BOX, 200, false);
		addColumn(datagrid, "creator", "业务员", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false);

		return datagrid;
	}

    
	protected PQueryProject createQueryProject(ResourceNode node){
    	
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "creator", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "codeType", "是否永久", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "memoto", "备注", ControlTypes.TEXT_BOX);
		return queryProject;
    }

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "weixinSceneId", "参数值", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		addFormField(form, "qrCodeUrl", "二维码", ControlTypes.IMAGE, false, false);
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