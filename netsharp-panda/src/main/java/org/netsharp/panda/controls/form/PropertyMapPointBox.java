package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.MapPointBox;
import org.netsharp.panda.controls.input.TextBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyMapPointBox implements IPropertyControl
{
    public Control create(PForm form,PFormField formField,FormGroup group)
    {
        TextBox textBox = new TextBox();
        {
        	textBox.setId(formField.getPropertyName());
        	textBox.value = "";
        	textBox.required = formField.isRequired();
        	textBox.setClassName("easyui-validatebox nsInput");
        	textBox.collected = true;
        };

        MapPointBox mapPointBox = new MapPointBox();
        {
        	mapPointBox.textBox = textBox;
        };

        return mapPointBox;
    }
}