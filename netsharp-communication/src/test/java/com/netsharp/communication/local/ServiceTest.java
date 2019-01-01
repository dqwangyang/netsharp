package com.netsharp.communication.local;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.dataccess.DbSession;
import org.netsharp.util.StringManager;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.communication.local.base.ISalesOrderService;
import com.netsharp.entity.Customer;

public class ServiceTest {

	@Test
	public void create() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		assertTrue(service != null);
	}

	@Test
	public void hello() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		assertTrue(service != null);

		String value = "netsharp.com";

		String ret = service.hello(value);

		Assert.assertTrue(StringManager.equals(ret, "hello " + value));

		Assert.assertNull(DbSession.getSession());
	}

	@Test
	public void transactionAnnocation() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);

		service.createTable();

		service.hello("netsharp.com");
		Assert.assertNull(DbSession.getSession());

		service.save();
		Assert.assertNull(DbSession.getSession());

		service.hello("netsharp.com");
		Assert.assertNull(DbSession.getSession());

		service.save();
		Assert.assertNull(DbSession.getSession());

		service.save();
		Assert.assertNull(DbSession.getSession());

		service.save();
		Assert.assertNull(DbSession.getSession());
	}

	@Test
	public void queryAndSaveTransaction() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

		Oql oql = new Oql();
		oql.setType(Customer.class);
		oql.setSelects("Customer.*");

		service.createTable();
		service.save();
		List<Customer> c = customerService.queryList(oql);

		Assert.assertEquals(c.size(), 10);

		service.save();
		c = customerService.queryList(oql);

		Assert.assertEquals(c.size(), 20);
	}

	@Test
	public void rullback() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

		Oql oql = new Oql();
		oql.setType(Customer.class);
		oql.setSelects("Customer.*");

		service.createTable();
		service.save();

		try {
			service.failSave();
		} catch (Exception ex) {

		}
		List<Customer> c = customerService.queryList(oql);

		Assert.assertEquals(c.size(), 10);

		service.save();
		c = customerService.queryList(oql);

		Assert.assertEquals(c.size(), 20);
	}

	@Test
	public void serviceInvokeService() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

		Oql oql = new Oql();
		oql.setType(Customer.class);
		oql.setSelects("Customer.*");

		service.createTable();
		service.save();
		service.invokeService();

		List<Customer> c = customerService.queryList(oql);

		Assert.assertEquals(c.size(), 20);
	}

	@Test
	public void primivateTest() {

		ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
		int i = service.primivateTest(4f);

		Assert.assertEquals(i, 3);
	}
}
