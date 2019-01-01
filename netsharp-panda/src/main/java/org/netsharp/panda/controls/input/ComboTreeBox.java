package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;

public class ComboTreeBox extends TextBox
{
    @HtmlAttr(html="foreignkey")
    public String foreignkey;

    @HtmlAttr(html="foreignName")
    public String foreignName;

    @HtmlAttr(html="foreignId")
    public String foreignId;

    @DataOption(html="idField")
    public String idField;

    @DataOption(html="textField")
    public String textField;

    @DataOption(html="url")
    public String url;

    @DataOption(html="width")
    public Integer width;
    
    @DataOption(html="panelWidth")
    public Integer panelWidth;

    @DataOption(html="onBeforeExpand",isEvent=true)
    public String onBeforeExpand;
    
    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-combotree");
    }
}
