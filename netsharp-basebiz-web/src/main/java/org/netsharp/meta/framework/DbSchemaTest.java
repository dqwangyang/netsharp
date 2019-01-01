package org.netsharp.meta.framework;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.application.Application;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.Table;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.util.PakcageManage;

public class DbSchemaTest {
	
	@Test
	public void syncTables()  {

		IDb db = DbFactory.create();
		List<Class<?>> clss = this.getTypes();
		for (Class<?> cls : clss) {

			Table annotation = (Table) cls.getAnnotation(Table.class);
			if (annotation == null) {
				continue;
			}

			if (annotation.isView()) {
				continue;
			}

			Mtable meta = MtableManager.getMtable(cls);
			if (db.isTableExsist(meta.getTableName())) {
				db.updateTable(meta, false);
			} else {
				db.createTable(meta);
			}
		}

		System.out.println("ok");
	}
	

	private List<Class<?>> getTypes() {

		List<Class<?>> clss = new ArrayList<Class<?>>();
		for(String pack : Application.getInstance().getPackagesToScan().split(",")){
			clss.addAll(PakcageManage.getClasses(pack,"表结构同步"));
		}		

		return clss;
	}
}
