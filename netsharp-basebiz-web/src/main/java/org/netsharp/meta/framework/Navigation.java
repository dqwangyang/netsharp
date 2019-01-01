package org.netsharp.meta.framework;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.panda.plugin.entity.PAccordionItem;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;


public class Navigation {
	
	@Before
	public void setup(){
		
		List<Mtable> metas = new ArrayList<Mtable>();
		
		metas.add(MtableManager.getMtable(PAccordion.class));
		metas.add(MtableManager.getMtable(PAccordionItem.class));
		metas.add(MtableManager.getMtable(PNavigation.class));
		metas.add(MtableManager.getMtable(PNavigationItem.class));
		
    	IDb db=DbFactory.create();
    	for(Mtable meta : metas){
    		db.reCreateTable(meta);
    	}
	}
	
	@Test
    public void test(){}
	
}
