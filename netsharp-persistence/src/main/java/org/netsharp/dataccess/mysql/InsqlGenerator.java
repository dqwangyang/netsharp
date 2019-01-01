package org.netsharp.dataccess.mysql;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.property.IProperty;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.util.StringManager;

public class InsqlGenerator implements IInsqlGenerator {
	
	public String inSql(Iterable<IPersistable> rows, String propertyName, QueryParameter qp) {
		
		ArrayList<String> ids = new ArrayList<String>();

		IProperty property = null;

		for (IPersistable row : rows) {
			if (property == null) {
				Mtable meta = MtableManager.getMtable(row.getClass());
				Column column = meta.getPropertyOrColumn(propertyName);

				if (column == null) {
					throw new OqlParseException(meta.getEntityId() + "[" + propertyName + "]不存在！");
				}

				property = column.getProperty();
			}

			Object value = property.field(row);

			if (value == null) {
				continue;
			}

			String id = value.toString();

			if (!ids.contains(id)) {
				ids.add(id);
			}
		}

		if (ids.size() == 0) {
			return "NULL";
		}

		return inSql(ids, qp);
	}

	public String inSql(String[] ids, QueryParameter qp) {
		List<String> list = new ArrayList<String>();
		for (String item : ids) {
			list.add(item);
		}

		return inSql(list, qp);
	}

	public String inSql(Iterable<String> ids, QueryParameter qp) {

		StringBuilder builder = new StringBuilder();
		for (String id : ids) {
			builder.append("'").append(id).append("'").append(",");
		}

		String str = builder.toString();

		if (StringManager.isNullOrEmpty(str)) {
			return "NULL";
		} else {
			str = StringManager.trimEnd(str, ',');
			return str;
		}
	}

	public String xmlId(Iterable<String> ids) {
		return inSql(ids, null);
	}

	public boolean isParameter() {
		return false;
	}
}