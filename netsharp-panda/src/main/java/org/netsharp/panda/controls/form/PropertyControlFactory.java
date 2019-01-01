package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.ReflectManager;

public class PropertyControlFactory
{
    public static IPropertyControl create(PFormField formField)
    {
        if (formField.getControlType() == ControlTypes.CUSTOM)
        {
            return (IPropertyControl)ReflectManager.newInstance(formField.getCustomControlType());
        }
        else
        {
            String type = "org.netsharp.panda.controls.form.Property" + formField.getControlType().getName();

            return (IPropertyControl)ReflectManager.newInstance(type);
        }
    }
}
