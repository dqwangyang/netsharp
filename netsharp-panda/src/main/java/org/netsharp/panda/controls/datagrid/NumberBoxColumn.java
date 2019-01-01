package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.EditorOption;

public class NumberBoxColumn extends DataGridEditColumn
{
    /// <summary>
    /// 小数位
    /// </summary>
    @EditorOption(html="precision",isOption =true,isEvent= false)
    public int precision;
    
    @EditorOption(html="suffix", isOption=true, isEvent=false)
    public String suffix;

    @Override
    public void initialize()
    {
        this.columnType = "NumberBox";
        this.type = "numberbox";
        super.initialize();
    }
}
