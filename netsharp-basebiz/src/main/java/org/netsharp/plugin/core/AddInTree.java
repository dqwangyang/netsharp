package org.netsharp.plugin.core;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.base.IPathServcie;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class AddInTree {
	
    public static List<Object> buildItems(Class<?> pathType,ResourceNode node,String path, Object caller){
    	
    	IPathServcie pst = ServiceFactory.create(IPathServcie.class);
    	Pathable pathable = pst.byPath( path, pathType.getName() );

    	//生成插件项目
    	List<Object> items = new ArrayList<Object>();
    	for(Codonable  codon : pathable.getCodons()){
    		
    		Doozer doozerAnno = (Doozer)codon.getClass().getAnnotation(Doozer.class);
    		IDoozer doozer = (IDoozer)ReflectManager.newInstance(doozerAnno.type());
    		Object item = doozer.buildItem(caller,node, codon,null);
    		if(item==null)continue;
    		items.add(item);
    	}

    	return items;
    }
}
