package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.EditorOption;

public class CurrencyBoxColumn extends DataGridEditColumn
{
    /// <summary>
    /// 小数位
    /// </summary>
    @EditorOption(html="precision", isOption=true, isEvent=false)
    public int precision = 2;

    @Override
    public void initialize()
    {
        this.columnType = "CurrencyBox";
        this.type = "numberbox";
        super.initialize();
    }
}
