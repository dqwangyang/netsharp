package org.netsharp.wx.sdk.ep.department;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class DepartmentUpdateRequest extends Request<DepartmentUpdateResponse> {

	private Integer id;
	private String name;
	private Integer parentid;
	private Integer order;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
