package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyTextBox implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        TextBox textbox = new TextBox();{
        	textbox.collected = true;
        	textbox.setId(formField.getPropertyName());
        	textbox.required = formField.isRequired();
        	textbox.validType="['unnormal']";
        };

        if (formField.isFullColumn())
        {
        	textbox.setClassName("full");
            
        }else{
        
        	textbox.setStyle("width:"+formField.getWidth()+"px;");
        }

        if (!StringManager.isNullOrEmpty(formField.getTroikaValidation()))
        {
        	textbox.validType=formField.getTroikaValidation();
        }

//		if (formField.getTooltip()!=null) {
//			textbox.placeholder =formField.getTooltip();
//		}

		if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
			
			textbox.onChange = formField.getTroikaTrigger();
		}
		
        return textbox;
    }
}