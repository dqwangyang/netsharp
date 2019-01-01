package org.netsharp.meta.framework;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.panda.entity.PReference;

public class ReferenceTest {
	
	@Before
	public void setup(){
    	Mtable meta= MtableManager.getMtable(PReference.class);
    	IDb db=DbFactory.create();
    	db.reCreateTable(meta);
	}
	
	@Test
	public void run()
	{
		
	}
}
