//package org.netsharp.meta.platform;
//
//import org.junit.Before;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.util.ReflectManager;
//import org.netsharp.meta.base.WorkspaceCreationBase;
//import org.netsharp.organization.dic.OperationTypes;
//import org.netsharp.panda.base.IPReferenceService;
//import org.netsharp.panda.controls.ControlTypes;
//import org.netsharp.panda.dic.DockType;
//import org.netsharp.panda.dic.OrderbyMode;
//import org.netsharp.panda.entity.PDatagrid;
//import org.netsharp.panda.entity.PDatagridColumn;
//import org.netsharp.panda.entity.PForm;
//import org.netsharp.panda.entity.PFormField;
//import org.netsharp.panda.entity.PPart;
//import org.netsharp.panda.entity.PQueryItem;
//import org.netsharp.panda.entity.PQueryProject;
//import org.netsharp.panda.entity.PReference;
//import org.netsharp.panda.entity.PWorkspace;
//import org.netsharp.panda.plugin.entity.PPads;
//import org.netsharp.resourcenode.entity.ResourceNode;
//
//public class PadsWorkspaceTest extends WorkspaceCreationBase {
//
//	@Override
//	@Before
//	public void setup() {
//
//		// 在子类中重定义
//		urlList = "/platform/pads/list";
//		urlForm = "/platform/pads/form";
//
//		entity = PPads.class;
//		meta = MtableManager.getMtable(entity);
//		formPartName = listPartName = meta.getName();
//		resourceNodeCode = PPads.class.getSimpleName();
//	}
//
//	@Override
//	protected PDatagrid createDatagrid(ResourceNode node) {
//
//		PDatagrid datagrid = super.createDatagrid(node);
//		{
//			datagrid.setPageSize(25);
//			datagrid.setOrderby("createTime desc");
//
//		}
//		
//		PDatagridColumn column = null;
//
//		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);
//		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
//		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
//		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 200);
//		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
//		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
//
//		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150);
//		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
//		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150);
//		{
//			column.setOrderbyMode(OrderbyMode.DESC);
//		}
//
//		return datagrid;
//	}
//
//	protected PQueryProject createQueryProject(ResourceNode node) {
//
//		PQueryProject queryProject = new PQueryProject();
//		{
//			queryProject.toNew();
//			queryProject.setName(this.meta.getName() + "查询");
//			queryProject.setResourceNode(node);
//		}
//
//		PQueryItem item = new PQueryItem();
//		{
//			item.toNew();
//			item.setPropertyName("name");
//			item.setHeader("名称");
//			item.setControlType(ControlTypes.TEXT_BOX);
//		}
//		queryProject.getQueryItems().add(item);
//		
//
//		return queryProject;
//	}
//
//	@Override
//	protected PForm createForm(ResourceNode node) {
//
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setName(this.meta.getName() + "表单");
//			form.setColumnCount(3);
//		}
//
//		PFormField field = null;
//
//		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
//		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
//		field = addFormField(form, "resourceNode.pathName", "资源节点", ControlTypes.REFERENCE_BOX, true, false);
//		{
//			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
//			PReference reference = referenceService.byCode(ResourceNode.class.getSimpleName());
//			field.setReference(reference);
//		}
//		addFormField(form, "memoto", "备注", ControlTypes.TEXT_BOX, false, false);
//
//		return form;
//	}
//
//	@Override
//	protected void addDetailGridPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(PPads.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "PAccordionItem");
//
//		addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 150, false,null, null, null);
//		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "icon", "图标", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "type", "类型", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "base", "继承上级", ControlTypes.TEXT_BOX, 300);
//		addColumn(datagrid, "parent", "显示上级", ControlTypes.TEXT_BOX, 300);
//		addColumn(datagrid, "seq", "排序", ControlTypes.NUMBER_BOX, 100);
//		addColumn(datagrid, "disabled", "停用", ControlTypes.CHECK_BOX, 100);
//
//		addColumn(datagrid, "resourceNode.pathName", "资源节点", ControlTypes.REFERENCE_BOX, 300);
//		addColumn(datagrid, "operationType.name", "操作类型", ControlTypes.REFERENCE_BOX, 100);
//		addColumn(datagrid, "operationType2.name", "操作类型2", ControlTypes.REFERENCE_BOX, 100);
//		addColumn(datagrid, "operationId", "operationId", ControlTypes.NUMBER_BOX, 100);
//		
//		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false,null, null, null);
//		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150,false, null, null, null);
//		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100,false, null, null, null);
//		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150,false, null, null, null);
//
//		//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setCode("items");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("items");
//			part.setResourceNode(node);
//			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("panda/datagrid/detail");
//			part.setWindowWidth(800);
//			part.setWindowHeight(400);
//			PForm form = this.createDetailGridForm(node);
//			part.setForm(form);
//		}
//
//		workspace.getParts().add(part);
//
//		// 设置表头表单样式
//		part = workspace.getParts().get(0);
//		part.setDockStyle(DockType.TOP);
//		part.setStyle("height:200px");
//	}
//	
//	protected PForm createDetailGridForm(ResourceNode node){
//		
//		PForm form = new PForm();
//		form.setResourceNode(node);
//		form.toNew();
//		form.setColumnCount(3);
//		form.setName("菜单子项");
//		addFormField(form, "code", "编码",null, ControlTypes.TEXT_BOX, true, false);
//		addFormField(form, "name", "名称",null, ControlTypes.TEXT_BOX, true, false);
//		addFormField(form, "url", "路径",null, ControlTypes.TEXT_BOX, false, false);
//		addFormField(form, "parent", "上级编码",null, ControlTypes.TEXT_BOX, false, false);
//		addFormField(form, "seq", "显示顺序",null, ControlTypes.NUMBER_BOX, true, false);
//		addFormField(form, "disabled", "停用",null, ControlTypes.CHECK_BOX, false, false);
//		return form;
//	}
//
//	@Override
//	protected void doOperation() {
//
//		ResourceNode node = this.getResourceNode();
//		operationService.addOperation(node, OperationTypes.view);
//		operationService.addOperation(node, OperationTypes.add);
//		operationService.addOperation(node, OperationTypes.update);
//		operationService.addOperation(node, OperationTypes.delete);
//	}
//}