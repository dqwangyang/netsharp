package org.netsharp.panda.controls.input;

public class PasswordTextBox extends TextBox
{
    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-validatebox nsInput");
        this.type = "password";
    }
}
