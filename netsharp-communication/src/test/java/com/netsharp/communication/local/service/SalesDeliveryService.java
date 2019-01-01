package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.core.NetsharpException;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ISalesDeliveryService;
import com.netsharp.communication.local.base.IWarehouseBookService;
import com.netsharp.entity.SalesDelivery;
import com.netsharp.entity.SalesOrder;

@Service
public class SalesDeliveryService extends PersistableService<SalesDelivery> implements ISalesDeliveryService {
	
	public SalesDeliveryService() {
		super();
		this.type = SalesDelivery.class;
	}

	public void nestSave(SalesOrder order) {
		
		SiContext ctx = SiContext.getCurrent();
		if(ctx.Top == ctx) {
			throw new NetsharpException("ISalesDeliveryService不能为top");
		}
		
		if(ctx.Top != ctx.Parent) {
			throw new NetsharpException("ISalesDeliveryService的parent必须为top");
		}
		
		String name = ISalesDeliveryService.class.getName()+".nestSave";
		
		 if(!name.equals(ctx.Name)) {
			 throw new NetsharpException("ISalesDeliveryServices上下问的ctx.name不正确，"+ctx.Name);
		 }
		
		 //
		SalesDelivery delivery = new SalesDelivery();
		{
			delivery.toNew();
			delivery.setAmount(order.getAmount());
			delivery.setCustomerId(order.getCustomerId());
			delivery.setPrice(order.getPrice());
			delivery.setQuantity(order.getQuantity());
		}
		
		this.pm.save(delivery);
		
		IWarehouseBookService book = ServiceFactory.create(IWarehouseBookService.class);
		
		book.nestSave(delivery);
		
	}

	public void transactionSave(SalesOrder order) {
		SalesDelivery delivery = new SalesDelivery();
		{
			delivery.toNew();
			delivery.setAmount(order.getAmount());
			delivery.setCustomerId(order.getCustomerId());
			delivery.setPrice(order.getPrice());
			delivery.setQuantity(order.getQuantity());
		}
		
		this.pm.save(delivery);
		
		IWarehouseBookService book = ServiceFactory.create(IWarehouseBookService.class);
		
		book.transactionSave(delivery);
	}
}
