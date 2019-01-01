package org.netsharp.panda.controls.datagrid;

public class TextAreaColumn extends DataGridEditColumn
{
    @Override
    public void initialize()
    { 
    	this.type = "textarea";
        this.columnType = "TextArea";
        super.initialize();
    }
}
