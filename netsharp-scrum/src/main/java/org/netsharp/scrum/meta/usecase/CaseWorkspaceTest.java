package org.netsharp.scrum.meta.usecase;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
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
import org.netsharp.scrum.entity.UseCase;
import org.netsharp.scrum.entity.UseCaseDetail;
import org.netsharp.scrum.entity.Project;
import org.netsharp.util.ReflectManager;

public class CaseWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {
		urlList = "/scrum/case/list";
		urlForm = "/scrum/case/form";
		resourceNodeCode = UseCase.class.getSimpleName();
		entity = UseCase.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = "用例";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(25);
		datagrid.setToolbar("panda/datagrid/row/edit");

		PDatagridColumn column = null;

		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "project.name", "项目", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "project.product.name", "产品", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "owner.name", "负责人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "organization.name", "部门", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);

		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName(formPartName);
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "project.name", "项目", Project.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "owner.name", "负责人", Employee.class.getSimpleName());
		addQueryItem(queryProject, "status", "任务状态", ControlTypes.ENUM_BOX);
		addRefrenceQueryItem(queryProject, "organization.pathName", "部门", "Organization-Department");

		return queryProject;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(UseCaseDetail.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		addColumn(datagrid, "name", "用例名", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "actor", "相关者", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "concern", "关注点", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "preCondition", "前置条件", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "steps", "主执行步骤", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "postCondition", "后置条件", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "roles", "业务规则", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "fields", "关键字段", ControlTypes.TEXT_BOX, 200);

		PPart part = new PPart();
		{
			part.setCode("details");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("details");
			part.setResourceNode(node);
			part.setName("子用例");
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);

			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		PPart part1 = workspace.getParts().get(0);
		{
			part1.setCode(ReflectManager.getFieldName(meta.getCode()));
			part1.setName("用例信息");
			part1.setStyle("height:340px;");
			part1.setDockStyle(DockType.DOCUMENTHOST);
		}
	}

	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("子用例");

		addFormField(form, "name", "用例名", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "actor", "相关者", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "concern", "关注点", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "preCondition", "前置条件", ControlTypes.TEXTAREA, true, false);
		addFormField(form, "steps", "主执行步骤", ControlTypes.TEXTAREA, true, false);
		addFormField(form, "postCondition", "后置条件", ControlTypes.TEXTAREA, true, false);
		addFormField(form, "roles", "业务规则", ControlTypes.TEXTAREA, false, false);
		addFormField(form, "fields", "关键字段", ControlTypes.TEXTAREA, false, false);

		for (int i = 2; i < form.getFields().size(); i++) {
			PFormField field = form.getFields().get(i);
			field.setFullColumn(true);
			field.setHeight(60);
		}

		return form;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, "用例表单");
		{
			form.setColumnCount(1);
		}

		PFormField field = addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true, true);
		field = addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "project.name", "项目", null, Project.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "owner.name", "负责人", null, Employee.class.getSimpleName(), true, false);
		field = addFormField(form, "status", "任务状态", null, ControlTypes.ENUM_BOX, false, false);
		field = addFormField(form, "content", "内容", null, ControlTypes.TEXTAREA, false, false);

		field.setHeight(500);

		return form;
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
	}
}
