package org.netsharp.wx.sdk.ep.agent;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class AgentSetRequest  extends Request<AgentSetResponse> {
	
	private Integer agentid;//	企业应用的id
	private Integer report_location_flag;//	企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报
	private Integer logo_mediaid;//	企业应用头像的mediaid，通过多媒体接口上传图片获得mediaid，上传后会自动裁剪成方形和圆形两个头像
	private String name;//	企业应用名称
	private String description;//	企业应用详情
	private String redirect_domain;//	企业应用可信域名
	private Integer isreportuser;//	是否接收用户变更通知。0：不接收；1：接收
	private Integer isreportenter;//	是否上报用户进入应用事件。0：不接收；1：接收

	public AgentSetRequest() {
		super();
		this.responseType = AgentSetResponse.class;
	}
	
	@Override
	protected AgentSetResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("agentid", this.agentid);
			item.put("report_location_flag", this.report_location_flag);
			item.put("logo_mediaid", this.logo_mediaid);
			item.put("name", this.name);
			item.put("description", this.description);
			item.put("redirect_domain", this.redirect_domain);
			item.put("isreportuser", this.isreportuser);
			item.put("isreportenter", this.isreportenter);
		}
		
		String json = JsonManage.serialize2(item);
		
		AgentSetResponse response =this.executeHttpPost(json);

		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token="+this.getToken().getAccess_token();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getReport_location_flag() {
		return report_location_flag;
	}

	public void setReport_location_flag(Integer report_location_flag) {
		this.report_location_flag = report_location_flag;
	}

	public Integer getLogo_mediaid() {
		return logo_mediaid;
	}

	public void setLogo_mediaid(Integer logo_mediaid) {
		this.logo_mediaid = logo_mediaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRedirect_domain() {
		return redirect_domain;
	}

	public void setRedirect_domain(String redirect_domain) {
		this.redirect_domain = redirect_domain;
	}

	public Integer getIsreportuser() {
		return isreportuser;
	}

	public void setIsreportuser(Integer isreportuser) {
		this.isreportuser = isreportuser;
	}

	public Integer getIsreportenter() {
		return isreportenter;
	}

	public void setIsreportenter(Integer isreportenter) {
		this.isreportenter = isreportenter;
	}
	
	
}