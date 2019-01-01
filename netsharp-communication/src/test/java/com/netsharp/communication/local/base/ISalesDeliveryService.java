package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;

import com.netsharp.entity.SalesDelivery;
import com.netsharp.entity.SalesOrder;

public interface ISalesDeliveryService extends IPersistableService<SalesDelivery>{
	void nestSave(SalesOrder order);
	void transactionSave(SalesOrder order);
}
