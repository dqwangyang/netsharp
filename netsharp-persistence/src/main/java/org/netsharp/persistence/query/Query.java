package org.netsharp.persistence.query;

import org.netsharp.core.DataTable;
import org.netsharp.core.ITable;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.persistence.oql.parser.table.TableParser;
import org.netsharp.util.StringManager;

public class Query {
	private IDataAccess dao = null;

	public Query() {
		dao = DataAccessFactory.create();
	}
	
	public Query(IDataAccess dao) {
		this.dao = dao;
	}

	public DataTable ExecuteTable(Oql oql) {

		Mtable meta = MtableManager.getMtable(oql.getEntityId());
		if (StringManager.equals(oql.getSelects(), "*")) {
			oql.setSelects(meta.getCode() + ".*");
		}

		TableParser parser = new TableParser();
		String oqlText = parser.parseTable(oql);

		ITable<?> table = dao.executeTable(oqlText, oql.getParameters());

		table.setEntityId(meta.getEntityId());
		table.setName(meta.getName());
		table.setCode(meta.getCode());

		return (DataTable) table;
	}

	public TSet QuerySet(Oql oql) {
		
		Mtable meta = MtableManager.getMtable(oql.getEntityId());
		if (StringManager.equals(oql.getSelects(), "*")) {
			oql.setSelects(meta.getCode() + ".*");
		}

		QuerySet setQuery = new QuerySet(this.dao);

		TSet set = setQuery.query(oql);

		return set;
	}
}