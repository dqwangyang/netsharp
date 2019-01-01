package org.netsharp.persistence.sql;

import org.netsharp.core.NetsharpException;
import org.netsharp.dic.DatabaseType;
import org.netsharp.util.StringManager;

public class OracleGenerator extends AbstractSqlGenerator
{
    public OracleGenerator()
    {
        dbType = DatabaseType.Oracle;
        parameterPrefix = prefix = ":";
    }

    /*
     * oralce的rownum从1开始
     * 第一页和其他的页数目前分开处理的
     */

    /// <summary>
    /// 带排序的分页Sql
    /// </summary>
    @Override
    public String generatePaging(int pageSize, int pageIndex,
        String tableName, String queryFields, String primaryKey, String whereClause, String orderByCondition)
    {
        String sqlTemplate = ""+
                    "SELECT <%=Fields%> FROM"+StringManager.NewLine+
                    "("+StringManager.NewLine+
                    "SELECT PagedTable.*, ROWNUM RN"+StringManager.NewLine+
                    "FROM (SELECT <%=Fields%> FROM <%=TableName%> <%=Filter%> <%=OrderBy%>) PagedTable"+StringManager.NewLine+
                    "WHERE ROWNUM <= <%=EndNumber%>"+StringManager.NewLine+
                    ")"+StringManager.NewLine+
                    "WHERE RN > <%=StartNumber%>";

        if (pageIndex <= 0) pageIndex = 1;
        int startNum = (pageIndex - 1) * pageSize;
        int endNum = (pageIndex) * pageSize;

        String sql = sqlTemplate.replaceAll("<%=StartNumber%>",String.valueOf(startNum));
        sql = sql.replaceAll("<%=EndNumber%>", String.valueOf(endNum));

        sql = sql.replaceAll("<%=Fields%>", StringManager.isNullOrEmpty(queryFields.trim()) ? "*" : queryFields);
        sql = sql.replaceAll("<%=TableName%>", tableName);
        sql = sql.replaceAll("<%=PrimaryKey%>", primaryKey);

        if (null != orderByCondition && 0 != orderByCondition.length())
        {
            sql = sql.replaceAll("<%=OrderBy%>", " ORDER BY " + orderByCondition); // + "," + primaryKey + " DESC"

            // 外层Order By需要去掉前缀
            //sql = Regex.replace(sql, "<%=OrderByOuter%>", " ORDER BY " + Regex.replace(orderByCondition, "\\b[\\w]*\\.", ""));
            throw new NetsharpException("暂未实现！！！");
        }
        else
        {
            sql = sql.replaceAll("<%=OrderBy%>", "").replaceAll("<%=OrderByOuter%>", "");
        }

        if (null != whereClause && 0 != whereClause.length())
        {
            sql = sql.replaceAll("<%=Filter%>", " WHERE " + whereClause);
        }
        else
        {
            sql = sql.replaceAll("<%=Filter%>", "");
        }
        return sql;
    }
}