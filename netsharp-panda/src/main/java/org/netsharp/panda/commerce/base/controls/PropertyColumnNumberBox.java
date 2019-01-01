package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.Align;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.NumberBoxColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnNumberBox extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        NumberBoxColumn numberBoxColumn = new NumberBoxColumn();
        {
        	numberBoxColumn.required = dcolumn.isRequired();
        	numberBoxColumn.align = Align.Right;
        };

        this.render(numberBoxColumn, dcolumn);

        return numberBoxColumn;
    }
}
