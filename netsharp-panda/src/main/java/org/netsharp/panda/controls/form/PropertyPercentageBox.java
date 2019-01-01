package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.PercentageBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyPercentageBox  implements IPropertyControl{
	
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
    	PercentageBox control = new PercentageBox();
        {
        	if(formField.getPrecision() != null){

        		control.precision = formField.getPrecision();
        	}
        	control.collected = true;
        	control.setId(formField.getPropertyName());
        	control.required = formField.isRequired();
        }
        if (formField.isFullColumn())
        {
        	control.setStyle("width:100%;");
        }else{
        	
        	control.width = formField.getWidth();
		}
        return control;
    }
}
