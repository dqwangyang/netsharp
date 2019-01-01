package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.PasswordTextBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyPasswordTextBox implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        PasswordTextBox control = new PasswordTextBox();
        {
        	control.collected = true;
        	control.setId(formField.getPropertyName());
        	control.required = formField.isRequired();
        };

        if (formField.isFullColumn())
        {
        	control.setStyle("width:100%;");
        }
        if (!StringManager.isNullOrEmpty(formField.getTroikaValidation()))
        {
        	control.validType = formField.getTroikaValidation();
        }

        return control;
    }
}