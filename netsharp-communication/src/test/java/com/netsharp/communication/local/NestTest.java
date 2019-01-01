package com.netsharp.communication.local;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.communication.local.base.IMetaService;
import com.netsharp.communication.local.base.INestSalesOrderService;
import com.netsharp.communication.local.base.ISalesDeliveryService;
import com.netsharp.communication.local.base.ISalesInvoiceService;
import com.netsharp.communication.local.base.IWarehouseBookService;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;

public class NestTest {

	@Before
	public void setup() {

		IMetaService service = ServiceFactory.create(IMetaService.class);
		service.recreateTable();
	}

	@Test
	public void testNest() {

		INestSalesOrderService orderService = ServiceFactory.create(INestSalesOrderService.class);
		ICustomerService customerService = ServiceFactory.create(ICustomerService.class);
		ISalesDeliveryService deliveryService = ServiceFactory.create(ISalesDeliveryService.class);
		ISalesInvoiceService invoiceService = ServiceFactory.create(ISalesInvoiceService.class);
		IWarehouseBookService bookService = ServiceFactory.create(IWarehouseBookService.class);

		SalesOrder order = new SalesOrder();
		{
			order.toNew();
			order.setPrice(BigDecimal.valueOf(3));
			order.setQuantity(BigDecimal.valueOf(2));
			order.setAmount(BigDecimal.valueOf(5));
			order.setSavedAmount(BigDecimal.valueOf(1));
		}

		for (int i = 0; i < 10; i++) {
			SalesOrderDetail detail = new SalesOrderDetail();
			detail.toNew();

			detail.setAmount(BigDecimal.valueOf(3));

			order.getDetails().add(detail);
		}
		orderService.testSave(order);

		Oql oql = new Oql();
		{
			oql.setSelects("id");
		}

		int count = orderService.queryCount(oql);
		Assert.assertEquals( 1,count);

		oql.setType(null);
		count = customerService.queryCount(oql);
		Assert.assertEquals( 1,count);

		oql.setType(null);
		count = deliveryService.queryCount(oql);
		Assert.assertEquals( 1,count);

		oql.setType(null);
		count = invoiceService.queryCount(oql);
		Assert.assertEquals( 1,count);

		oql.setType(null);
		count = bookService.queryCount(oql);
		Assert.assertEquals( 1,count);
	}

}
