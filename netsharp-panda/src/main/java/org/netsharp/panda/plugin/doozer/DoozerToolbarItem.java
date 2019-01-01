package org.netsharp.panda.plugin.doozer;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.panda.controls.toolbar.ToolbarItem;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class DoozerToolbarItem implements IDoozer {
	
	IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);

	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {

		if (codon.getDisabled()) {
			return null;
		}

		if (node == null) {
			node = codon.getResourceNode();
		}
		
		if(codon.getResourceNode()!=null){
			node = codon.getResourceNode();
		}

		boolean isPermission = PermissionHelper.isPermission(node, codon.getOperationId(), codon.getOperationType(),codon.getOperationType2());

		if (!isPermission) {
			return null;
		}

		PToolbarItem pcodon = (PToolbarItem) codon;
		ToolbarItem item = new ToolbarItem();
		{
			item.id = caller + codon.getCode();
			item.setCode(codon.getCode());
			item.setClassName(pcodon.getClassName());
			item.value = codon.getName();
			item.IsPopup = pcodon.isPopup();
			item.IsSplitter = pcodon.isSplitter();
		}

		if (!StringManager.isNullOrEmpty(pcodon.getCommand())) {
			item.OnClick = pcodon.getCommand().replace("{controller}", caller.toString());
		}

		if (!StringManager.isNullOrEmpty(pcodon.getIcon())) {
			item.Icon = pcodon.getIcon();
		}

		if (pcodon.isPopup()) {
			for(Codonable subCodon : codon.getChildrens()){
				ToolbarItem subItem = (ToolbarItem)this.buildItem(caller, node, subCodon, subItems);
				item.getControls().add(subItem);
			}
			
//			if(item.getControls().size()==0){
//				return null;
//			}
		}
		
		return item;
	}
}