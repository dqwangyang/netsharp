package org.netsharp.dataccess.replace;

import java.util.ArrayList;
import java.util.List;

public class ReplaceManage {
	
	private static List<IReplace> replaces = new ArrayList<IReplace>();
	
	public static String replace(String cmdText){
		
		if(replaces.size()==0){
			replaces.add(new ReplaceDepartment());
			replaces.add(new ReplaceUserId());
			replaces.add(new ReplaceDepartmentEmployees());
			replaces.add(new ReplaceEncryption());
			replaces.add(new ReplaceCoperation());
		}
		
		String sql = cmdText;
		for(IReplace replace : replaces){
			sql = replace.execute( sql );
		}
		
		return sql;
	}
}
