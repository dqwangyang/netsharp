package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.TextBoxColumn;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.util.StringManager;

public class PropertyColumnTextBox extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        TextBoxColumn textBoxColumn = new TextBoxColumn();
        {
        	textBoxColumn.required = dcolumn.isRequired();
        	
        	if(!StringManager.isNullOrEmpty(dcolumn.getTroikaValidation())){

            	textBoxColumn.validType = dcolumn.getTroikaValidation();
        	}
        }

        this.render(textBoxColumn, dcolumn);

        return textBoxColumn;
    }
}
