package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.Align;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.NumberBoxColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnPercentageBox extends PropertyColumnBase{
	
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
    	NumberBoxColumn numberBoxColumn = new NumberBoxColumn();
        {
        	numberBoxColumn.required = dcolumn.isRequired();
        	numberBoxColumn.align = Align.Right;
        	numberBoxColumn.suffix = "%";
        	if(dcolumn.getPrecision() != null){

        		numberBoxColumn.precision = dcolumn.getPrecision();
        	}
        };

        this.render(numberBoxColumn, dcolumn);

        return numberBoxColumn;
    }
}
