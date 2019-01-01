package org.netsharp.panda.controls.datagrid;

public class TextBoxColumn extends DataGridEditColumn
{
    @Override
    public void initialize()
    {
        this.columnType = "TextBox";
        this.type = "validatebox";
        this.validType="unnormal";
        super.initialize();
    }
}
