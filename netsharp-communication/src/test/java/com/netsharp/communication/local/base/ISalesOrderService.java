package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.netsharp.entity.SalesOrder;

public interface ISalesOrderService extends IPersistableService<SalesOrder> {
	
    String hello(String value);
    
    void createTable() ;
    
    @Transaction
    void save() ;
    
    @Transaction
    void failSave() ;
    
    @Transaction
    void invokeService() ;
    
    int primivateTest(float f);
}
