package org.netsharp.panda.controls.input;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.Linkbutton;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class MapPointBox extends Control
{
    public TextBox textBox;
    public Linkbutton linkbutton;
    public Div div;


    @Override
    public void initialize()
    {
        super.initialize();

        this.div = new Div();

        this.textBox.type = "text";
        this.textBox.controlType = "TextBox";

        this.linkbutton = new Linkbutton();
        this.linkbutton.value="获取";
        this.linkbutton.href="javascript:GetMapPoint('" + this.textBox.getId() + "');";

        this.div.getControls().add(this.textBox);
        this.div.getControls().add(this.linkbutton);
    }

    @Override
    public void render(IHtmlWriter writer)
    {
        super.render(writer);
        this.div.render(writer);
    }
}