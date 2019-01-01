package org.netsharp.panda.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.netsharp.organization.entity.Operation;

public class UserPermission implements Serializable {

	private static final long serialVersionUID = 2546114162696528413L;
	
	private List<Operation> operations;              // 当前员工的操作权限
	private Map<String, List<String>> fieldGeteways; // 当前员工的字段权限
	private boolean isPermission;                   // 是否已经加载了权限数据	

	public void desctroy() {

		if (this.isPermission) {
			this.operations.clear();
			this.fieldGeteways.clear();
		}
	}

	public void reset() {

		this.isPermission = false;
		if (operations != null) {
			this.operations.clear();
		}

		if (operations != null) {
			this.fieldGeteways.clear();
		}
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Map<String, List<String>> getFieldGeteways() {
		return fieldGeteways;
	}

	public void setFieldGeteways(Map<String, List<String>> fieldGeteways) {
		this.fieldGeteways = fieldGeteways;
	}

	public boolean isPermission() {
		return isPermission;
	}

	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}
}
