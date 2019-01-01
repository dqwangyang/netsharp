package org.netsharp.dataccess.replace;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

/*当前登录用户部门下的所有员工*/
public class ReplaceDepartmentEmployees extends ReplaceBase {
	
	public ReplaceDepartmentEmployees() {
		this.key = "{department-employee-ids}";;
	}
	
	public String doExecute(String cmdText) {
		
		String oeTable = "sys_permission_organization_employee";
		String orTable = "sys_permission_organization";
		
		String[] pathCodes = SessionManager.getDepartmentPathCodes();
		if(pathCodes==null || pathCodes.length==0){
			String nofilter = "SELECT employeeId FROM " + oeTable + " where 1=0";
			return cmdText.replace(this.key, nofilter);
		}
		
		List<String> likes = new ArrayList<String>();
		for(String pathCode : pathCodes){
			likes.add("path_code LIKE '"+pathCode+"%'");
		}
		
		String filter ="SELECT employeeId FROM "+ oeTable + " WHERE organizationId IN(SELECT id FROM "+ orTable +" WHERE "+StringManager.join(" OR ", likes)+")";
		
		return cmdText.replace(this.key, filter);
	}

}
