package org.netsharp.organization.filter;

import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

/** 当前组织下下的用户参照过滤条件*/
public class EmployeeReferenceFilter implements IReferenceFilterBuilder {

	@Override
	public String builderFilter() {
		
		StringBuilder sb=new StringBuilder();
		String[] pathCodes= SessionManager.getDepartmentPathCodes();		
		sb.append("organizations.organizationId IN (SELECT id FROM sys_permission_organization WHERE ");
		sb.append("path_code in ('").append(StringManager.join("','",pathCodes)).append("') ");
		for(int i=0,len=pathCodes.length;i<len;i++){
			sb.append("OR path_code like '").append(pathCodes[i]).append("%' ");
		}
		sb.append(")");
		return sb.toString();
	}

}
