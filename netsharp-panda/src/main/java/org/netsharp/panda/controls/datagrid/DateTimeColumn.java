package org.netsharp.panda.controls.datagrid;

public class DateTimeColumn extends DataGridEditColumn
{
    @Override
    public void initialize()
    {
        this.columnType = "DateTime";
        this.type = "datetimebox";
        super.initialize();
    }
}