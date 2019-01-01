package org.netsharp.organization.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.panda.session.PandaSession;

public class EmployeeOnlineListPart extends ListPart {

	public List<?> query() {

		ICacheCommand cmd = CacheCommandFactory.create(CacheType.redis, "session");

		List<EmployeeOnline> list = new ArrayList<EmployeeOnline>();
		Set<String> set = cmd.keys("*");
		for (String key : set) {
			PandaSession ps = (PandaSession) cmd.get(key);
			if (ps == null) {
				continue;
			}

			EmployeeOnline online = new EmployeeOnline();

			online.setName(ps.getSession().getUserName());
			online.setTenancyName(ps.getSession().getTenancyName());
			online.setDepartmentName(ps.getSession().getDepartmentName());
			online.setPosition(ps.getSession().getPostName());
			
			online.setLoginTime(ps.getSession().getLoginTime());
			online.setLastedTime(ps.getSession().getLastedTime());
			
			online.setIpAddress(ps.getUserClient().getIp());
			online.setExplorer(ps.getUserClient().getUserAgent());
			
			list.add(online);
		}

		return list;
	}
}

class EmployeeOnline {

	private String name;
	private String tenancyName;
	private String departmentName;
	private String position;
	private String ipAddress;
	private String explorer;
	private Date loginTime;
	private Date lastedTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getExplorer() {
		return explorer;
	}
	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLastedTime() {
		return lastedTime;
	}
	public void setLastedTime(Date lastedTime) {
		this.lastedTime = lastedTime;
	}
	public String getTenancyName() {
		return tenancyName;
	}
	public void setTenancyName(String tenancyName) {
		this.tenancyName = tenancyName;
	}
}