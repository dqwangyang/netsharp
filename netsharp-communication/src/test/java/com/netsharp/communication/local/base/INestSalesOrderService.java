package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;

import com.netsharp.entity.SalesOrder;

/***
 * 服务嵌套调用测试类
 * */
public interface INestSalesOrderService extends IPersistableService<SalesOrder> {
	
	SalesOrder testSave(SalesOrder order);

}