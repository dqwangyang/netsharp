package org.netsharp.dataccess.mysql;

import org.netsharp.dataccess.IPagingGenerator;
import org.netsharp.dataccess.PagingObject;
import org.netsharp.util.StringManager;

public class PagingGenerator implements IPagingGenerator {

	public String generatePaging(PagingObject paging) {

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT ").append(paging.Selects).append(" FROM ").append(paging.TableName).append(" ");
		if (!StringManager.isNullOrEmpty(paging.Filter)) {
			sb.append("WHERE").append(" ").append(paging.Filter);
		}

		if (!StringManager.isNullOrEmpty(paging.Orderby)) {
			sb.append(" ORDER BY ").append(paging.Orderby);
		}

		int startIndex = paging.PageSize * (paging.PageIndex - 1);

		sb.append(" LIMIT ").append(startIndex).append(",").append(paging.PageSize);

		return sb.toString();
	}
}
