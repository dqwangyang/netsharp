package com.netsharp.communication.local;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;

import com.netsharp.communication.local.base.IMetaService;
import com.netsharp.communication.local.base.INestSalesOrderService;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;


public class DataTableTest {

	@Before
	public void setup() {

		IMetaService service = ServiceFactory.create(IMetaService.class);
		service.recreateTable();
	}

	@Test
	public void testNest() {

		INestSalesOrderService orderService = ServiceFactory.create(INestSalesOrderService.class);
		
		for(int k=0;k<10;k++) {
			
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
			
			orderService.save(order);
		}
		
		Oql oql = new Oql();
		{
			oql.setSelects("SalesOrder.*");
		}
		
		DataTable table = orderService.queryTable(oql);
		
		Assert.assertEquals(table.size(), 10);
	}

}
