package org.netsharp.dataccess;

/// <summary>
/// 分页查询条件
/// </summary>
public class PagingObject
{
    /// <summary>
    /// 每页记录数
    /// </summary>
    public int PageSize;

    /// <summary>
    /// 查询第几页
    /// 从1开始
    /// </summary>
    public int PageIndex;

    /// <summary>
    /// 查询的表名称
    /// </summary>
    public String TableName;

    /// <summary>
    /// 查询字段
    /// </summary>
    public String Selects;

    /// <summary>
    /// 主键
    /// </summary>
    public String PrimaryKey;

    /// <summary>
    /// 查询条件
    /// </summary>
    public String Filter;

    /// <summary>
    /// 排序条件
    /// </summary>
    public String Orderby;
}

