package org.netsharp.plugin.core;

import java.util.List;

import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;

/*插件项生成器*/
public interface IDoozer {
	
	Object buildItem(Object caller, ResourceNode node,Codonable codon, List<Object> subItems);
	
}
