package org.netsharp.dataccess.replace;

public abstract class ReplaceBase implements IReplace {

	protected String key = null;
	
	public String execute(String cmdText)
	{
		if(!this.validate(cmdText)) {
			return cmdText;
		}
		
		cmdText = this.doExecute(cmdText);
		
		return cmdText;
	}
	
	public boolean validate(String cmdText) {
		
		int index = cmdText.indexOf(key);
		
		return index>0;
	}
	
	
	public abstract String doExecute(String cmdText);

}
