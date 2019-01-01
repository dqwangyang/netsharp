package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.CheckBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyCheckBox implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        CheckBox checkBox = new CheckBox();
        checkBox.collected = true;
		checkBox.setId(formField.getPropertyName());
        return checkBox;
    }
}
