//package org.netsharp.persistence;
//
//import java.sql.Types;
//
//import org.junit.Test;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.Oql;
//import org.netsharp.core.QueryParameters;
//import org.netsharp.core.util.DES;
//import org.netsharp.core.util.StringManager;
//import org.netsharp.entity.Inventory;
//import org.springframework.util.Assert;
//
//public class DESField {
//	
//	IPersister<Inventory> pm = PersisterFactory.create();
//	
//	String des="徐芳波";
//	
//	@Test
//	public void crud() {
//		
//		// 删除所有数据
//		String cmdText = "delete from "+MtableManager.getMtable(Inventory.class).getTableName();
//		pm.executeNonQuery(cmdText, null);
//		
//		// 新增存货，保存后des字段会被加密
//		Inventory i = new Inventory();
//		{
//			i.toNew();
//			
//			i.setCode("001");
//			i.setName("小棉袄");
//			i.setDes(this.des);
//		}
//		
//		pm.save(i);
//		
//		i = pm.byId(i);
//		
//		Assert.isTrue(StringManager.equals(i.getDes(), this.des));
//		
//		// sql语句中加密解析
//		Oql oql = new Oql();
//		{
//			oql.setType(Inventory.class);
//			oql.setSelects("*");
//			oql.setFilter("des = '{cryp"+this.des+"!cryp}'");
//		}
//		
//		int count = pm.queryCount(oql);
//		Assert.isTrue(count==1);
//		
//		//参数中进行加密设置
//		oql.setFilter("des=?");
//		QueryParameters qps = new QueryParameters();
//		{
//			qps.add("@des",this.des,Types.VARCHAR,true);//最后一个boolean参数为true时候，表示这是一个加密字段
//		}
//		
//		oql.setParameters(qps);
//		
//		count = pm.queryCount(oql);
//		Assert.isTrue(count==1);
//	}
//	
//	@Test
//	public void xufangbo() throws Exception {
//		
//		String value = "185145117456";
//		
//		String des = DES.encrypt(value, DES.getKey());
//		
//		String value2 = DES.decrypt(des, DES.getKey());
//		
//		Assert.isTrue(StringManager.equals(value, value2));
//	}
//	
//	
//}
