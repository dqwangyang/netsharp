package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Image;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyImageBox  implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
    	Image img = new Image();{
        	img.setId(formField.getPropertyName());
        	img.alt= formField.getHeader();
        	img.getInnerValues().put("collected", "true");
        	img.getInnerValues().put("controlType", "ImageBox");
        	img.setStyle("width:100px;");
        }
    	
        return img;
    }
}

