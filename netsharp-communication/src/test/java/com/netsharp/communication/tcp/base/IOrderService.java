package com.netsharp.communication.tcp.base;

import java.util.List;

import com.netsharp.communication.tcp.entity.Order;

public interface IOrderService {
	
	Order byId(Object id);
	Order save(Order order);
	List<Order> queryList(String filter);
	Integer queryCount(String filter);
	void delete();
	void audit();
	
}
