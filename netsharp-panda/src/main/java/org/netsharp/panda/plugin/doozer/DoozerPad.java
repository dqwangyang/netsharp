package org.netsharp.panda.plugin.doozer;

import java.util.List;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.tab.TabItem;
import org.netsharp.panda.core.pad.IPad;
import org.netsharp.panda.plugin.entity.PPad;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class DoozerPad implements IDoozer {

	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {

		if (codon.getDisabled()) {
			return null;
		}

		if (node == null) {
			node = codon.getResourceNode();
		}

		boolean isPermission = PermissionHelper.isPermission(node, codon.getOperationId(), codon.getOperationType(), codon.getOperationType2());

		if (!isPermission) {
			return null;
		}

		PPad ppad = (PPad) codon;

		IPad pad = (IPad) ReflectManager.newInstance(ppad.getType());

		if (!pad.condition()) {
			return null;
		}

		TabItem tabItem = new TabItem();
		{
			tabItem.iconCls = ppad.getIcon();
			tabItem.title = ppad.getName();
			tabItem.setStyle("padding-bottom:1px;");
		}

		Control control = pad.create();

		tabItem.getControls().add(control);

		return tabItem;
	}
}