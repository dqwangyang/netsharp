package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.EncryptionBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyEncryptionBox implements IPropertyControl {

	public Control create(PForm form, PFormField formField, FormGroup group) {
		EncryptionBox textbox = new EncryptionBox();
		{

			textbox.collected = true;
			textbox.setId(formField.getPropertyName());
			textbox.required = formField.isRequired();
			textbox.validType = "unnormal";
			textbox.showEye = false;
		}
		;

		if (formField.isFullColumn()) {
			textbox.setStyle("width:100%;");

		} else {

			textbox.setStyle("width:" + formField.getWidth() + "px;");
		}

		if (!StringManager.isNullOrEmpty(formField.getTroikaValidation())) {
			textbox.validType = formField.getTroikaValidation();
		}

		// if (formField.getTooltip()!=null) {
		// textbox.placeholder =formField.getTooltip();
		// }

		if (!StringManager.isNullOrEmpty(formField.getTroikaTrigger())) {

			textbox.onChange = formField.getTroikaTrigger();
		}

		return textbox;
	}

}
