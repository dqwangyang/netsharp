package org.netsharp.persistence.oql.operation.select;

public enum OperationType
{
    /// <summary>
    /// 双目
    /// </summary>
    Two,

    /// <summary>
    /// 左单目
    /// TOP、ALL、DISTINCT、ORDER BY
    /// </summary>
    Left,

    /// <summary>
    /// 右单目
    /// ASC、DESC
    /// </summary>
    Right
}
