package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.DecimalFenBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;


public class PropertyDecimalFenBox extends PropertyDecimalBox implements IPropertyControl{

	@Override
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        DecimalFenBox control = new DecimalFenBox();
        {
        	if(formField.getPrecision() != null){

        		control.precision = formField.getPrecision();
        	}
        	control.collected = true;
        	control.setId(formField.getPropertyName());
        	control.required = formField.isRequired();
        };
        
		if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
			
			control.onChange = "function(newValue,oldValue){"+formField.getTroikaTrigger()+"}";
		}
        
        if(formField.getMinValue() != null){
        	
        	control.min = formField.getMinValue();
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
