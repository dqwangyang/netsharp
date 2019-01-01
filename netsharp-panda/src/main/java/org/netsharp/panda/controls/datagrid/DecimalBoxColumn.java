package org.netsharp.panda.controls.datagrid;

public class DecimalBoxColumn extends DataGridEditColumn
{
    @Override
    public void initialize()
    {
        this.columnType = "DecimalBox";
        this.type = "numberbox";
        super.initialize();
    }
}