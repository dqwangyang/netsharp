package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.core.BusinessException;
import org.netsharp.core.NetsharpException;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.IWarehouseBookService;
import com.netsharp.entity.SalesDelivery;
import com.netsharp.entity.WarehouseBook;

@Service
public class WarehouseBookService extends PersistableService<WarehouseBook> implements IWarehouseBookService {
	
	public WarehouseBookService() {
		super();
		this.type = WarehouseBook.class;
	}

	public void nestSave(SalesDelivery delivery) {
		
		SiContext ctx = SiContext.getCurrent();
		if(ctx.Top == ctx) {
			throw new NetsharpException("IWarehouseBookService不能为top");
		}
		if(ctx.Top != ctx.Parent.Parent) {
			throw new NetsharpException("IWarehouseBookService的parent.parent必须为top");
		}
		String name = IWarehouseBookService.class.getName()+".nestSave";
		
		 if(!name.equals(ctx.Name)) {
			 throw new NetsharpException("IWarehouseBookService上下问的ctx.name不正确，"+ctx.Name);
		 }
		WarehouseBook book = new WarehouseBook();
		{
			book.toNew();
			
			book.setPrice(delivery.getPrice());
			book.setQuantity(delivery.getQuantity());
		}
		
		this.pm.save(book);
	}
	

	public void transactionSave(SalesDelivery order) {
		throw new BusinessException("发货数量不能为负数");
	}
}
