package org.netsharp.wx.meta.pa.configuration;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;

public class NWeixinSubscriberWorkspaceTest extends WorkspaceCreationBase {


	@Override
	@Before
	public void setup() {

		urlList = "/wx/subscriber/list";
		urlForm = "/wx/subscriber/form";
		entity = NWeixinSubscriber.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = NWeixinSubscriber.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);

		// 基本信息
		addColumn(datagrid, "publicAccount.name", "公众号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "javaType", "java类型", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "disabled", "停用", ControlTypes.TEXT_BOX, 100);
		PDatagridColumn column = addColumn(datagrid, "seq", "排序", ControlTypes.TEXT_BOX, 100);
		{
			column.setOrderbyMode(OrderbyMode.ASC);
		}
		
		addColumn(datagrid, "memoto", "描述", ControlTypes.TEXTAREA, 300, false);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false,null, null, null);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 120, false,null, null, null);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, 120, false, null, null, null);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		{
			form.setColumnCount(1);
		}

		// 基本信息
		addFormFieldRefrence(form, "publicAccount.name", "公众号", null, PublicAccount.class.getSimpleName(), true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "javaType", "Java类型", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "disabled", "停用", ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "seq", "排序", ControlTypes.NUMBER_BOX, false, false);
		
		addFormField(form, "creator", "创建人", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updator", "最后修改人",ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, false, true);

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