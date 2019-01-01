package org.netsharp.dataccess.replace;

import org.netsharp.dataccess.DataAccessException;
import org.netsharp.util.DES;

/*加密字段替换*/
public class ReplaceEncryption extends ReplaceBase {

	private String start = "{cryp";
	private String end = "!cryp}";

	@Override
	public boolean validate(String cmdText) {
		
		int index = cmdText.indexOf(start);
		
		if(index<0) {
			return false;
		}
		
		 index = cmdText.indexOf(end);
		 
		 return index >=0;
	}	

	public String doExecute(String cmdText) {

		while (cmdText.indexOf(this.start) >= 0 && cmdText.indexOf(this.end) > 0) {

			try {
				int startIndex = cmdText.indexOf(this.start);
				int endIndex = cmdText.indexOf(this.end);

				String cryp = cmdText.substring(startIndex, endIndex + this.end.length());
				String value = cmdText.substring(startIndex + this.start.length(), endIndex);
				String dbValue = DES.encrypt(value, DES.getKey());

				cmdText = cmdText.replace(cryp, dbValue);
			} catch (Exception ex) {
				throw new DataAccessException("sql有加密字段时，解析出现异常", ex);
			}
		}

		return cmdText;
	}
}
