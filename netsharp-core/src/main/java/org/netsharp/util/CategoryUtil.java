package org.netsharp.util;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Category;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.entity.IPersistable;

public class CategoryUtil {
	
	private Mtable meta ;
	private Category catMeta;
	
	public List<IPersistable> listToTree(List<IPersistable> objs){
		
		List<IPersistable> tops = new ArrayList<IPersistable>();
		
		if(objs.size()==0){
			return tops;
		}
		
		Class<?> type = objs.get(0).getClass();
		this.meta = MtableManager.getMtable(type);
		this.catMeta = meta.getCategory();
		
		for(IPersistable obj : objs){
			Object parentId = catMeta.getParentId(obj);
			if(catMeta.getId().isEmpty(parentId)){
				tops.add(obj);
			}
		}
		
		return tops;
	}
	
	public List<IPersistable> getChildren(IPersistable obj,List<IPersistable> objs){
		if(this.meta == null){
			this.meta    = MtableManager.getMtable(obj.getClass());
			this.catMeta = meta.getCategory();
		}
		
		Object id = this.meta.getId(obj);
		
		ArrayList<IPersistable> children = new ArrayList<IPersistable>();
		for(IPersistable item : objs){
			Object parentId = catMeta.getParentId(item);
			if(id.equals(parentId)){
				children.add(item);
			}
		}
		
		return children;
	}
}
