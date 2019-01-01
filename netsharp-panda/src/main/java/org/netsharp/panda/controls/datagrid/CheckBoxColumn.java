package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.EditorOption;

public class CheckBoxColumn extends DataGridEditColumn
{
    @EditorOption(html="off",isOption=true,isEvent=false)
    public String Off;

    @EditorOption(html="on", isOption=true,isEvent=false)
    public String On;

    @Override
    public void initialize()
    {
        this.columnType = "Checkbox";
        this.formatter = "function(value,rowData,rowIndex){if(value){return value.toString().toLowerCase()=='true'?'<span class=checked></span>':'<span class=unchecked></span>';} return '<span class=unchecked></span>';}";
        super.initialize();
    }
}
