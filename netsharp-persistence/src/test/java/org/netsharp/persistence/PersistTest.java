package org.netsharp.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;

import com.netsharp.entity.Color;
import com.netsharp.entity.Customer;
import com.netsharp.entity.Inventory;
import com.netsharp.entity.InventoryType;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;


public class PersistTest{
	
	@Test
	public void createCustomer() {
		
		IPersister<IPersistable> pm=PersisterFactory.create();
		Customer customer=new Customer();
		Inventory inventory1=new Inventory();
		Inventory inventory2=new Inventory();
		
		customer.setId(1);
		customer.setCode("001");
		customer.setName("李莉");
		customer.setDistrictName("华东地区");
		customer.toNew();
		
		pm.save(customer);
		System.out.println(customer.getId());
		Assert.assertTrue(customer.getId().intValue()!=1);
		Assert.assertEquals(customer.getEntityState(), EntityState.Transient);
		
		//
		inventory1.setCode("001");
		inventory1.setName("金美孚1号");
		inventory1.setCreateTime(new Date());
		inventory1.setColor(Color.red);
		inventory1.toNew();
		pm.save(inventory1);
		
		inventory2.setCode("002");
		inventory2.setName("刹车片");
		inventory2.setCreateTime(new Date());
		inventory2.setColor(Color.blue);
		inventory2.toNew();
		pm.save(inventory2);
		
		Oql oql=new Oql();
		oql.setType(Inventory.class);
		oql.setSelects("Inventory.*");
		
		List<IPersistable> inventorys= pm.queryList(oql);
		Assert.assertEquals(inventorys.size(), 2);
		
		int count= pm.queryCount(oql);
		Assert.assertTrue(count==2);
		
		//
		SalesOrder order=new SalesOrder();
		order.setId(-1);
		order.setCode("001");
		order.setCreateTime(new Date());
		order.setAmount(BigDecimal.valueOf(34.56));
		order.setCustomer(customer);
		order.setSavedAmount(BigDecimal.valueOf(10.12));
		order.toNew();
		
		SalesOrderDetail detail1=new SalesOrderDetail();
		order.getDetails().add(detail1);
		
		detail1.setPrice(BigDecimal.valueOf(23));
		detail1.setPrice(BigDecimal.valueOf(1.1));
		detail1.setAmount(BigDecimal.valueOf(23*1.1));
		detail1.setInventory(inventory1);
		detail1.toNew();
		
		
		SalesOrderDetail detail2=new SalesOrderDetail();
		order.getDetails().add(detail2);
		
		detail2.setPrice(BigDecimal.valueOf(23));
		detail2.setPrice(BigDecimal.valueOf(1.1));
		detail2.setAmount(BigDecimal.valueOf(23*1.1));
		detail2.setInventory(inventory2);
		detail2.toNew();
		
		pm.save(order);
		
		Assert.assertNotSame(-1,order.getId());

        for(SalesOrderDetail detail : order.getDetails()){
        	Assert.assertEquals(order.getId(), detail.getIdOrder());
        }
        
        //
        SalesOrder dbOrder=(SalesOrder)pm.byId(SalesOrder.class.getName(),order.getId());
        
        Assert.assertNotNull(dbOrder);
        Assert.assertNotNull(dbOrder.getCustomer());
        Assert.assertEquals(order.getCode(), dbOrder.getCode());
        Assert.assertNotNull(dbOrder.getDetails());
        Assert.assertEquals(dbOrder.getDetails().size(), 2);
        
        for(SalesOrderDetail detail : dbOrder.getDetails()){
        	Assert.assertEquals(dbOrder.getId(), detail.getIdOrder());
        }
        
//        Assert.assertEquals(dbOrder.getDetails().get(0).getInventory().getColor(), Color.red);
        Assert.assertEquals(dbOrder.getDetails().get(0).getInventory().getType(), InventoryType.product);
	}
	
	@Test
	public void bulk() {
		
		IPersister<Customer> pm=PersisterFactory.create();
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(int i=0;i<1000;i++) {
			
			Customer customer=new Customer();
			{
				customer.setCode("00"+i);
				customer.setName("李莉"+i);
				customer.setDistrictName("华东地区");
				customer.toNew();
			}
			
			customers.add(customer);
		}
		
		pm.bulk(customers,true);
	}
}
