package org.netsharp.panda.controls.input;

public class CheckBox extends Input
{
    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-validatebox");
        this.type = "checkbox";
    }
}
