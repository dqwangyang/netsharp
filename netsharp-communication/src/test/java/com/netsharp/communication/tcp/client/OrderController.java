package com.netsharp.communication.tcp.client;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.netsharp.communication.tcp.base.IOrderService;
import com.netsharp.communication.tcp.entity.Order;

public class OrderController {

	IOrderService service = ServiceFactory.create(IOrderService.class);

	@Test
	public void byId() {
		Order order = this.service.byId(1);
		Assert.assertEquals(order.getCode(), new Order().getCode());
	}

	@Test
	public void save() {

		Order order = new Order();
		order.addItems();

		order = service.save(order);

		Assert.assertNotEquals(order.getCode(), new Order().getCode());
	}

	@Test
	public void queryList() {
		List<Order> orders = this.service.queryList("name='567'");
		Assert.assertEquals(orders.size(), 1000);
	}

	@Test
	public void queryCount() {
		Integer count = this.service.queryCount("name='567'");
		Integer i = 1245;
		Assert.assertEquals(count, i);
	}

	@Test
	public void delete() {
		this.service.delete();
	}

	@Test
	public void audit() {
		
		try {
			this.service.audit();
		}  catch (BusinessException be) {
			Assert.assertTrue(true);
		} catch (Exception ex) {
			Assert.assertTrue(false);
		}
	}

}
