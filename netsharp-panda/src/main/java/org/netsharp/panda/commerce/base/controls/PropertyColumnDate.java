package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.DateColumn;
import org.netsharp.panda.entity.PDatagridColumn;

/*
使用PropertyColumnDateBox代替
 */
@Deprecated
public class PropertyColumnDate extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        DateColumn dateColumn = new DateColumn();
        {
        	dateColumn.required = dcolumn.isRequired();
        };

        this.render(dateColumn, dcolumn);

        return dateColumn;
    }
}
