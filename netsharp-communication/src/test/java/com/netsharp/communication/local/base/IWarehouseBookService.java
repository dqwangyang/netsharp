package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;

import com.netsharp.entity.SalesDelivery;
import com.netsharp.entity.WarehouseBook;

public interface IWarehouseBookService extends IPersistableService<WarehouseBook>{
	void nestSave(SalesDelivery delivery);
	void transactionSave(SalesDelivery order);
}
