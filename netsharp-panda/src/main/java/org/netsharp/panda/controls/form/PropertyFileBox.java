package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.FileBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyFileBox implements IPropertyControl{
	
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
    	FileBox control = new FileBox();{
    		control.collected = true;
    		control.setId(formField.getPropertyName());
    		control.required = formField.isRequired();
        };

        if (!StringManager.isNullOrEmpty(formField.getTroikaValidation()))
        {
        	control.getDataOptions().add("validType:'" + formField.getTroikaValidation() + "'");
        }
        
        if (formField.isFullColumn())
        {
        	control.setStyle("width:100%;");
        }else{
        	
        	control.setStyle("width:"+formField.getWidth()+"px;");
		}

        return control;
    }
}
