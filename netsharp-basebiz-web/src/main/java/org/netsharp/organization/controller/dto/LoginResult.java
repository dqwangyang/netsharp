package org.netsharp.organization.controller.dto;

import java.util.List;

public class LoginResult {

	private int result = Types.invalid;

	private List<Workbench> workbenchList;

	private Object data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Workbench> getWorkbenchList() {
		return workbenchList;
	}

	public void setWorkbenchList(List<Workbench> workbenchList) {
		this.workbenchList = workbenchList;
	}
	
	public class Types{
		public static final int invalid=0;//登录无效
		public static final int ok=1;//登录成功
		public static final int disabled=2;//员工停用
		public static final int defaultPwd=3;//登录成功,但是默认密码
	}
}
