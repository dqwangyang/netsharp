package org.netsharp.wx.sdk.ep.department;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class DepartmentUpdateRequest extends Request<DepartmentUpdateResponse> {

	private Long id;
	private String name;
	private Long parentid;
	private Long order;

	public DepartmentUpdateRequest() {
		super();
		this.responseType = DepartmentUpdateResponse.class;
	}

	@Override
	protected DepartmentUpdateResponse doResponse() {

		Map<String, Object> item = new HashMap<String, Object>();
		{
			item.put("id", this.id);
			item.put("name", this.name);
			item.put("parentid", this.parentid);
			item.put("order", this.order);
		}

		String json = JsonManage.serialize2(item);

		DepartmentUpdateResponse response = this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + this.getToken().getAccess_token();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}
}
