package org.netsharp.persistence;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;

import com.netsharp.entity.Customer;
import com.netsharp.entity.Inventory;
import com.netsharp.entity.SalesOrder;
import com.netsharp.entity.SalesOrderDetail;

public class TestDropTable {
	
	private final Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void dropTable()  {
		ArrayList<Mtable> clss = new ArrayList<Mtable>();

		clss.add(MtableManager.getMtable(SalesOrder.class));
		clss.add(MtableManager.getMtable(SalesOrderDetail.class));
		clss.add(MtableManager.getMtable(Customer.class));
		clss.add(MtableManager.getMtable(Inventory.class));

		IDataAccess dao = DataAccessFactory.create();

		for (Mtable meta : clss) {

			String cmdText = "drop table " + meta.getTableName();

			try {
				dao.executeUpdate(cmdText, null);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
	}
}
