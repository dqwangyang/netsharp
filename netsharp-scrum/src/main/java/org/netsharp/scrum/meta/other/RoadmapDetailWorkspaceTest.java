package org.netsharp.scrum.meta.other;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.scrum.entity.RoadmapDetail;

public class RoadmapDetailWorkspaceTest  extends WorkspaceCreationBase {

	@Before
	public void setup() {
		// 在子类中重定义
		entity = RoadmapDetail.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "计划路线图明细";
		resourceNodeCode = entity.getSimpleName();
		
		this.urlList="/scrum/roadmap/detail/list";
		this.urlForm="/scrum/roadmap/detail/form";
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, "项目列表");
		form.setColumnCount(2);
		
		// 基本信息
		PFormField field = addFormField(form, "roadmap.name", "路线图", null, ControlTypes.REFERENCE_BOX, true);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Roadmap.class.getSimpleName());
			field.setReference(reference);
		}
		
		addFormField(form, "name", "名称",null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "date", "时间",null, ControlTypes.TEXT_BOX,true, false);
		addFormField(form, "status", "状态",null, ControlTypes.ENUM_BOX,true, false);

        // 操作信息
		addFormField(form, "creator", "创建人", null, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", null, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "updator", "最后修改人",null, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updateTime", "最后修改时间", null, ControlTypes.DATE_BOX, false, true);
		field = addFormField(form, "memoto", "内容",null, ControlTypes.TEXTAREA,true, false);
		{
			field.setFullColumn(true);
			field.setHeight(100);
		}
		

		return form;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		// 基本信息
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "roadmap.name", "路线图", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "date", "时间", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100, true);
		addColumn(datagrid, "memoto", "内容", ControlTypes.TEXT_BOX, 300, true);
		
		// 操作信息
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 120, false);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATE_BOX, 120, false);
		
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName(meta.getName());
			queryProject.setResourceNode(node);
		}
		queryProject.setColumnCount(3);
		
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "roadmap.name", "路线图", Roadmap.class.getSimpleName());
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);

		return queryProject;
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