package org.netsharp.meta.platform;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.web.PlatformFormProjectDetailPart;

public class FormWorkspaceTest extends WorkspaceCreationBase {

	 String formDetailToolbar = "panda/platform/form/detail";

	 String formDetailPartImportJs = "/panda-platform/js/platform-tool-detail-part.js|/panda-platform/js/platform-formproject-detail-part.js";
	 
	 String formDetailPartJsController = PlatformFormProjectDetailPart.class.getName();

	 String formDetailPartServiceController =  PlatformFormProjectDetailPart.class.getName();
	
	@Override
	@Before
	public void setup() {

		urlList = "/platform/form/list";
		urlForm = "/platform/form/form";
//		formJsImport = "/panda-platform/js/platform-tool-form-part.js";
		entity = PForm.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  meta.getName();
		resourceNodeCode = PForm.class.getSimpleName();
		
	}
	
	@Test
	public void run() {
		createFormDetailToolbar();
		createListWorkspace();
		createFormWorkspace();
	}
	public void createFormDetailToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/detail");
			toolbar.setPath(formDetailToolbar);
			toolbar.setName("平台表单明细");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "selectFileds", "选择字段","fa fa-list-ol", "selectFileds()",null,20);
		toolbarService.save(toolbar);
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setOrderby("createTime desc");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300, false);
		addColumn(datagrid, "resourceNode.entityId", "entityId", ControlTypes.TEXT_BOX, 300, false);
		column = addColumn(datagrid, "columnCount", "每行列数", ControlTypes.TEXT_BOX, 100, false);{
			column.setAlign(DatagridAlign.CENTER);
		}

		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "id", "id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "resourceNode.entityId", "实体", ControlTypes.TEXT_BOX);
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
		
		//PFormField formField = null;
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);{
			
//			formField.setTroikaTrigger("resourceNodeChange(newValue, oldValue);");
		}
		addFormField(form, "columnCount", "每行列数", ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "readOnly", "只读", ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(PFormField.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "表单字段");
		datagrid.setShowTitle(true);
		
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 150);
		addColumn(datagrid, "groupName", "分组", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "header", "标题", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "propertyName", "字段", ControlTypes.TEXT_BOX, 100);
		
		column = addColumn(datagrid, "controlType", "控件类型", ControlTypes.ENUM_BOX, 100);{
			
			String formatter = EnumUtil.getColumnFormatter(ControlTypes.class);
			column.setFormatter(formatter);
		}
		
		addColumn(datagrid, "required", "必输", ControlTypes.CHECK_BOX, 60);
		addColumn(datagrid, "reference.name", "参照", ControlTypes.REFERENCE_BOX, 100);
		addColumn(datagrid, "refFilter", "参照过滤", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "precision", "数据精度", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "maxLength", "最大长度", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "defaultValue", "默认值", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "height", "高度", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "width", "宽度", ControlTypes.NUMBER_BOX, 80);
//		addColumn(datagrid, "rowSpan", "占用行", ControlTypes.NUMBER_BOX, 80);
//		addColumn(datagrid, "columnSpan", "占用列", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "customControlType", "自定义控件", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "fullColumn", "占满列", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "readonly", "只读", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "visible", "显示", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "system", "系统", ControlTypes.BOOLCOMBO_BOX, 60);

		PPart part = new PPart();
		{
			part.toNew();

			part.setCode("fields");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("fields");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(formDetailToolbar);
			part.setJsController(formDetailPartJsController);
			part.setServiceController(formDetailPartServiceController);
			part.setImports(formDetailPartImportJs);
			part.setWindowWidth(800);
			part.setWindowHeight(400);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:250px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("表单字段");
		}
		addFormField(form, "groupName", "分组",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "header", "标题",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "propertyName", "字段",null, ControlTypes.TEXT_BOX, true, false);
		
		addFormField(form, "controlType", "控件类型",null, ControlTypes.ENUM_BOX, true, false);
		addFormFieldRefrence(form, "reference.name", "参照", null, PReference.class.getSimpleName(), false, false);
		addFormField(form, "refFilter", "参照过滤",null, ControlTypes.TEXT_BOX, false, false);
		
		addFormField(form, "customControlType", "自定义控件",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "precision", "数据精度",null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "maxLength", "最大长度",null, ControlTypes.NUMBER_BOX, false, false);
		
		addFormField(form, "defaultValue", "默认值",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "rowSpan", "占用行",null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "columnSpan", "占用列",null, ControlTypes.NUMBER_BOX, false, false);
		
		addFormField(form, "height", "高度",null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "width", "宽度",null, ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "required", "必输",null, ControlTypes.CHECK_BOX, false, false);

		addFormField(form, "fullColumn", "占满列",null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "readonly", "只读",null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "system", "系统",null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "visible", "显示",null, ControlTypes.CHECK_BOX, false, false);
		
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
