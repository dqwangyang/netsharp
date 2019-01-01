package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.EditorOption;

public class JavaEnumBoxColumn extends DataGridEditColumn
{
    @EditorOption(html="valueField", isOption= true,isEvent= false)
    public String valueField="value";

    @EditorOption(html="textField", isOption= true,isEvent= false)
    public String textField="text";

    @EditorOption(html="editable", isOption= true,isEvent= false)
    public boolean editable=false;
    
    @EditorOption(html="data", isOption= true,isEvent= true)
    public String data;
    
    @EditorOption(html="width", isOption= true,isEvent= false)
    public int editorWidth;
    
    @Override
    public void initialize()
    {
        this.type = "combobox";
        this.formatter="function(value,row,index){return PandaHelper.JavaEnumBoxFormatter(this,value);}";
        super.initialize();
    }
}