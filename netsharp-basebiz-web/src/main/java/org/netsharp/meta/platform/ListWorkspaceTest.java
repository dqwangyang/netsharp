package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.AggregateType;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.web.PDatagridFormPart;
import org.netsharp.web.PlatformGridProjectDetailPart;

public class ListWorkspaceTest extends WorkspaceCreationBase {

	String formDetailToolbar = "panda/platform/form/detail";

	String formDetailPartImportJs = "/panda-platform/js/platform-tool-detail-part.js|/panda-platform/js/platform-gridproject-detail-part.js";

	String formDetailPartJsController = PlatformGridProjectDetailPart.class.getName();

	String formDetailPartServiceController = PlatformGridProjectDetailPart.class.getName();

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/datagrid/list";
		urlForm = "/platform/datagrid/form";
//		formJsImport = "/panda-platform/js/platform-tool-form-part.js";
		entity = PDatagrid.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "列表方案";
		resourceNodeCode = PDatagrid.class.getSimpleName();

		this.formServiceController = PDatagridFormPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(10);
			datagrid.setOrderby("createTime desc");
		}

		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "resourceNode.entityId", "entityId", ControlTypes.TEXT_BOX, 300, true);

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

		PFormField formField = null;

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "iconCls", "图标", ControlTypes.TEXT_BOX, false, false);

		formField = addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);{
			
//			formField.setTroikaTrigger("resourceNodeChange(newValue, oldValue);");
		}
		addFormFieldRefrence(form, "queryProject.name", "简单查询方案", null, PQueryProject.class.getSimpleName(), false, false);
		addFormFieldRefrence(form, "advancedQueryProject.name", "高级查询方案", null, PQueryProject.class.getSimpleName(), false, false);

		addFormField(form, "pageSize", "分页数", ControlTypes.NUMBER_BOX, false, false);
		addFormField(form, "filter", "过滤条件", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "sortName", "默认排序", ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "groupField", "Row分组字段", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "groupFormatter", "Row分组格式化", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "toolbar", "行工具栏", ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "nowrap", "禁止换行", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "showFooter", "显示底部", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "remember", "记忆列宽", ControlTypes.SWITCH_BUTTON, false, false);

		addFormField(form, "autoQuery", "自动查询", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "showCheckbox", "复选框", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "singleSelect", "单选", ControlTypes.SWITCH_BUTTON, false, false);

		addFormField(form, "fit", "自适应列宽", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "showTitle", "显示标题", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "pagination", "分页", ControlTypes.SWITCH_BUTTON, false, false);

		formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		{
			formField.setHeight(50);
			formField.setFullColumn(true);
		}

		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		//
		ResourceNode node = this.resourceService.byCode(PDatagridColumn.class.getSimpleName());
		PDatagrid datagrid = this.createColumnsDetail(node);
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("栏目");
			part.setCode("columns");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("columns");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(formDetailToolbar);
			part.setJsController(formDetailPartJsController);
			part.setServiceController(formDetailPartServiceController);
			part.setImports(formDetailPartImportJs);
			
			part.setWindowWidth(900);
			part.setWindowHeight(450);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		//
		node = this.resourceService.byCode(PRowStyler.class.getSimpleName());
		datagrid = this.createStyleDetail(node);
		part = new PPart();
		{
			part.toNew();
			part.setCode("stylers");
			part.setName("样式");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("stylers");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(formDetailToolbar);
			part.setWindowWidth(450);
			part.setWindowHeight(300);
			PForm form = this.createDetailGridStylerForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:400px");
	}

	private PDatagrid createColumnsDetail(ResourceNode node) {
		PDatagrid datagrid = new PDatagrid(node, "列表栏目");
		PDatagridColumn column = null;

		addColumn(datagrid, "groupName", "分组", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "header", "标题", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "propertyName", "字段", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "width", "宽度", ControlTypes.NUMBER_BOX, 80);
		column = addColumn(datagrid, "controlType", "控件类型", ControlTypes.ENUM_BOX, 100);
		{
			String formatter = EnumUtil.getColumnFormatter(ControlTypes.class);
			column.setFormatter(formatter);
		}
		column = addColumn(datagrid, "align", "对齐方式", ControlTypes.ENUM_BOX, 100);
		{
			String formatter = EnumUtil.getColumnFormatter(DatagridAlign.class);
			column.setFormatter(formatter);
		}
		column = addColumn(datagrid, "orderbyMode", "排序方式", ControlTypes.ENUM_BOX, 100);
		{
			String formatter = EnumUtil.getColumnFormatter(OrderbyMode.class);
			column.setFormatter(formatter);
		}
		column = addColumn(datagrid, "aggregateType", "合计类型", ControlTypes.ENUM_BOX, 100);
		{
			String formatter = EnumUtil.getColumnFormatter(AggregateType.class);
			column.setFormatter(formatter);
		}
		addColumn(datagrid, "visible", "显示", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "system", "系统", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "frozen", "冻结列", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "imported", "导入字段", ControlTypes.BOOLCOMBO_BOX, 60);
		addColumn(datagrid, "styler", "显示样式", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "formatter", "显示格式", ControlTypes.TEXT_BOX, 200);

		return datagrid;
	}

	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("工具栏项目");
		}
		addFormField(form, "groupName", "分组", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "header", "标题", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "propertyName", "字段", null, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "styler", "样式", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "formatter", "格式化", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "width", "宽度", null, ControlTypes.NUMBER_BOX, false, false);

		addFormField(form, "controlType", "控件类型", null, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "align", "对齐方式", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "orderbyMode", "排序方式", null, ControlTypes.ENUM_BOX, false, false);

		addFormField(form, "aggregateType", "合计类型", null, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "frozen", "冻结列", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "filterable", "显示过滤", null, ControlTypes.CHECK_BOX, false, false);

		addFormField(form, "imported", "导入字段", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "visible", "显示", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "system", "系统", null, ControlTypes.CHECK_BOX, false, false);

		return form;
	}

	private PDatagrid createStyleDetail(ResourceNode node) {

		PDatagrid datagrid = new PDatagrid(node, "列表样式");

		addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 150, false, null, null, null);
		addColumn(datagrid, "rowCondition", "条件", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "rowStyle", "样式", ControlTypes.TEXT_BOX, 200);
		return datagrid;
	}

	protected PForm createDetailGridStylerForm(ResourceNode node) {

		PForm form = new PForm();
		{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("行样式");
		}

		addFormField(form, "rowCondition", "条件", null, ControlTypes.TEXTAREA, true);
		addFormField(form, "rowStyle", "样式", null, ControlTypes.TEXTAREA, true);
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
