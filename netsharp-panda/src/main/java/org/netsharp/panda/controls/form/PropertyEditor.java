package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextArea;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyEditor implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        TextArea editor = new TextArea();
        {
        	editor.controlType = "Editor";
        	editor.collected = true;
        	editor.setId(formField.getPropertyName());
        	editor.required = formField.isRequired();
        };

        if (formField.isFullColumn())
        {
            editor.setStyle("width:100%;height:" +formField.getHeight() + "px");
        }

        return editor;
    }
}
