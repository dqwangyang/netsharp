package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;

public class BoolComboBox extends Select
{
    @HtmlAttr(html="multiple")
    public boolean multiple;

    @HtmlAttr(html="controlType")
    public String controlType;

	@DataOption(html = "editable")
	public boolean editable = false;
	
	@DataOption(html = "width")
	public Integer width;
	
    @Override
    public void initialize()
    {
        super.initialize();
        this.getControls().add( new SelectOption( "-1", "不限") );
        this.getControls().add( new SelectOption( "1", "是") );
        this.getControls().add(new SelectOption( "0", "否" ));
    }

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}