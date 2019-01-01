package org.netsharp.panda.controls.input;


public class PercentageBox extends CurrencyBox{
	
    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-numberbox nsInput");
        this.setStyle("text-align:right;");
        this.getDataOptions().add("suffix:'%'");
        this.getDataOptions().add("max:100");
        this.getDataOptions().add("min:0");
    }
}
