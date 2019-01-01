package org.netsharp.meta.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

/**
 * @ClassName: WorkspaceCreationBase
 * @Description:工作区元数据创建测试用例
 * @author: 韩伟
 * @date: 2017年8月24日 上午10:40:28
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public abstract class WorkspaceCreationBase {

	/**
	 * @Fields entity : 实体
	 */
	protected Class<?> entity = null;
	/**
	 * @Fields meta : 实体元数据
	 */
	protected Mtable meta = null;
	/**
	 * @Fields urlList : 列表路径
	 */
	protected String urlList = null;
	/**
	 * @Fields urlForm : 表单路径
	 */
	protected String urlForm = null;
	/**
	 * @Fields partName : 部件名称
	 */
	protected String formPartName = null;

	/**
	 * @Fields resourceNodeCode : 资源编码
	 */
	protected String resourceNodeCode;
	/**
	 * @Fields listPartType : 部件类型
	 */
	protected Integer listPartType = PartType.DATAGRID_PART.getId();
	/**
	 * @Fields formIsCache : 表单是否缓存
	 */
	protected boolean formIsCache = false;
	/**
	 * @Fields listIsCache : 列表是否缓存
	 */
	protected boolean listIsCache = false;

	/**
	 * @Fields formToolbarPath : 表单工具栏
	 */
	protected String formToolbarPath = "panda/form/edit";
	/**
	 * @Fields formServiceController : 表单服务端控制器
	 */
	protected String formServiceController = null;
	/**
	 * @Fields formJsController : 表单客户端控制器
	 */
	protected String formJsController = null;
	/**
	 * @Fields formJsImport : 表单扩展JS文件路径
	 */
	protected String formJsImport = null;
	/**
	 * @Fields listToolbarPath : 列表页工具栏
	 */
	protected String listToolbarPath = "panda/datagrid/edit";
	/**
	 * @Fields listFilter : 列表默认过滤条件
	 */
	protected String listFilter = "";
	/**
	 * @Fields listName : 列表工作区名称
	 */
	protected String listPartName = "";
	/**
	 * @Fields listPartJsController : 列表客户端控制器
	 */
	protected String listPartJsController = "";
	/**
	 * @Fields listPartImportJs : 列表扩展JS文件路径
	 */
	protected String listPartImportJs = "";
	/**
	 * @Fields listPartServiceController : 列表服务端控制器
	 */
	protected String listPartServiceController = "";
	/**
	 * @Fields formOpenMode : 表单打开方式（默认打开浏览器页签）
	 */
	protected OpenMode formOpenMode = OpenMode.OPEN;
	/**
	 * @Fields openWindowWidth : 弹出窗口宽度
	 */
	protected Integer openWindowWidth = 600;
	/**
	 * @Fields openWindowHeight : 弹出窗口高度
	 */
	protected Integer openWindowHeight = 450;

	/**
	 * @Fields resourceService : 资源服务
	 */
	protected IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
	/**
	 * @Fields workspaceService : 工作区服务
	 */
	protected IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class);
	/**
	 * @Fields operationService : 操作服务
	 */
	protected IOperationService operationService = ServiceFactory.create(IOperationService.class);

	/**
	 * @Fields referenceService : 参照服务
	 */
	protected IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);

	/**
	 * @Fields toolbarService : 工具栏服务
	 */
	protected IPToolbarService toolbarService = ServiceFactory.create(IPToolbarService.class);

	protected IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);

	protected Integer workspaceOperationType1Id = getWorkspaceOperationTypeId();

	private Integer getWorkspaceOperationTypeId() {

		OperationType opt = operationTypeService.byCode(OperationTypes.view);
		if (opt != null) {

			return opt.getId();
		}

		return null;
	}

	@Before
	public void setup() {

		entity = null;
		urlList = null;
		urlForm = null;
		formPartName = null;
		resourceNodeCode = null;
		listToolbarPath = "panda/datagrid/edit";
		listFilter = null;
		listPartName = null;
		listPartJsController = null;
		listPartImportJs = null;
		listPartServiceController = null;
		formToolbarPath = "panda/form/edit";
		formServiceController = null;
	}

	@Test
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}

	protected void createListWorkspace() {

		if (StringManager.isNullOrEmpty(urlList)) {

			return;
		}

		ResourceNode node = this.getResourceNode();
		Mtable meta = MtableManager.getMtable(this.entity);
		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(listPartName);
			workspace.setUrl(urlList);
			workspace.setCached(listIsCache);
			workspace.setOperationTypeId(workspaceOperationType1Id);
		}
		createListWorkspaceParts(node, meta, workspace);

		workspaceService.save(workspace);
	}

	protected PPart createListWorkspaceParts(ResourceNode node, Mtable meta, PWorkspace workspace) {

		PDatagrid datagrid = this.createDatagrid(node);

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode(ReflectManager.getFieldName(meta.getCode()) + "List");
			part.setResourceNode(node);
			part.setName(listPartName);
			part.setPartTypeId(listPartType);
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setUrl(urlForm);
			part.setOpenMode(this.formOpenMode);

			if (this.formOpenMode == OpenMode.WINDOW) {

				part.setWindowWidth(this.openWindowWidth);
				part.setWindowHeight(this.openWindowHeight);
			}

			if (!StringManager.isNullOrEmpty(listToolbarPath)) {

				part.setToolbar(listToolbarPath);
			}

			if (!StringManager.isNullOrEmpty(listPartJsController)) {

				part.setJsController(listPartJsController);
			}

			if (!StringManager.isNullOrEmpty(listPartServiceController)) {

				part.setServiceController(listPartServiceController);
			}

			if (!StringManager.isNullOrEmpty(listPartImportJs)) {

				part.setImports(listPartImportJs);
			}
		}

		workspace.getParts().add(part);

		return part;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PQueryProject queryProject = this.createQueryProject(node);
		PQueryProject advancedQueryProject = this.createAdvancedQueryProject(node);
		PDatagrid datagrid = new PDatagrid(node, listPartName);
		{
			datagrid.setPageSize(20);
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			datagrid.setFilter(listFilter);
			datagrid.setNowrap(true);
			datagrid.setQueryProject(queryProject);
			datagrid.setAdvancedQueryProject(advancedQueryProject);
		}

		createRowStyler(datagrid);
		return datagrid;
	}

	/**
	 * @Title: addColumn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param datagrid
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param controlType
	 * @param: @param width
	 * @param: @return
	 * @return: PDatagridColumn
	 * @throws
	 */
	protected PDatagridColumn addColumn(PDatagrid datagrid, String propertyName, String header, ControlTypes controlType, int width) {
		return addColumn(datagrid, propertyName, header, controlType, width, false);
	}

	/**
	 * @Title: addColumn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param datagrid
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param controlType
	 * @param: @param width
	 * @param: @param isFrozen
	 * @param: @return
	 * @return: PDatagridColumn
	 * @throws
	 */
	protected PDatagridColumn addColumn(PDatagrid datagrid, String propertyName, String header, ControlTypes controlType, int width, Boolean isFrozen) {
		return addColumn(datagrid, propertyName, header, controlType, width, isFrozen, null, null, null);
	}

	/**
	 * @Title: addColumn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param datagrid
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param controlType
	 * @param: @param width
	 * @param: @param isFrozen
	 * @param: @param isReadOnly
	 * @param: @param referenceId
	 * @param: @param groupName
	 * @param: @param orderbyMode
	 * @param: @return
	 * @return: PDatagridColumn
	 * @throws
	 */
	protected PDatagridColumn addColumn(PDatagrid datagrid, String propertyName, String header, ControlTypes controlType, int width, Boolean isFrozen, Integer referenceId, String groupName,
			OrderbyMode orderbyMode) {

		PDatagridColumn column = new PDatagridColumn();
		{
			column.toNew();
			column.setPropertyName(propertyName);
			column.setHeader(header);
			column.setControlType(controlType);
			column.setFrozen(isFrozen);
			column.setWidth(width);
			column.setGroupName(groupName);
			column.setOrderbyMode(orderbyMode);
			datagrid.getColumns().add(column);
		}

		if (controlType == ControlTypes.DATE_BOX) {

			column.setFormatter(" if(value){return new Date(value.replace(/-/g, \'/\')).format(\'yyyy-MM-dd\');}");
			column.setWidth(80);
			column.setAlign(DatagridAlign.CENTER);
		}

		if (controlType == ControlTypes.BOOLCOMBO_BOX) {

			column.setFormatter(" return value==true?'是':'否';");
			column.setWidth(60);
			column.setAlign(DatagridAlign.CENTER);
		}

		if (controlType == ControlTypes.DECIMAL_FEN_BOX) {

			column.setAlign(DatagridAlign.RIGHT);
			String formatter = "return (value/100).toFixed(2);";
			column.setFormatter(formatter);
		}

		if (controlType == ControlTypes.DATETIME_BOX) {

			column.setWidth(150);
			column.setAlign(DatagridAlign.CENTER);

		} else if (controlType == ControlTypes.DECIMAL_BOX || controlType == ControlTypes.CURRENCY_BOX || controlType == ControlTypes.NUMBER_BOX || controlType == ControlTypes.PERCENTAGE_BOX) {

			column.setAlign(DatagridAlign.RIGHT);

		} else if (controlType == ControlTypes.CHECK_BOX || controlType == ControlTypes.ENUM_BOX || controlType == ControlTypes.OPERATION_COLUMN) {

			column.setAlign(DatagridAlign.CENTER);
		}

		return column;
	}

	/**
	 * @Title: addColumn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param datagrid
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param refrenceClazz
	 * @param: @param width
	 * @param: @param isFrozen
	 * @param: @param isReadOnly
	 * @param: @param referenceId
	 * @param: @param groupName
	 * @param: @param orderbyMode
	 * @param: @return
	 * @return: PDatagridColumn
	 * @throws
	 */
	protected PDatagridColumn addRefrenceColumn(PDatagrid datagrid, String propertyName, String header, Class<?> refrenceClazz, int width, Boolean isFrozen, Integer referenceId, String groupName,
			OrderbyMode orderbyMode) {

		PDatagridColumn column = new PDatagridColumn();
		{
			column.toNew();
			column.setPropertyName(propertyName);
			column.setHeader(header);
			column.setControlType(ControlTypes.REFERENCE_BOX);
			column.setFrozen(isFrozen);
			column.setWidth(width);
			column.setGroupName(groupName);
			column.setOrderbyMode(orderbyMode);

			PReference reference = referenceService.byCode(refrenceClazz.getSimpleName());
			column.setReference(reference);
			datagrid.getColumns().add(column);
		}
		return column;
	}

	protected PDatagridColumn addColumn(PDatagrid datagrid, String propertyName, String header, ControlTypes controlType, int width, OrderbyMode orderbyMode) {

		PDatagridColumn column = new PDatagridColumn();
		column.toNew();
		column.setPropertyName(propertyName);
		column.setHeader(header);
		column.setControlType(ControlTypes.REFERENCE_BOX);
		column.setFrozen(false);
		column.setWidth(width);
		column.setReadonly(false);
		column.setOrderbyMode(orderbyMode);
		datagrid.getColumns().add(column);
		return column;
	}

	// ------------------------------------------------Form------------------------------------------------------

	/**
	 * @Title: createFormWorkspace
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return: void
	 * @throws
	 */
	protected void createFormWorkspace() {

		if (StringManager.isNullOrEmpty(urlForm)) {

			return;
		}

		Mtable meta = MtableManager.getMtable(this.entity);
		ResourceNode node = this.getResourceNode();
		PForm pform = this.createForm(node);
		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(formPartName);
			workspace.setUrl(urlForm);
			workspace.setCached(formIsCache);
			workspace.setOperationTypeId(workspaceOperationType1Id);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode(ReflectManager.getFieldName(meta.getCode()));
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.FORM_PART.getId());
			part.setForm(pform);
			part.setName(formPartName);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(formToolbarPath);

			if (!StringManager.isNullOrEmpty(this.formServiceController)) {
				part.setServiceController(formServiceController);
			}

			if (!StringManager.isNullOrEmpty(this.formJsController)) {
				part.setJsController(formJsController);
			}

			if (!StringManager.isNullOrEmpty(this.formJsImport)) {
				part.setImports(this.formJsImport);
			}
		}
		workspace.getParts().add(part);
		this.addDetailGridPart(workspace);
		workspaceService.save(workspace);
	}

	/**
	 * @Title: addDetailFormPart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param workspace
	 * @return: void
	 * @throws
	 */
	protected void addDetailGridPart(PWorkspace workspace) {

	}

	/**
	 * @Title: createForm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param node
	 * @param: @return
	 * @return: PForm
	 * @throws
	 */
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, node.getName());
		form.toNew();
		form.setColumnCount(3);
		form.setName(formPartName);
		return form;
	}

	/**
	 * @Title: addFormField
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param form
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param groupName
	 * @param: @param controlType
	 * @param: @param isRequired
	 * @param: @return
	 * @return: PFormField
	 * @throws
	 */
	protected PFormField addFormField(PForm form, String propertyName, String header, String groupName, ControlTypes controlType, Boolean isRequired) {
		return addFormField(form, propertyName, header, groupName, controlType, isRequired, false);
	}

	/**
	 * @Title: addFormFieldRefrence
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param form
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param groupName
	 * @param: @param refrenceClazz
	 * @param: @param isRequired
	 * @param: @param isReadOnly
	 * @param: @return
	 * @return: PFormField
	 * @throws
	 */
	protected PFormField addFormFieldRefrence(PForm form, String propertyName, String header, String groupName, String refrenceCode, Boolean isRequired, Boolean isReadOnly) {

		PFormField formField = addFormField(form, propertyName, header, groupName, ControlTypes.REFERENCE_BOX, isRequired, isReadOnly);
		PReference reference = referenceService.byCode(refrenceCode);
		formField.setReference(reference);
		formField.setReferenceCode(refrenceCode);
		return formField;
	}

	/**
	 * @Title: addFormField
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param form
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param groupName
	 * @param: @param controlType
	 * @param: @param isRequired
	 * @param: @param isReadOnly
	 * @param: @return
	 * @return: PFormField
	 * @throws
	 */
	protected PFormField addFormField(PForm form, String propertyName, String header, String groupName, ControlTypes controlType, Boolean isRequired, Boolean isReadOnly) {

		PFormField formField = new PFormField();
		formField.toNew();
		formField.setPropertyName(propertyName);
		formField.setHeader(header);
		formField.setGroupName(groupName);
		formField.setControlType(controlType);
		formField.setRequired(isRequired);
		formField.setReadonly(isReadOnly);

		if (controlType == ControlTypes.TEXTAREA) {

			formField.setFullColumn(true);
			formField.setHeight(50);
		}

		if (controlType == ControlTypes.SWITCH_BUTTON) {
			formField.setWidth(60);
		}

		form.getFields().add(formField);
		return formField;
	}

	/**
	 * @Title: addFormField
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param form
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param tip
	 * @param: @param groupName
	 * @param: @param controlType
	 * @param: @param isRequired
	 * @param: @param isReadOnly
	 * @param: @return
	 * @return: PFormField
	 * @throws
	 */
	protected PFormField addFormField(PForm form, String propertyName, String header, String tip, String groupName, ControlTypes controlType, Boolean isRequired, Boolean isReadOnly) {

		PFormField formField = new PFormField();
		formField.toNew();
		formField.setPropertyName(propertyName);
		formField.setHeader(header);
		formField.setGroupName(groupName);
		formField.setControlType(controlType);
		formField.setRequired(isRequired);
		formField.setReadonly(isReadOnly);
		formField.setTooltip(tip);

		form.getFields().add(formField);
		return formField;
	}

	/**
	 * @Title: addFormField
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param form
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param controlType
	 * @param: @param isRequired
	 * @param: @param readonly
	 * @param: @return
	 * @return: PFormField
	 * @throws
	 */
	protected PFormField addFormField(PForm form, String propertyName, String header, ControlTypes controlType, Boolean isRequired, boolean readonly) {
		return addFormField(form, propertyName, header, null, controlType, isRequired, readonly);
	}

	// -----------------------------Query------------------------------------------

	/**
	 * @Title: createQueryProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param node
	 * @param: @return
	 * @return: PQueryProject
	 * @throws
	 */
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		// queryProject.toNew();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		return queryProject;
	}

	/**
	 * @Title: 高级查询
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param node
	 * @param: @return
	 * @return: PQueryProject
	 * @throws
	 */
	protected PQueryProject createAdvancedQueryProject(ResourceNode node) {
		return null;
	}

	/**
	 * @Title: addQueryItem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @return: PQueryItem
	 * @throws
	 */
	protected PQueryItem addQueryItem(PQueryProject queryProject, String propertyName, String header, ControlTypes controlType) {

		PQueryItem queryItem = new PQueryItem();
		queryItem.toNew();
		queryItem.setPropertyName(propertyName);
		queryItem.setHeader(header);
		queryItem.setControlType(controlType);
		queryProject.getQueryItems().add(queryItem);
		return queryItem;
	}

	/**
	 * @Title: addRefrenceQueryItem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param queryProject
	 * @param: @param propertyName
	 * @param: @param header
	 * @param: @param refrenceCode
	 * @param: @return
	 * @return: PQueryItem
	 * @throws
	 */
	protected PQueryItem addRefrenceQueryItem(PQueryProject queryProject, String propertyName, String header, String refrenceCode) {

		PQueryItem queryItem = new PQueryItem();
		queryItem.toNew();
		queryItem.setPropertyName(propertyName);
		queryItem.setHeader(header);
		queryItem.setControlType(ControlTypes.REFERENCE_BOX);
		PReference reference = referenceService.byCode(refrenceCode);
		queryItem.setReferenceCode(refrenceCode);
		queryItem.setReference(reference);
		queryProject.getQueryItems().add(queryItem);
		return queryItem;
	}

	/**
	 * @Title: addToolbarItem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param toolbar
	 * @param: @param code
	 * @param: @param name
	 * @param: @param icon
	 * @param: @param commandCode
	 * @param: @param operationType
	 * @param: @param seq
	 * @param: @return
	 * @return: PToolbarItem
	 * @throws
	 */
	protected PToolbarItem addToolbarItem(PToolbar toolbar, String code, String name, String icon, String command, OperationType operationType, int seq) {

		PToolbarItem toolbarItem = new PToolbarItem();
		toolbarItem.setCode(code);
		toolbarItem.setName(name);
		toolbarItem.setIcon("fa " + icon);
		toolbarItem.setCommand("{controller}." + command + ";");
		toolbarItem.setOperationType(operationType);
		toolbarItem.setSeq(seq);
		toolbar.getItems().add(toolbarItem);
		return toolbarItem;
	}

	/**
	 * @Title: createRowStyler
	 * @Description: 创建行样式
	 * @param: @param datagrid
	 * @return: void
	 * @throws
	 */
	protected void createRowStyler(PDatagrid datagrid) {

	}

	protected PRowStyler addRowStyler(PDatagrid datagrid, String rowCondition, String rowStyle) {

		if (datagrid.getStylers() == null) {

			List<PRowStyler> stylers = new ArrayList<PRowStyler>();
			datagrid.setStylers(stylers);
		}

		PRowStyler styler = new PRowStyler();
		styler.toNew();
		styler.setRowCondition(rowCondition);
		styler.setRowStyle(rowStyle);
		datagrid.getStylers().add(styler);

		return styler;
	}

	/**
	 * @Title: operation
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return: void
	 * @throws
	 */
	@Test
	public void operation() {
		this.doOperation();
	}

	/**
	 * @Title: doOperation
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return: void
	 * @throws
	 */
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperations(node);
	}

	/**
	 * 资源节点
	 * 
	 * @Fields resourceNode : 资源节点
	 */
	private ResourceNode resourceNode = null;

	/**
	 * @Title: getResourceNode
	 * @Description: 根据资源编码获取资源节点
	 * @param: @param code
	 * @param: @return
	 * @return: ResourceNode
	 * @throws
	 */
	protected ResourceNode getResourceNode(String code) {

		ResourceNode resourceNode = resourceService.byCode(code);
		return resourceNode;
	}

	/**
	 * @Title: getResourceNode
	 * @Description: 获取资源节点
	 * @param: @return
	 * @return: ResourceNode
	 * @throws
	 */
	protected ResourceNode getResourceNode() {

		if (resourceNode == null) {

			resourceNode = this.getResourceNode(resourceNodeCode);
			if (resourceNode == null) {
				resourceNode = this.getResourceNode(entity.getSimpleName());
			}
		}
		return resourceNode;
	}
}
