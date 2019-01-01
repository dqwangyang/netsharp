package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.web.PlatformQueryProjectDetailPart;

public class QueryProjectWorkspace extends WorkspaceCreationBase {

	String formDetailToolbar = "panda/platform/form/detail";

	String formDetailPartImportJs = "/panda-platform/js/platform-tool-detail-part.js|/panda-platform/js/platform-queryproject-detail-part.js";

	String formDetailPartJsController = PlatformQueryProjectDetailPart.class.getName();

	String formDetailPartServiceController = PlatformQueryProjectDetailPart.class.getName();

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/queryproject/list";
		urlForm = "/platform/queryproject/form";
//		formJsImport = "/panda-platform/js/platform-tool-form-part.js";
		entity = PQueryProject.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = PQueryProject.class.getSimpleName();
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
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "columnCount", "栏目数", ControlTypes.NUMBER_BOX, 100);
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

		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		{
			form.setColumnCount(3);
		}

		//PFormField formField = null;
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);{
			
//			formField.setTroikaTrigger("resourceNodeChange(newValue, oldValue);");
		}
		addFormField(form, "columnCount", "栏目数", ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "labelWidth", "标题宽度", ControlTypes.NUMBER_BOX, true, false);

		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(PQueryItem.class.getSimpleName());

		PDatagrid datagrid = new PDatagrid(node, "查询项");
		datagrid.setShowTitle(true);
		PDatagridColumn column = null;

		addColumn(datagrid, "header", "标题", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "propertyName", "字段", ControlTypes.TEXT_BOX, 200);

		column = addColumn(datagrid, "controlType", "控件类型", ControlTypes.ENUM_BOX, 100);
		{
			String formatter = EnumUtil.getColumnFormatter(ControlTypes.class);
			column.setFormatter(formatter);
		}

		addColumn(datagrid, "defaultValue", "默认值", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "customControlType", "自定义控件", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "reference.name", "参照", ControlTypes.REFERENCE_BOX, 200);
		addColumn(datagrid, "refFilter", "参照过滤", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "required", "必输", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "validated", "有效输入", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "customOql", "自定义OQL", ControlTypes.BOOLCOMBO_BOX, 100);
		addColumn(datagrid, "multiple", "多选", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "shortcut", "快捷键", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "interzone", "区间", ControlTypes.BOOLCOMBO_BOX, 80);

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("queryItems");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("queryItems");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
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

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:200px");
	}

	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("表单字段");
		}
		addFormField(form, "groupName", "分组", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "header", "标题", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "propertyName", "字段", null, ControlTypes.TEXT_BOX, true, false);

		addFormField(form, "controlType", "控件类型", null, ControlTypes.ENUM_BOX, true, false);
		addFormFieldRefrence(form, "reference.name", "参照", null, PReference.class.getSimpleName(), false, false);
		addFormField(form, "refFilter", "参照过滤", null, ControlTypes.TEXT_BOX, false, false);

		addFormField(form, "defaultValue", "默认值", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "customControlType", "自定义控件", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "required", "必输", null, ControlTypes.CHECK_BOX, false, false);

		addFormField(form, "validated", "有效输入", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "readonly", "只读", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "customOql", "自定义OQL", null, ControlTypes.CHECK_BOX, false, false);

		addFormField(form, "multiple", "多选", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "shortcut", "快捷键", null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "interzone", "区间", null, ControlTypes.CHECK_BOX, false, false);
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