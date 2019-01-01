package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.CheckBoxColumn;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.HAlign;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnCheckBox extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        CheckBoxColumn checkBoxColumn = new CheckBoxColumn();
        {
        	checkBoxColumn.On = "true";
        	checkBoxColumn.Off = "false";
        	checkBoxColumn.type = "checkbox";
        	checkBoxColumn.align = HAlign.Center;
        	checkBoxColumn.required = dcolumn.isRequired();
        };

        this.render(checkBoxColumn, dcolumn);

        return checkBoxColumn;
    }
}
