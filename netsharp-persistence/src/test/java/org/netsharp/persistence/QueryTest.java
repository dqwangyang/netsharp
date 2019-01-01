package org.netsharp.persistence;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.Oql;
import org.netsharp.util.DateManage;

import com.netsharp.entity.Customer;
import com.netsharp.entity.Inventory;
import com.netsharp.entity.SalesOrder;

public class QueryTest {

	@Before
	public void setup()  {
		IPersister<Customer> pm = PersisterFactory.create();

		for (int i = 0; i < 10; i++) {
			Customer c = new Customer();
			c.setId(-1);
			c.setCode("00" + (i + 10));
			c.toNew();

			pm.save(c);
		}
	}

	@Test
	public void connect() {
//		System.out.println(this.getClass().getName());
	}

	@Test
	public void in()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("Id IN (1,2,3)");

		pm.queryList(oql);
	}

	@Test
	public void or1()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("Id=1 or id=2");

		pm.queryList(oql);
	}

	@Test
	public void or2()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("code='001' and ( Id=1 or id=2)");

		pm.queryList(oql);
	}

	@Test
	public void is()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("Id is null");

		pm.queryList(oql);
	}

	@Test
	public void isnot()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("Id is not null");

		pm.queryList(oql);
	}

	@Test
	public void morethanOrEqueals()  {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		oql.setType(Customer.class).setSelects("Customer.*").setFilter("Id>=12");

		pm.queryList(oql);
	}

	@Test
	public void columnMap() {
		IPersister<SalesOrder> pst = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("SalesOrder.*");
			oql.setFilter("savedAmount > 2 and saved_amount < 100 and customer.district_name = '华北' and customer.districtName='华东'");
		}

		pst.queryList(oql);
	}

	@Test
	public void refSelect() {
		IPersister<SalesOrder> pst = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("SalesOrder.{code,savedAmount},customer.!{createTime,updateTime}");
			oql.setFilter("savedAmount > 2 and saved_amount < 100 and customer.district_name = '华北' and customer.districtName='华东'");
		}

		pst.queryList(oql);
	}

	@Test
	public void subSelect() {
		IPersister<SalesOrder> pst = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("SalesOrder.*,customer.*,details.*,details.inventory.*");
			oql.setFilter("savedAmount > 2 and saved_amount < 100 and customer.district_name = '华北' and customer.districtName='华东'");
		}

		pst.queryList(oql);
	}

	@Test
	public void function1() {

		// String date = DateManage.toString(DateManage.addDays(new Date(),
		// 1),"yyyyMMdd");
		String date = DateManage.toString(new Date(), "yyyyMMdd");

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(Customer.class);
			oql.setSelects("Customer.*");
			oql.setFilter("DATE_FORMAT(create_Time,'%Y%m%d')='" + date + "'");
		}

		List<Customer> customers = pm.queryList(oql);

		Assert.assertTrue(customers.size() > 0);

		for (Customer c : customers) {
			Assert.assertNotNull(c.getCreateTime());
		}
	}

	@Test
	public void function2() {

		IPersister<Customer> pm = PersisterFactory.create();

		String date = DateManage.toString(new Date(), "yyyyMMdd");

		Oql oql = new Oql();
		{
			oql.setType(Customer.class);
			oql.setSelects("Customer.id");
			oql.setFilter("'" + date + "'=DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 3 DAY),'%Y%m%d')");
		}

		pm.queryList(oql);
	}

	@Test
	public void function3() {

		IPersister<Customer> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(Customer.class);
			oql.setSelects("Customer.id");
			oql.setFilter("DATE_FORMAT(create_Time,  '%Y%m%d')=DATE_FORMAT(DATE_SUB(NOW() , INTERVAL 3 DAY),'%Y%m%d')");
		}

		pm.queryList(oql);
	}

	@Test
	public void distinct1() {
		
		IPersister<SalesOrder> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("distinct SalesOrder.code");
			oql.setFilter("createTime < NOW()");
		}

		pm.queryList(oql);
	}
	
	@Test
	public void distinct2() {
		
		IPersister<SalesOrder> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("distinct SalesOrder.code");
			oql.setFilter("customer.createTime < NOW()");
		}

		pm.queryList(oql);
	}
	
	@Test
	public void orderby1() {
		
		IPersister<SalesOrder> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("SalesOrder.*");
			oql.setOrderby("SalesOrder.amount desc");
		}

		pm.queryList(oql);
	}
	
	@Test
	public void orderby2() {
		
		IPersister<SalesOrder> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(SalesOrder.class);
			oql.setSelects("SalesOrder.*");
			oql.setOrderby("amount desc");
		}

		pm.queryList(oql);
	}
	
	@Test
	public void like() {
		
		IPersister<Inventory> pm = PersisterFactory.create();

		Oql oql = new Oql();
		{
			oql.setType(Inventory.class);
			oql.setSelects("Inventory.*");
			oql.setFilter("name like '%()%'");
		} 

		pm.queryList(oql);
	}
}
