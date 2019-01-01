package org.netsharp.panda.core.workbench;

import java.util.List;


public class LoginResultDTO {

	private int result = 0;

	/**   
	 * @Fields workbenchPtah : TODO(工作台路径)
	 */   
	private List<WorkbenchDTO> workbenchList;

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

	public List<WorkbenchDTO> getWorkbenchList() {
		return workbenchList;
	}

	public void setWorkbenchList(List<WorkbenchDTO> workbenchList) {
		this.workbenchList = workbenchList;
	}
}
