package org.netsharp.dataccess.sqlserver;

import org.netsharp.core.Oql;
import org.netsharp.dataccess.IPagingGenerator;
import org.netsharp.dataccess.PagingObject;
import org.netsharp.util.StringManager;

public class PagingGenerator implements IPagingGenerator {

	public String generatePaging(PagingObject paging) {
		StringBuilder sb = new StringBuilder();

        // 如果paging.PageSize 或者 paging.PageIndex小于等于0，查询所有记录
        if (paging.PageSize <= 0 || paging.PageIndex <= 0)
        {
            sb.append("SELECT ").append(paging.Selects).append(" FROM ").append(paging.TableName).append(Oql.WithNolock());

            if (null != paging.Filter && 0 != paging.Filter.length())
            {
                sb.append(" WHERE ").append(paging.Filter);
            }

            if (null != paging.Orderby && 0 != paging.Orderby.length())
            {
                sb.append(" ORDER BY ").append(paging.Orderby);
            }
        }
        else
        {
            String sqlTemplate = ""+StringManager.NewLine+
                "SELECT Id FROM ("+StringManager.NewLine+
                "    SELECT ROW_NUMBER() OVER(order by {0}) AS RowNumber,{1} FROM {2} {3}"+StringManager.NewLine+
                ") AS PagedTable"+StringManager.NewLine+
                "WHERE RowNumber BETWEEN {4} AND {5} ";

            if (paging.PageIndex <= 0)
            {
                paging.PageIndex = 1;
            }
            int startNum = (paging.PageIndex - 1) * paging.PageSize + 1;
            int endNum = paging.PageIndex * paging.PageSize;

            if (StringManager.isNullOrEmpty(paging.Orderby))
            {
                paging.Orderby = paging.PrimaryKey;
            }

            if (!StringManager.isNullOrEmpty(paging.Filter))
            {
                paging.Filter = " WHERE " + paging.Filter;
            }

            sb.append(String.format(sqlTemplate, paging.Orderby, paging.Selects, paging.TableName, paging.Filter, startNum, endNum));
        }

        return sb.toString();
	}

}
