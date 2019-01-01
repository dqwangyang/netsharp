package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.upload.QiNiuUpload;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyQiNiuUpload implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		QiNiuUpload upload = new QiNiuUpload();{
			upload.collected = true;
			upload.setId(formField.getPropertyName());
			upload.required = formField.isRequired();
        };

        if (formField.isFullColumn())
        {
        	upload.setStyle("width:100%;");
        }else if(formField.getWidth() > 100){
        
        	upload.setStyle ("width:"+formField.getWidth()+"px;");
        }else{

        	upload.setStyle ("width:151;");
        }
        
        if (formField.isFullColumn())
        {
        	upload.setStyle("width:100%;");
        }else{
        	
        	upload.setStyle("width:"+formField.getWidth()+"px;");
		}
        return upload;
	}

}
