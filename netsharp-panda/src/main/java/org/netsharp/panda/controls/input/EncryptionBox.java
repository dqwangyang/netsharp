package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class EncryptionBox extends TextBox {

	@DataOption(html = "passwordChar")
	public String passwordChar = "*";

	@DataOption(html = "checkInterval")
	public Integer checkInterval = 200;

	@DataOption(html = "lastDelay")
	public Integer lastDelay = 500;

	@DataOption(html = "revealed")
	public boolean revealed = false;

	@DataOption(html = "showEye")
	public boolean showEye = true;
    
    @DataOption(html="height")
    public Integer height = 30;

	@Override
	public void initialize() {
		super.initialize();
		this.setClassName("easyui-validatebox easyui-passwordbox nsInput");
		this.type = "text";
	}
}
