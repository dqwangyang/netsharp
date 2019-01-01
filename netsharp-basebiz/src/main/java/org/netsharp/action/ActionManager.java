package org.netsharp.action;

import java.util.List;

import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.core.AddInTree;

/*说明：如果Action通过annotation拦截的话，需要处理跟缓存的拦截冲突*/
public class ActionManager {
	
	public void execute(ActionContext ctx){
		
		List<Object> codons = AddInTree.buildItems(BeanPath.class, null, ctx.getPath(),this);
		
		for(Object codon : codons){
			IAction action = (IAction)codon;
			action.execute(ctx);
		}
	}
}
