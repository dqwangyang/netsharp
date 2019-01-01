package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.controller.RoleAuthorizationListPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.Role;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleAuthorizationWorkspaceTest extends WorkspaceCreationBase{
	
	@Before
	public void setup() {

		urlList = "/system/role/authorization";
		
		entity = Role.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = entity.getSimpleName();
		formPartName = listPartName = meta.getName();
		this.listPartImportJs="/panda-bizbase/organization/org-role-authorization-part.js";
	}
	
	@Test
	@Override
	public void run() {
		this.createPermissionWorkspace();
	}

	public void createPermissionWorkspace() {
		
		String urlPermissionForm = "/role/permission/form";
		
		ResourceNode node = resourceService.byCode(OrganizationEmployee.class.getSimpleName());
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("角色授权");
			workspace.setUrl(urlPermissionForm);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("Permission");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setResourceNode(node);
			part.setServiceController(RoleAuthorizationListPart.class.getName());
			part.setJsController(RoleAuthorizationListPart.class.getName());
			part.setImports("/panda-bizbase/role/role-authorization-list-part.js");
		}
		workspace.getParts().add(part);
		workspaceService.save(workspace);
	}
	
	@Override
	protected void doOperation() {
		

	}
}
