package org.netsharp.dataccess;

import java.util.Date;

import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.BackupOption;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.util.DateManage;

import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderExt;

public class DbTest {
	
    @Test
    public void createTable() {
    	
    	Mtable meta= MtableManager.getMtable(SalesOrder.class);
    	
    	IDb db=DbFactory.create();
    	if(db.isTableExsist(meta.getTableName())){
    		db.dropTable(meta.getTableName());
    	}
    	
    	db.createTable(meta);
    }
    
    @Test
    public void attach(){
    	
    	this.createTable();
    	
    	Mtable meta= MtableManager.getMtable(SalesOrderExt.class);
    	
    	IDb db=DbFactory.create();

    	db.updateTable(meta,true);
    }
    
    @Test
    public void backup(){
    	
    	BackupOption option=new BackupOption();
    	{
    		option.pageSize=5000;
    		String fileName=DateManage.toFileName(new Date());
    		option.fileName="d:\\tt\\ds"+fileName+".xml";
    	}
    	
    	IDb db=DbFactory.create();
    	db.backup(option);
    }
}
