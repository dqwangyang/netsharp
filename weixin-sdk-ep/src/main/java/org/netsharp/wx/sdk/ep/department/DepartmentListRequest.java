package org.netsharp.wx.sdk.ep.department;

import org.netsharp.wx.sdk.ep.Request;

public class DepartmentListRequest extends Request<DepartmentListResponse> {

	private Long id;//部门id。获取指定部门及其下的子部门
	
	public DepartmentListRequest() {
		super();
		this.responseType = DepartmentListResponse.class;
	}
	
	@Override
	protected DepartmentListResponse doResponse() {
		
		DepartmentListResponse response =this.executeHttpGet();
		
		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+this.getAccessToken()+"&id="+this.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
