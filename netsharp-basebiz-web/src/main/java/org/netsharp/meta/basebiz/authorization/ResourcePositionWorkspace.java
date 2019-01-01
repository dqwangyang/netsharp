package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourcePositionWorkspace extends WorkspaceCreationBase{

	@Before
	public void setup() {
		
		urlList = "/system/resource/position/tree";
		entity = ResourceNode.class;
		listPartName = formPartName = "权限岗位";
		resourceNodeCode = "ResourcePosition";
	}

	@Test
	public void run() {

		this.createListWorkspace();
	}

	public void createListWorkspace() {

		ResourceNode node = resourceService.byCode("ResourcePosition");
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("权限岗位");
			workspace.setUrl(urlList);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("resourcePositionTree");
			part.setPartTypeId(PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:250px;");
			part.setResourceNode(node);
			part.setToolbar(null);
			part.setUrl("/system/resource/position/form");
		}
		workspace.getParts().add(part);

		node = resourceService.byCode(Organization.class.getSimpleName());
		PDatagrid datagrid = this.createDatagrid(node);
		part = new PPart();
		{
			part.toNew();
			part.setCode("resourcePositionList");
			part.setParentCode("resourcePositionTree");
			part.setPartTypeId(PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("resource.id");
			part.setResourceNode(node);
		}

		workspace.getParts().add(part);
		workspaceService.save(workspace);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setShowTitle(true);
		datagrid.setName("岗位列表");
		datagrid.setFilter("organizationType=" + OrganizationType.POST.getValue());
		datagrid.setAutoQuery(false);
		addColumn(datagrid, "pathName", "岗位名称", ControlTypes.TEXT_BOX, 600);
		return datagrid;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
