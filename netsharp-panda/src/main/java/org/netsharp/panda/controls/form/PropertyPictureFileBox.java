package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.PictureFileBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyPictureFileBox implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {

		PictureFileBox control = new PictureFileBox();{
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
