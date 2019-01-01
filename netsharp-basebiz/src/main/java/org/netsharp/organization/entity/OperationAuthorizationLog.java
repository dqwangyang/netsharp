package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.dic.UiOperationType;

@Table(name="sys_authorization_log",header="授权日志")
public class OperationAuthorizationLog extends Entity {
	
	private static final long serialVersionUID = -3082447172410862092L;
	
	@Column(name="position_id",header="授权主体id(因增加职务授权主体，字段暂时先不变更")
    private Integer positionId;
	
	@Column(name="position_name",header="授权主体名称")
    private String positionName;

	@Column(name="operation_authorization_text",header="操作权限文本",size=10000)
	private String operationAuthorizationText;
	
	@Column(name="operation_type",header="操作类型")
	private UiOperationType operationType;

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOperationAuthorizationText() {
		return operationAuthorizationText;
	}

	public void setOperationAuthorizationText(String operationAuthorizationText) {
		this.operationAuthorizationText = operationAuthorizationText;
	}

	public UiOperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(UiOperationType operationType) {
		this.operationType = operationType;
	}

}
