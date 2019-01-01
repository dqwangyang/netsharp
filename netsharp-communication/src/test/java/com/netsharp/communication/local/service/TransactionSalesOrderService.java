package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.communication.local.base.ISalesDeliveryService;
import com.netsharp.communication.local.base.ISalesInvoiceService;
import com.netsharp.communication.local.base.ITransactionSalesOrderService;
import com.netsharp.entity.Customer;
import com.netsharp.entity.SalesOrder;

@Service
public class TransactionSalesOrderService extends PersistableService<SalesOrder> implements ITransactionSalesOrderService {

	public TransactionSalesOrderService() {
		super();
		this.type=SalesOrder.class;
	}

	
	@Transaction
	public SalesOrder testSave(SalesOrder order) {
		//
		Customer c = new Customer();
		{
			c.setCode("0001");
			c.setName("0001");
			c.toNew();
		}
		
		ICustomerService customerServicve = ServiceFactory.create(ICustomerService.class);
		c = customerServicve.save(c);
		
		order.setCustomerId(c.getId());
		order.setCustomer(c);
		
		//
		this.pm.save(order);
		
		ISalesDeliveryService deliveryService = ServiceFactory.create(ISalesDeliveryService.class);
		deliveryService.transactionSave(order);
		
		ISalesInvoiceService invoiceService = ServiceFactory.create(ISalesInvoiceService.class);
		invoiceService.transactionSave(order);
		
		
		return order;
	}

}
