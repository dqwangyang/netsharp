package org.netsharp.dbm.service.execute;

import java.util.List;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.dbm.entity.Column;
import org.netsharp.dbm.entity.DbmResult;
import org.netsharp.dbm.service.parse.DbmQL;
import org.netsharp.util.StringManager;

public class ExecutorSelect implements IDbExecutor {

	private DbmQL dbmOL;

	public ExecutorSelect(DbmQL dbmOL) {
		this.dbmOL = dbmOL;
	}

	public DbmResult execute(String cmdText) {
		
		if(StringManager.isNullOrEmpty(this.dbmOL.getLimit())) {
			cmdText+=" limit 100";
		}

		IDataAccess dao = DataAccessFactory.create();
		DataTable table = dao.executeTable(cmdText, null);

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
