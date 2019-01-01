package org.netsharp.wx.sdk.ep.department;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class DepartmentCreateRequest extends Request<DepartmentCreateResponse> {

	private Department department;

	public DepartmentCreateRequest() {
		super();
		this.responseType = DepartmentCreateResponse.class;
	}

	@Override
	protected DepartmentCreateResponse doResponse() {

		String json = JsonManage.serialize2(this.department);

		DepartmentCreateResponse response = this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + this.getAccessToken();
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
