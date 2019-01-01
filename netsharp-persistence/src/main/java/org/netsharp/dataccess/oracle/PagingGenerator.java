package org.netsharp.dataccess.oracle;

import org.netsharp.core.NetsharpException;
import org.netsharp.dataccess.IPagingGenerator;
import org.netsharp.dataccess.PagingObject;
import org.netsharp.util.StringManager;

public class PagingGenerator implements IPagingGenerator {

	public String generatePaging(PagingObject paging) {
		String sqlTemplate = ""+
                "SELECT <%=Fields%> FROM"+StringManager.NewLine+
                "("+StringManager.NewLine+
                "SELECT PagedTable.*, ROWNUM RN"+StringManager.NewLine+
                "FROM (SELECT <%=Fields%> FROM <%=paging.TableName%> <%=Filter%> <%=OrderBy%>) PagedTable"+StringManager.NewLine+
                "WHERE ROWNUM <= <%=EndNumber%>"+StringManager.NewLine+
                ")"+StringManager.NewLine+
                "WHERE RN > <%=StartNumber%>";

    if (paging.PageIndex <= 0) paging.PageIndex = 1;
    int startNum = (paging.PageIndex - 1) * paging.PageSize;
    int endNum = (paging.PageIndex) * paging.PageSize;

    String sql = sqlTemplate.replaceAll("<%=StartNumber%>",String.valueOf(startNum));
    sql = sql.replaceAll("<%=EndNumber%>", String.valueOf(endNum));

    sql = sql.replaceAll("<%=Fields%>", StringManager.isNullOrEmpty(paging.Selects.trim()) ? "*" : paging.Selects);
    sql = sql.replaceAll("<%=paging.TableName%>", paging.TableName);
    sql = sql.replaceAll("<%=paging.PrimaryKey%>", paging.PrimaryKey);

    if (null != paging.Orderby && 0 != paging.Orderby.length())
    {
        sql = sql.replaceAll("<%=OrderBy%>", " ORDER BY " + paging.Orderby); // + "," + paging.PrimaryKey + " DESC"

        // 外层Order By需要去掉前缀
        //sql = Regex.replace(sql, "<%=OrderByOuter%>", " ORDER BY " + Regex.replace(paging.Orderby, "\\b[\\w]*\\.", ""));
        throw new NetsharpException("暂未实现！！");
    }
    else
    {
        sql = sql.replaceAll("<%=OrderBy%>", "").replaceAll("<%=OrderByOuter%>", "");
    }

    if (null != paging.Filter && 0 != paging.Filter.length())
    {
        sql = sql.replaceAll("<%=Filter%>", " WHERE " + paging.Filter);
    }
    else
    {
        sql = sql.replaceAll("<%=Filter%>", "");
    }
    return sql;
	}

}