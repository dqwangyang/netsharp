package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.db.Db;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.communication.local.base.ISalesOrderService;
import com.netsharp.entity.Customer;
import com.netsharp.entity.SalesOrder;

@Service
public class SalesOrderService extends PersistableService<SalesOrder> implements ISalesOrderService {
	
	public SalesOrderService() {
		super();
		this.type = SalesOrder.class;
	}

	public String hello(String value) {

		value = "hello " + value;

		System.out.println(value);

		return value;
	}

	public void createTable() {

		Db db = new Db();
		Mtable meta = MtableManager.getMtable(Customer.class);
		if (db.isTableExsist(meta.getTableName())) {
			db.dropTable(meta.getTableName());
		}
		db.createTable(meta);
	}

	@Override
	@Transaction
	public void save() {

		IPersister<Customer> pm = PersisterFactory.create();

		for (int i = 0; i < 10; i++) {
			Customer c = new Customer();
			c.setId(-1);
			c.setCode("000" + i);
			c.toNew();

			pm.save(c);
		}
	}

	@Transaction
	public void failSave() {
		
		this.save();

		IPersister<Customer> pm = PersisterFactory.create();

		pm.executeNonQuery("asdflarutirty", null);
	}

	public void invokeService() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);

		service.save();

		ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

		Oql oql = new Oql();
		oql.setType(Customer.class);
		customerService.queryList(oql);
	}

	public int primivateTest(float f) {
		return 3;
	}
}
