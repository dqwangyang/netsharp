package org.netsharp.organization.filter;

import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.session.SessionManager;

/**当前组织下的岗位参照动态过滤 */
public class OrganizationReferenceFilter implements IReferenceFilterBuilder {

	@Override
	public String builderFilter() {
		
		StringBuilder sb=new StringBuilder();
		String[] pathCodes= SessionManager.getDepartmentPathCodes();
		sb.append("(path_code like '").append(pathCodes[0]).append("%' ");
		for(int i=1,len=pathCodes.length;i<len;i++){
			sb.append("OR path_code like '").append(pathCodes[i]).append("%' ");
		}
		sb.append(")");
		return sb.toString();
	}

}
