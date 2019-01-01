package com.netsharp.communication.tcp.entity;

import java.util.ArrayList;
import java.util.List;

public class Order extends BizEntity {
	
	private static final long serialVersionUID = 7772461562388276579L;
	
	private Customer customer = new Customer();
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	public void addItems() {
		
		for(int i=0;i<10;i++) {
			
			OrderItem item = new OrderItem();
			this.items.add(item);
			
		}
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
