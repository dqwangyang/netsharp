package com.netsharp.communication.local.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.netsharp.communication.local.base.ICustomerService;
import com.netsharp.entity.Customer;

@Service
public class CustomerService extends PersistableService<Customer> implements ICustomerService {

	public CustomerService() {
		super();
		this.type = Customer.class;
		
	}
}
