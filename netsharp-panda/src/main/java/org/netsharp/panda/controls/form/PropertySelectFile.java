package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.SelectButton;
import org.netsharp.panda.controls.input.SelectFile;
import org.netsharp.panda.controls.input.TextBox;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertySelectFile implements IPropertyControl
{
    public Control create(PForm form,PFormField formField, FormGroup group)
    {
        SelectFile selectFile = new SelectFile();
        TextBox textBox = new TextBox();
        {
        	textBox.setId(formField.getPropertyName());
        	textBox.value = "";
        	textBox.setStyle("width:" + (formField.getWidth() - 50) + "px;float:left;height:18px;margin-right:3px;");
        	textBox.collected = true;
        };
    	selectFile.textBox = textBox;

        String url = "/Manager/SelectFile.html?BackFillControl=" + formField.getPropertyName();
        url = UrlHelper.getUrl(url);
        
        SelectButton selectButton = new SelectButton();
        {
        	selectButton.setId("uploadFile" + formField.getPropertyName());
        	throw new PandaException("selectButton.FileTypeExts 文件后缀名格式未处理！");
//        	selectButton.FileTypeExts = formField.getMemo();
//        	selectButton.Value = "选择";
//        	selectButton.Style = group.Style + "border:1px #95b8e7 solid;";
//        	selectButton.Type = "button";
//        	selectButton.Onclick = "IMessageBox.window('选择文件', '" + url + "', 1000, 500, function(){}, true);";
        }

//        selectFile.SelectButton = selectButton;
//
//        Div div=new Div();
//        {
//        	div.ClassName = "SelectPanel";
//        	div.Style = "width:" + formField.getWidth() + "px;";
//        };
//        selectFile.Div = div;
//
//        return selectFile;
    }
}
