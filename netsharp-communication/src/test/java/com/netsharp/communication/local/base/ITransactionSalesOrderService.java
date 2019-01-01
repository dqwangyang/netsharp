package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.netsharp.entity.SalesOrder;

/***
 * 事务调用测试类
 * */
public interface ITransactionSalesOrderService extends IPersistableService<SalesOrder> {
	
	@Transaction
	SalesOrder testSave(SalesOrder order);

}
