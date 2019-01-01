package com.netsharp.communication.local.base;

import org.netsharp.base.IPersistableService;

import com.netsharp.entity.SalesInvoice;
import com.netsharp.entity.SalesOrder;

public interface ISalesInvoiceService extends IPersistableService<SalesInvoice>{
	void nestSave(SalesOrder order);
	void transactionSave(SalesOrder order);
}
