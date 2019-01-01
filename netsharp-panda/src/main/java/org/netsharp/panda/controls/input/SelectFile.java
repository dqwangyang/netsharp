package org.netsharp.panda.controls.input;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class SelectFile extends Control
{
    public TextBox textBox;

    public SelectButton selectButton;

    public Div div;

    @Override
    public void initialize()
    {
        super.initialize();

        //this.Div = new Div()
        //{
        //    Name = this.SelectButton.Id,
        //    ClassName = "SelectPanel",
        //    Style = "width:"+0+"px;"
        //};

        this.textBox.type = "text";
        this.textBox.controlType = "textBox";
        this.textBox.disabled = true;
        //this.TextBox.Style = "width:92px;float:left;height:18px;margin-right:3px;";

        this.selectButton.type = "button";
        this.selectButton.setStyle("float:right;");
        this.selectButton.backFillControl = this.textBox.getId();

        this.div.getControls().add(this.textBox);
        this.div.getControls().add(this.selectButton);
    }

    @Override
    public void render(IHtmlWriter writer)
    {
        this.div.render(writer);
    }
}
