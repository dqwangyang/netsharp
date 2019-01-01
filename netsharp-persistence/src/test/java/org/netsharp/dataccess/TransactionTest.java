package org.netsharp.dataccess;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.db.Db;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.netsharp.entity.SalesOrder;

public class TransactionTest {
	
    @Test
    public void run(){
    	
    	Db db=new Db();
    	Mtable meta= MtableManager.getMtable(SalesOrder.class);
    	if(db.isTableExsist(meta.getTableName())){
    		db.dropTable(meta.getTableName());
    	}
    	db.createTable(meta);
    	
    	DbSession session= DbSession.start();
    	session.beginTransaction();
    	
    	IPersister<SalesOrder> pm=PersisterFactory.create();
    	
    	try
    	{
    		SalesOrder order=new SalesOrder();{
    			order.setAmount(BigDecimal.valueOf(100));
    			order.setCode("SA0001");
    			order.toNew();
    		}
    		
    		pm.save(order);
    		
    		throw new NetsharpException("hello");
    	}
    	catch(Exception ex){
    		session.rollback();
    	}
    	
    	Oql oql=new Oql();
    	oql.setType(SalesOrder.class);
    	
    	int count= pm.queryCount(oql);
    	
    	Assert.assertTrue(count==0);
    	
    	session.beginTransaction();
    	try
    	{
    		SalesOrder order=new SalesOrder();{
    			order.setAmount(BigDecimal.valueOf(100));
    			order.setCode("SA0001");
    			order.toNew();
    		}
    		
    		pm.save(order);
    		session.commit();
    	}
    	catch(Exception ex){
    		session.rollback();
    	}
    	
    	count= pm.queryCount(oql);
    	
    	Assert.assertTrue(count==1);
    	
    }
}
