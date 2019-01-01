package org.netsharp.scrum.meta.bug;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.scrum.entity.Project;
import org.netsharp.util.ReflectManager;

public class BugWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/bug/list";
		urlForm = "/bug/form";

		entity = Bug.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode = "bugList";
		listPartName = formPartName = "研发缺陷";
		formJsImport = "/addins/scrum/bugFormPart.js";
		formJsController = "org.netsharp.scrum.web.BugFormPart";
		listPartServiceController = AdvancedListPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		datagrid.setPageSize(25);
		
		addRowStyler(datagrid,"row.importance == '重要'","color:red;");

		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);

		column = addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, true);
		{
			column.setImported(true);
		}

		column = addColumn(datagrid, "type", "类型", ControlTypes.TEXT_BOX, 80);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "project.name", "项目", ControlTypes.TEXT_BOX, 150);
		{
			column.setImported(true);
		}
		
		column = addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "urgency", "紧急性", ControlTypes.ENUM_BOX, 100);
		{
			column.setImported(true);
		}
		addColumn(datagrid, "priority", "优先级", ControlTypes.ENUM_BOX, 100);
		column = addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "actualHours", "耗时(小时)", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}

		column = addColumn(datagrid, "testor.name", "测试员", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "developer.name", "开发者", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "putor.name", "提出人", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}

		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		{
			column.setImported(true);
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		{
			column.setImported(true);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("缺陷");
			queryProject.setResourceNode(node);
		}

		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "name", "缺陷名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "testor.name", "测试员", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "developer.name", "开发者", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "project.name", "项目", Project.class.getSimpleName());
		addQueryItem(queryProject, "status", "状态", ControlTypes.ENUM_BOX);{
			
			
		}
		addQueryItem(queryProject, "importance", "重要性", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "urgency", "紧急性", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "priority", "优先级", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);

		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		PFormField field = null;
		addFormFieldRefrence(form, "project.name", "项目", null,Project.class.getSimpleName(), true, false);
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		field = addFormField(form, "type", "类型", ControlTypes.ENUM_BOX, true, false);
		{

			//field.setDataOptions("type:'" + BugType.class.getName() + "'");
		}
		addFormField(form, "status", "状态", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "importance", "重要性", ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "urgency", "紧急性", ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "priority", "优先级", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "estimateHours", "估时（小时）", ControlTypes.DECIMAL_BOX, true, false);
		addFormField(form, "actualHours", "耗时（小时）", ControlTypes.DECIMAL_BOX, false, false);
		addFormFieldRefrence(form, "testor.name", "测试人员", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "developer.name", "开发人员", null, Employee.class.getSimpleName(), true, false);
		addFormField(form, "filePath", "图片1", ControlTypes.OSS_UPLOAD, false, false);
		addFormField(form, "filePath1", "图片2", ControlTypes.OSS_UPLOAD, false, false);
		addFormField(form, "filePath2", "图片3", ControlTypes.OSS_UPLOAD, false, false);
		field = addFormField(form, "filePath3", "图片4", ControlTypes.OSS_UPLOAD, false, false);
		{

//			addFormField(form, "name", "只读名称", ControlTypes.LABEL, false, false);
//			addFormField(form, "project.name", "项目", ControlTypes.LABEL, false, false);

			// UploaderFilters uf = new UploaderFilters();{
			//
			// uf.setMaxFileSize("10240kb");//文件大小
			// uf.setPreventDuplicates(false);//是否可选择重复文件
			// MimeType mt = new MimeType();
			// mt.setTitle("Image files");//标题
			// mt.setExtensions("jpg,gif,png");//文件类型
			//
			// List<MimeType> mimeTypes = new ArrayList<MimeType>();
			// mimeTypes.add(mt);
			// uf.setMimeTypes(mimeTypes);
			// }
			// String filters = JsonManage.serialize2(uf).replaceAll("\"", "'");
			// field.setDataOptions(filters);
		}

		field = addFormField(form, "content", "缺陷内容", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		field = addFormField(form, "service", "开发说明", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("bug-devp-log");
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;
		column = addColumn(datagrid, "createTime", "操作时间", ControlTypes.TEXT_BOX, 130);
		{

			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "creator", "操作人", ControlTypes.TEXT_BOX, 100);
		{

			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 500);

		PPart part = new PPart();
		{
			part.setCode("logs");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("logs");
			part.setResourceNode(node);
			part.setName("缺陷日志");
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);

		PPart part1 = workspace.getParts().get(0);
		{
			part1.setCode(ReflectManager.getFieldName(meta.getCode()));
			part1.setName("项目缺陷");
			part1.setDockStyle(DockType.DOCUMENTHOST);
		}
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.exportExcel);
		service.addOperation(node, OperationTypes.attachment);
	}
}
