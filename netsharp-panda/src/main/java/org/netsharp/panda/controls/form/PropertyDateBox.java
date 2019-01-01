package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.DateBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyDateBox implements IPropertyControl
{
    public FormGroup FormGroup;

    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        DateBox control = new DateBox();
        {
        	control.collected = true;
        	control.setId(formField.getPropertyName());
        	control.required = formField.isRequired();
        };
        
		if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
			
			control.onSelect ="function(date){"+ formField.getTroikaTrigger()+"}";
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
