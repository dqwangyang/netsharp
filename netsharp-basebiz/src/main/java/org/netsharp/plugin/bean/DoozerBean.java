package org.netsharp.plugin.bean;

import java.util.List;

import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class DoozerBean implements IDoozer {
	
	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {
		
		Bean bean = (Bean)codon;
		
		Object obj = ReflectManager.newInstance(bean.getType());
		
		return obj;
	}
}
