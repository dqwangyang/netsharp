package org.netsharp.wx.sdk.ep.user;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.ep.Request;

public class UserSimpleListRequest  extends Request<UserSimpleListResponse> {

	private Integer department_id;
	private Integer fetch_child;    // 1/0：是否递归获取子部门下面的成员
	private Integer status;         // 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加

	public UserSimpleListRequest() {
		super();
		this.responseType = UserSimpleListResponse.class;
	}

	@Override
	protected UserSimpleListResponse doResponse() {
		
		UserSimpleListResponse response =this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		
		List<String> ss = new ArrayList<String>();
		{
			ss.add("department_id="+this.department_id);
			ss.add("fetch_child="+this.fetch_child);
			ss.add("status="+this.status);
			ss.add("access_token="+this.getAccessToken());
		}
		
		return "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?" + StringManager.join("&", ss);
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getFetch_child() {
		return fetch_child;
	}

	public void setFetch_child(Integer fetch_child) {
		this.fetch_child = fetch_child;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
