package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.HyperLink;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyHyperLink implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        HyperLink hyperLink = new HyperLink();
        
        hyperLink.setId(formField.getPropertyName());
    	hyperLink.target = "_blank";
    	hyperLink.value = "";
    	hyperLink.collected = true;

        return hyperLink;
    }
}