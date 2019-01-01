package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.basebiz.organization.OrganizationWorkspaceTest;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.controller.authorization.operation.OperationAuthorizationPart;
import org.netsharp.organization.controller.organization.OrganizationTreePart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

public class AuthorizationWorkspaceTest extends OrganizationWorkspaceTest {

	@Before
	public void setup() {
		
		super.setup();
		urlList = "/system/authorization/tree";
		urlForm = null;
		resourceNodeCode = "OrganizationEmployee";
	}

	@Test
	@Override
	public void run() {

		this.createTreeWorkspace();
	}
	
	@Override
	public void createTreeWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("授权管理");
			workspace.setUrl(urlList);
		}
		ResourceNode node1 = resourceService.byCode(Organization.class.getSimpleName());

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:300px;");
			part.setResourceNode(node1);
			part.setToolbar("panda/tree");
			part.setUrl("/system/organization/form");

			part.setServiceController(OrganizationTreePart.class.getName());
			part.setJsController(OrganizationTreePart.class.getName());
		}
		workspace.getParts().add(part);

		part = new PPart();
		{
			part.toNew();
			part.setCode("Permission");
			part.setParentCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREEGRID_PART.getId());
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("organizationId");
			part.setResourceNode(node1);
			part.setServiceController(OperationAuthorizationPart.class.getName());
			part.setJsController(OperationAuthorizationPart.class.getName());
		}

		workspace.getParts().add(part);

		workspaceService.save(workspace);
	}

	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}
