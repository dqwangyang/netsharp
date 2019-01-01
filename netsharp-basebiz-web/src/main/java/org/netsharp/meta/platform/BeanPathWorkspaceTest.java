package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class BeanPathWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/bean/path/list";
		urlForm = "/platform/bean/path/form";

		entity = BeanPath.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  meta.getName();
		resourceNodeCode = BeanPath.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "path", "path", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "basePath", "basePath", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "path", "路径", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "basePath", "父路径", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(2);
		}

		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		addFormField(form, "path", "path", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "basePath", "basePath", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(Bean.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "Bean");
		datagrid.setShowTitle(true);
		addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 120);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "type", "类型", ControlTypes.TEXT_BOX, 400);
		addColumn(datagrid, "seq", "排序", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX, 100);

		addColumn(datagrid, "resourceNode.pathName", "资源节点", ControlTypes.REFERENCE_BOX, 300);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false,null, null, null);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150,false,null, null, null);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100,false,null, null, null);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150,false,null, null, null);

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("items");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("items");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(650);
			part.setWindowHeight(300);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:300px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("Bean");
		}

		addFormField(form, "name", "名称",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "seq", "执行顺序",null, ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "type", "类型",null, ControlTypes.TEXTAREA, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), false, false);
		addFormField(form, "disabled", "停用",null, ControlTypes.CHECK_BOX, false, false);

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