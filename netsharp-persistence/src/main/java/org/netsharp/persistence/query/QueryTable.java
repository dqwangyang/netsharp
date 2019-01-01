package org.netsharp.persistence.query;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.dataccess.InsqlGeneratorFactory;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.persistence.oql.parser.table.TableParser;

public class QueryTable extends AbstractQuery {
	public QueryTable(IDataAccess dao) {
		super(dao);
	}

	@Override
	protected TSet queryOnece()  {
		TableParser parser = new TableParser();
		String cmdText = parser.parseTable(oql);

		Mtable meta = MtableManager.getMtable(oql.getEntityId());

		// dao.open();

		readTable(meta, cmdText, oql.getParameters());

		// dao.close();

		return set;
	}

	@Override
	protected TSet queryPaging()  {
		TableParser parser = new TableParser();

		OqlStruct os = parser.parseMain(oql);

		int totalCount = getTotalCount(os, this.oql);

		// 查询总记录数

		// 生成当前页
		ArrayList<String> ids = createPaging(os);

		if (oql.getParameters() == null) {
			oql.setParameters(new QueryParameters());
		}

		// 执行当前页查询
		IInsqlGenerator generator = InsqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());
		QueryParameter qp = new QueryParameter();
		qp.setName("@IdIns");
		if (generator.isParameter()) {
			oql.getParameters().add(qp);
		}

		String keyName = os.Mtable.getKeyColumn().getColumnName();
		os.Wheres = os.Mtable.getCode() + "." + keyName + " IN (" + generator.inSql(ids, qp) + ")";

		String cmdText = parser.generate(os);

		// dao.open();

		readTable(os.Mtable, cmdText, oql.getParameters());

		// dao.close();

		// int totalCount= TotalCount(os, this.oql);

		// 查询总记录数
		set.Main.setTotalCount(totalCount);

		return set;
	}
}