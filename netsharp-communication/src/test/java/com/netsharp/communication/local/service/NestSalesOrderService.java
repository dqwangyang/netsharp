package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.communication.local.base.INestSalesOrderService;
import com.netsharp.communication.local.base.ISalesDeliveryService;
import com.netsharp.communication.local.base.ISalesInvoiceService;
import com.netsharp.entity.Customer;
import com.netsharp.entity.SalesOrder;

@Service
public class NestSalesOrderService extends PersistableService<SalesOrder> implements INestSalesOrderService {
	
	public NestSalesOrderService() {
		super();
		this.type=SalesOrder.class;
	}

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
		deliveryService.nestSave(order);
		
		ISalesInvoiceService invoiceService = ServiceFactory.create(ISalesInvoiceService.class);
		invoiceService.nestSave(order);
		
		
		return order;
	}

}
