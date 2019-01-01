package org.netsharp.persistence.query;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.dataccess.InsqlGeneratorFactory;
import org.netsharp.persistence.oql.parser.OqlContex;
import org.netsharp.persistence.oql.parser.SbManager;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.persistence.oql.parser.set.OqlStructs;
import org.netsharp.persistence.oql.parser.set.SetParser;
import org.netsharp.util.StringManager;

public class QuerySet extends AbstractQuery {

	protected final Log logger = LogFactory.getLog(this.getClass());

	public QuerySet(IDataAccess dao) {
		super(dao);
	}

	@Override
	protected TSet queryOnece()  {
		SetParser parser = new SetParser();
		OqlContex context = parser.parseSet(oql);

		return doQuery(context);
	}

	@Override
	protected TSet queryPaging()  {
		Mtable meta = MtableManager.getMtable(oql.getEntityId());

		SetParser parser = new SetParser();
		OqlStruct os = parser.parseMain(oql);

		int totalCount = getTotalCount(os, this.oql);
		oql.getPaging().setTotalCount(totalCount);

		// 生成当前页
		ArrayList<String> ids = createPaging(os);

		if (oql.getParameters() == null) {
			oql.setParameters(new QueryParameters());
		}

		IInsqlGenerator generator = InsqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());
		QueryParameter qp = new QueryParameter();
		qp.setName("@IdIns");
		if (generator.isParameter()) {
			oql.getParameters().add(qp);
		}

		String keyName = meta.getKeyColumn().getColumnName();// xfb 2017
		os.Wheres = meta.getCode() + "." + keyName + " IN (" + generator.inSql(ids, qp) + ")";

		// where 条件因为有分页了，所以不需要joins
		// order by 还需要joins，因为分页不能体现顺序
		os.Joins = os.OrderbyJoins;

		OqlContex context = parser.parseSubs();

		TSet set = doQuery(context);

		// 查询总记录数
		set.Main.setTotalCount(totalCount);

		return set;
	}

	private TSet doQuery(OqlContex context)  {
		// -----------------------------------------------------
		// 分解OQL解析顺序
		// -----------------------------------------------------
		ArrayList<OqlStructs> osss = new ArrayList<OqlStructs>();
		osss.addAll(context.OqlStructs.values());

		Collections.sort(osss, new OqlSort());

		OqlStructs current = null;
		for (OqlStructs os : osss) {
			if (current == null) {
				current = os;
				continue;
			}

			if (os.getMaxLevel() < current.getMaxLevel()) {
				logger.error("OQL执行的顺序有误");
			}

			current = os;
		}

		for (OqlStructs oss : osss) {
			query(oss);
		}

		return set;
	}

	private void query(OqlStructs oss)  {

		QueryParameters qpc = new QueryParameters();
		for (QueryParameter qp : oql.getParameters()) {
			qpc.add(qp);
		}

		OqlStruct os = oss.get(0);

		// 生成SELECTS
		ArrayList<String> selectes = new ArrayList<String>();
		for (OqlStruct item : oss) {
			String[] ss = item.Selects.split(",");

			for (String s : ss) {
				if (!selectes.contains(s)) {
					selectes.add(s);
				}
			}
		}

		//
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ").append(StringManager.join(",", selectes)).append(StringManager.NewLine).append("FROM ").append(os.Mtable.getTableName());

		if (os.IsMain) {
			builder.append(" AS ").append(os.Mtable.getCode()).append(Oql.WithNolock()).append(StringManager.NewLine);
			SbManager.appendLineReqired(builder, "", os.Joins);
			SbManager.appendLineReqired(builder, "WHERE ", os.Wheres);
		} else {
			builder.append(Oql.WithNolock()).append(StringManager.NewLine);

			//
			HashMap<String, OqlStruct> ss = new HashMap<String, OqlStruct>();
			for (OqlStruct oqlStruct : oss) {
				if (!ss.containsKey(oqlStruct.toString())) {
					ss.put(oqlStruct.toString(), oqlStruct);
				}
			}

			//
			ArrayList<String> filters = new ArrayList<String>();
			for (OqlStruct oqlStruct : ss.values()) {
				if (oqlStruct.Parent == null) {
					continue;
				}

				if (oqlStruct.Parent.Mtable == null) {
					continue;
				}

				TTable parentTable = set.getTable(oqlStruct.Parent.Mtable.getEntityId());
				if (parentTable != null) {
					String key = oqlStruct.Mtable.getCode() + "_" + oqlStruct.InInIdField + "_" + oqlStruct.OutInId + "_" + oqlStruct.Parent.Mtable.getCode();

					IInsqlGenerator generator = InsqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());

					QueryParameter qp = new QueryParameter();
					qp.setName("@Ins_" + key);
					qp.setDbType(Types.VARCHAR);
					if (generator.isParameter()) {
						qpc.add(qp);
					}

					String filter = generator.inSql(parentTable.Items, oqlStruct.InInIdField, qp);
					String keyName = oqlStruct.Mtable.getPropertyOrColumn(oqlStruct.OutInId).getColumnName();// xfb 2017
					filters.add(keyName + " IN (" + filter + ")");
				} else {
					// throw new DbException("依赖的查询必须有数据");
				}
			}

			if (filters.size() > 0) {
				builder.append(" WHERE ").append(StringManager.join(" OR ", filters)).append(StringManager.NewLine);
			}

		}

		SbManager.appendLineReqired(builder, " ORDER BY ", os.Orderby);

		String cmdText = builder.toString();

		// 执行查询
		readTable(os.Mtable, cmdText, qpc);
	}

	private class OqlSort implements Comparator<OqlStructs> {

		@Override
		public int compare(OqlStructs x, OqlStructs y) {
			if (x.getMaxLevel() == y.getMaxLevel()) {
				return 0;
			}

			return x.getMaxLevel() > y.getMaxLevel() ? 1 : -1;
		}
	}
}
