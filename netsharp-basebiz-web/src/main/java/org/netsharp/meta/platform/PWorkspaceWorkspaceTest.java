package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PPartType;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.web.PWorkspaceFormPart;

public class PWorkspaceWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/workspace/list";
		urlForm = "/platform/workspace/form";

		entity = PWorkspace.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = PWorkspace.class.getSimpleName();

		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
		this.formServiceController=PWorkspaceFormPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setOrderby("createTime desc");
		}
		
		PDatagridColumn column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "url", "url", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "operationType.name", "操作类型", ControlTypes.REFERENCE_BOX, 100);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "operationType2.name", "操作类型2", ControlTypes.REFERENCE_BOX,100);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		
		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();

		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);		
		addQueryItem(queryProject, "url", "路径", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "resourceNode.entityId", "实体", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		{
			form.setColumnCount(3);
		}

		PFormField field = null;

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);

		field = addFormField(form, "url", "路径", ControlTypes.TEXT_BOX, true, false);{
			field.setFullColumn(true);
		}
		field = addFormField(form, "redirectUrl", "跳转路径", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		
		field = addFormField(form, "jsController", "jsController", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		
		field = addFormField(form, "serviceController", "serviceController", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		
		field = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);{
			
			field.setHeight(50);
			field.setFullColumn(true);
		}
		
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(PPart.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "部件信息");
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);
		//PDatagridColumn column = null;

		addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 100, false, null, null, null);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "partType.name", "部件类型", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "parentCode", "parentCode", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "relationRole", "relationRole", ControlTypes.TEXT_BOX, 100);

		addColumn(datagrid, "resourceNode.pathName", "资源及节点", ControlTypes.REFERENCE_BOX, 300);
		addColumn(datagrid, "form.name", "表单", ControlTypes.REFERENCE_BOX, 150);
		addColumn(datagrid, "datagrid.name", "列表", ControlTypes.REFERENCE_BOX, 150);
		addColumn(datagrid, "toolbar", "工具栏", ControlTypes.TEXT_BOX, 150);

		PDatagridColumn column = addColumn(datagrid, "dockStyle", "dockStyle", ControlTypes.ENUM_BOX, 100);{
			String formatter = EnumUtil.getColumnFormatter(DockType.class);
			column.setFormatter(formatter);
		}
		addColumn(datagrid, "url", "url", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "jsController", "jsController", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "serviceController", "serviceController", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "style", "style", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "imports", "imports", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "filter", "filter", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "visible", "显示", ControlTypes.BOOLCOMBO_BOX, 60);

		//
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("parts");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("parts");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(950);
			part.setWindowHeight(700);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:350px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("部件");
		}
		
		PFormField formField = null;
		//基本信息
		String groupName = "基本信息";
		addFormField(form, "code", "编码",groupName, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称",groupName, ControlTypes.TEXT_BOX, true);
		addFormFieldRefrence(form, "partType.name", "部件类型", groupName, PPartType.class.getSimpleName(), true, false);
		addFormField(form, "dockStyle", "布局",groupName, ControlTypes.ENUM_BOX, true);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", groupName, ResourceNode.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "form.name", "表单", groupName, PForm.class.getSimpleName(), false, false);
		
		groupName = "关联配置";
		addFormField(form, "parentCode", "parentCode",groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "relationRole", "relationRole",groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "toolbar", "工具栏",groupName, ControlTypes.TEXT_BOX, false);
		formField = addFormField(form, "imports", "引入文件", ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
			formField.setGroupName(groupName);
		}
		formField = addFormField(form, "jsController", "JsController", ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
			formField.setGroupName(groupName);
		}
		formField = addFormField(form, "serviceController", "ServiceController", ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
			formField.setGroupName(groupName);
		}

		
		//表格相关
		groupName = "表格相关";
		addFormFieldRefrence(form, "datagrid.name", "表格方案", groupName, PDatagrid.class.getSimpleName(), false, false);
		addFormField(form, "openMode", "打开方式",groupName, ControlTypes.ENUM_BOX, true);	
		addFormField(form, "url", "表单路径",groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "filter", "过滤条件",groupName, ControlTypes.TEXT_BOX, false);	
		addFormField(form, "windowWidth", "弹出宽度",groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "windowHeight", "弹出高度",groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "headerVisible", "显示标题",groupName, ControlTypes.CHECK_BOX, false);
		
		groupName = "外观配置";
		addFormField(form, "width", "宽度",groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "height", "高度",groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "tooltip", "提示", groupName,ControlTypes.TEXT_BOX, false);
		addFormField(form, "style", "样式", groupName,ControlTypes.TEXT_BOX, false);
		addFormField(form, "visible", "显示",groupName, ControlTypes.CHECK_BOX, false);

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