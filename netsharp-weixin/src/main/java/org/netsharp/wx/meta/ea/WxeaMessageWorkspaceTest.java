package org.netsharp.wx.meta.ea;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.ea.entity.WxeaMessage;
import org.netsharp.wx.ea.entity.WxeaMessageReciver;

/**
 * 微信企业号发送消息配置
 */
public class WxeaMessageWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		urlList = "/wx/ea/message/list";
		urlForm = "/wx/ea/message/form";
		entity = WxeaMessage.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = WxeaMessage.class.getSimpleName();
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "wxpaConfiguration.name", "微信应用", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "memoto", "描述", ControlTypes.TEXTAREA, 300);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, 120);
		return datagrid;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(WxeaMessageReciver.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "固定接收人");
		datagrid.setShowTitle(true);

		addColumn(datagrid, "receiver.name", "接收人", ControlTypes.TEXT_BOX, 300);
	    
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("pmConfig");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("fixReceivers");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			
			part.setWindowWidth(500);
			part.setWindowHeight(300);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:200px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("接收人");
		PFormField field = addFormField(form, "receiver.name", "接收人", null, ControlTypes.REFERENCE_BOX, true, false);
		{
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			field.setReference(reference);
		}
		return form;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("企业号消息配置");
			form.setColumnCount(3);
		}

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "wxpaConfiguration.name", "微信应用", null, WxeaApp.class.getSimpleName(), true, false);
		addFormField(form, "memoto", "描述", ControlTypes.TEXTAREA, false, false);
		return form;

	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "消息编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "消息名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "wxpaConfiguration.name", "微信应用", WxeaApp.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "fixReceivers.receiver.name", "接收人", Employee.class.getSimpleName());
		return queryProject;
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
