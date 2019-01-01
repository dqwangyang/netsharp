package com.netsharp.communication.tcp.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.BusinessException;

import com.netsharp.communication.tcp.base.IOrderService;
import com.netsharp.communication.tcp.entity.Order;

public class OrderService implements IOrderService {
	
	public Order byId(Object id) {
		Order order = new Order();
		order.addItems();
		
		return order;
	}

	public Order save(Order order) {
		order.setCode("000000000000000002");
		
		return order;
	}

	public List<Order> queryList(String filter) {
		
		List<Order> orders = new ArrayList<Order>();
		
		for(int i=0;i<1000;i++) {
			Order order = new Order();
			order.addItems();
			
			orders.add(order);
		}
		
		return orders;
	}

	public Integer queryCount(String filter) {
		return 1245;
	}
	
	public void delete()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
//			throw new NetsharpException("can't delete");
		}
	}
	
	public void audit() {
		throw new BusinessException("audit failed!!!!!");
	}

}
