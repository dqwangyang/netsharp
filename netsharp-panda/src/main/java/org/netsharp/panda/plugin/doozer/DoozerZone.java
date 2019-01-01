package org.netsharp.panda.plugin.doozer;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DoozerZone implements IDoozer{

	IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);

	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {

		if (codon.getDisabled()) {
			return null;
		}

		if (node == null) {
			node = codon.getResourceNode();
		}

//		boolean isPermission = PermissionManager.isPermission(node, codon.getOperationId(), codon.getOperationType(),codon.getOperationType2());
//
//		if (!isPermission) {
//			return null;
//		}

		return codon;
	}
}
