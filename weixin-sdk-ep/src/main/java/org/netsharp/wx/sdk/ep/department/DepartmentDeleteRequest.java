package org.netsharp.wx.sdk.ep.department;

import org.netsharp.wx.sdk.ep.Request;

public class DepartmentDeleteRequest extends Request<DepartmentDeleteResponse> {
	
	private Long id;

	public DepartmentDeleteRequest() {
		super();
		this.responseType = DepartmentDeleteResponse.class;
	}
	
	@Override
	protected DepartmentDeleteResponse doResponse() {
		
		DepartmentDeleteResponse response =this.executeHttpGet();
		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+this.getAccessToken()+"&id="+this.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

