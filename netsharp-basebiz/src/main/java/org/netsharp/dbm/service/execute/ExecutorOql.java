package org.netsharp.dbm.service.execute;

import java.util.List;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.dbm.entity.Column;
import org.netsharp.dbm.entity.DbmResult;
import org.netsharp.dbm.service.parse.DbmQL;
import org.netsharp.entity.Persistable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

public class ExecutorOql implements IDbExecutor {
	
	private DbmQL dbmOL;
	
	public ExecutorOql(DbmQL dbmOL) {
		this.dbmOL = dbmOL;
	}
	
	public DbmResult execute(String cmdText) {
		
		IPersister<Persistable> pm = PersisterFactory.create();
		
		Oql oql = new Oql();
		{
			Mtable meta = MtableManager.getMtable(this.dbmOL.getFrom());
			oql.setEntityId(this.dbmOL.getFrom());
			if(StringManager.equals(this.dbmOL.getSelect(), "*")) {
				this.dbmOL.setSelect( meta.getType().getSimpleName()+".*");
			}
			oql.setSelects(this.dbmOL.getSelect());
			oql.setFilter(this.dbmOL.getWhere());
			oql.setOrderby(this.dbmOL.getOrderby());
			
			Paging paging = new Paging();
			{
				paging.setPageNo(1);
				paging.setPageSize(100);
			}
			
			oql.setPaging(paging);
		}
		DataTable table = pm.queryTable(oql);

		List<Map<String, Object>> items = table.getValueMapList();
		Column[] columns = new Column[table.getColumns().size()];
		for (int i = 0; i < columns.length; i++) {

			Column column = new Column();
			{
				column.setField(table.getColumns().get(i).getColumnName());
				column.setTitle(table.getColumns().get(i).getColumnName());
			}
			columns[i] = column;
		}

		DbmResult result = new DbmResult();
		{
			result.setColumns(columns);
			result.setItems(items);
		}

		return result;
	}
}
