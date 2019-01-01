package org.netsharp.persistence;

import java.util.ArrayList;

import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;

import com.netsharp.entity.Customer;
import com.netsharp.entity.Inventory;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;

public class TestCreateTable {

	@Test
	public void createTable() {
		   
	   ArrayList<Mtable> clss=new ArrayList<Mtable>(); 
	   
	   clss.add(MtableManager.getMtable(SalesOrder.class));
	   clss.add(MtableManager.getMtable(SalesOrderDetail.class));
	   clss.add(MtableManager.getMtable(Customer.class));
	   clss.add(MtableManager.getMtable(Inventory.class));
	   
	   IDb db= DbFactory.create();
	   for(Mtable meta : clss){
		   db.createTable(meta);
	   }
    }

}
