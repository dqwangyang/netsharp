package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public interface IPropertyControl
{
    Control create(PForm form, PFormField formField, FormGroup group);
}
