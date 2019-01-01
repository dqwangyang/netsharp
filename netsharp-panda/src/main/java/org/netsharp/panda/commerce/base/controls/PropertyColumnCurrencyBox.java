package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.Align;
import org.netsharp.panda.controls.datagrid.CurrencyBoxColumn;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnCurrencyBox extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        CurrencyBoxColumn currencyBoxColumn = new CurrencyBoxColumn();
        {
        	if(dcolumn.getPrecision() != null){

        		currencyBoxColumn.precision = dcolumn.getPrecision();
        	}
        	currencyBoxColumn.required = dcolumn.isRequired();
        	currencyBoxColumn.align = Align.Right;
        };

        this.render(currencyBoxColumn, dcolumn);

        return currencyBoxColumn;
    }
}
