package org.netsharp.wx.sdk.ep.department;

import java.util.List;

import org.netsharp.wx.sdk.ep.Response;

public class DepartmentListResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4061260189387478959L;
	private List<Department> department;

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}
	
}