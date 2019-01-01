package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.DateTimeColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnDateTime extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        DateTimeColumn dateTimeColumn = new DateTimeColumn();
        {
        	dateTimeColumn.required = dcolumn.isRequired();
        };

        this.render(dateTimeColumn, dcolumn);

        return dateTimeColumn;
    }
}
