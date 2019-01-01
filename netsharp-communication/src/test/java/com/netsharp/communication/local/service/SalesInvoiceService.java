package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.core.NetsharpException;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ISalesInvoiceService;
import com.netsharp.entity.SalesInvoice;
import com.netsharp.entity.SalesOrder;

@Service
public class SalesInvoiceService extends PersistableService<SalesInvoice> implements ISalesInvoiceService {
	
	public SalesInvoiceService() {
		super();
		this.type = SalesInvoice.class;
	}

	public void nestSave(SalesOrder order) {
		
		SiContext ctx = SiContext.getCurrent();
		if(ctx.Top == ctx) {
			throw new NetsharpException("ISalesInvoiceService不能为top");
		}
		
		if(ctx.Top != ctx.Parent) {
			throw new NetsharpException("ISalesInvoiceService的parent必须为top");
		}
		
		String name = ISalesInvoiceService.class.getName()+".nestSave";
		
		 if(!name.equals(ctx.Name)) {
			 throw new NetsharpException("ISalesInvoiceServices上下问的ctx.name不正确，"+ctx.Name);
		 }
		 
		 //
		SalesInvoice delivery = new SalesInvoice();
		{
			delivery.toNew();
			delivery.setAmount(order.getAmount());
			delivery.setCustomerId(order.getCustomerId());
			delivery.setPrice(order.getPrice());
			delivery.setQuantity(order.getQuantity());
		}
		
		this.pm.save(delivery);
	}

	@Override
	public void transactionSave(SalesOrder order) {
		SalesInvoice delivery = new SalesInvoice();
		{
			delivery.toNew();
			delivery.setAmount(order.getAmount());
			delivery.setCustomerId(order.getCustomerId());
			delivery.setPrice(order.getPrice());
			delivery.setQuantity(order.getQuantity());
		}
		
		this.pm.save(delivery);
	}
}
