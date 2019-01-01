package org.netsharp.wx.meta.pa.configuration;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.PublicAccount;

public class ReplyTextWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/wx/reply/text/list";
		urlForm = "/wx/reply/text/form";
		entity = NTextReply.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = NTextReply.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);

		// 基本信息
		addColumn(datagrid, "publicAccount.name", "公众号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "keyword", "关键字", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "content", "回复内容", ControlTypes.TEXT_BOX, 400);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100);

		// 操作信息
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, 120);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("文本回复");
			form.setColumnCount(3);
		}

		addFormFieldRefrence(form, "publicAccount.name", "公众号", null, PublicAccount.class.getSimpleName(), true, false);

		addFormField(form, "keyword", "关键字", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "content", "回复内容", ControlTypes.TEXTAREA, true, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);

		return form;

	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addRefrenceQueryItem(queryProject, "publicAccount.name", "公众号", PublicAccount.class.getSimpleName());
		return queryProject;
	}

	@Override
	public void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}