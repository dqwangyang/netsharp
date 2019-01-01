package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextArea;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyTextArea implements IPropertyControl {

	public Control create(PForm form, PFormField formField, FormGroup group) {

		TextArea textarea = new TextArea();
		{
			textarea.collected = true;
			textarea.setId(formField.getPropertyName());
			textarea.required = formField.isRequired();
			textarea.validType="['unnormal']";
		}

		StringBuilder stylesBuilder = new StringBuilder();
		if (formField.isFullColumn()) {

			textarea.setClassName("full");
		} else {
			stylesBuilder.append("width:" + formField.getWidth() + "px;");
		}

		if (formField.getHeight() > 0) {
			
			stylesBuilder.append("height:" + formField.getHeight() + "px;");
		}else {
			stylesBuilder.append("height:60px;");
		}
		textarea.setStyle(stylesBuilder.toString());
		
		if (formField.isReadonly() || form.isReadOnly()) {
			textarea.disabled = true;
		}
		
        if (!StringManager.isNullOrEmpty(formField.getTroikaValidation()))
        {
        	textarea.validType=formField.getTroikaValidation();
        }
		
		return textarea;
	}
}
