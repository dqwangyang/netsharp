package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class EnumBox extends TextBox
{
    @DataOption(html="idEnum")
    public Integer idEnum;

    @DataOption(html="valueField")
    public String valueField;

    @DataOption(html="textField")
    public String textField;

    @DataOption(html="panelHeight")
    public String panelHeight;

    @DataOption(html="url")
    public String url;

    @DataOption(html="systemic")
    public Boolean systemic;

    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-combobox");
        this.valueField = "id";
        this.textField = "text";
        this.panelHeight = "auto";
        this.url = "TT.enum?Id=" + idEnum;
    }
}
