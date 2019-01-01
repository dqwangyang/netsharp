package com.netsharp.communication.local.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;

import com.netsharp.communication.local.base.IMetaService;
import com.netsharp.entity.Customer;
import com.netsharp.entity.Inventory;
import com.netsharp.entity.SalesDelivery;
import com.netsharp.entity.SalesInvoice;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;
import com.netsharp.entity.SalesOrderExt;
import com.netsharp.entity.WarehouseBook;

@Service
public class MetaService implements IMetaService {
	
	public void recreateTable() {
		IDb db = DbFactory.create();
		List<Class<?>> types = new ArrayList<Class<?>>();
		{
			types.add(Customer.class);
			types.add(Inventory.class);
			types.add(SalesDelivery.class);
			types.add(SalesInvoice.class);
			types.add(SalesOrder.class);
			types.add(SalesOrderDetail.class);
			types.add(SalesOrderExt.class);
			types.add(WarehouseBook.class);
		}

		db.reCreateTable(types);
	}

}
