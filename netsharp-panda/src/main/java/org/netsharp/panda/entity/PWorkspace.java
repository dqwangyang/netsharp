package org.netsharp.panda.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.dic.WorkspaceType;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

@Table(name = "ui_pa_workspace", header = "工作区")
public class PWorkspace extends ResourceBizEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7729716502295093363L;

	@Column(name = "url", header = "路径")
	private String url;

	@Column(name = "redirect_url", header = "跳转的url", size = 1000)
	private String redirectUrl;

	@Column(name = "js_controller", header = "客户端控制器", size = 500)
	private String jsController;

	@Column(name = "service_controller", header = "服务端控制器", size = 500)
	private String serviceController;

	@Column(name = "operation_id", header = "操作Id")
	private Integer operationId;

	@Column(name = "cached", header = "缓存")
	protected boolean cached = false;

	@Column(name = "operation_type_id", header = "操作类型1")
	private Integer operationTypeId;

	@Column(name = "operation_type_id2", header = "操作类型2")
	private Integer operationTypeId2;

	@Reference(foreignKey = "operationTypeId", header = "操作类型")
	private OperationType operationType;

	@Reference(foreignKey = "operationTypeId2", header = "操作类型2")
	private OperationType operationType2;

	@Column(name = "type", header = "类型")
	private WorkspaceType type = WorkspaceType.GENERAL;

	@Subs(subType = PPart.class, foreignKey = "workspaceId", header = "部件")
	private List<PPart> parts = new ArrayList<PPart>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsController() {
		return jsController;
	}

	public void setJsController(String jsController) {
		this.jsController = jsController;
	}

	public String getServiceController() {
		return serviceController;
	}

	public void setServiceController(String serviceController) {
		this.serviceController = serviceController;
	}

	public List<PPart> getParts() {
		return parts;
	}

	public void setParts(List<PPart> parts) {
		this.parts = parts;
	}

	public Integer getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Integer idOperationType) {
		this.operationTypeId = idOperationType;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Integer getOperationTypeId2() {
		return operationTypeId2;
	}

	public void setOperationTypeId2(Integer idOperationType2) {
		this.operationTypeId2 = idOperationType2;
	}

	public OperationType getOperationType2() {
		return operationType2;
	}

	public void setOperationType2(OperationType operationType2) {
		this.operationType2 = operationType2;
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public boolean isCached() {
		return cached;
	}

	public void setCached(boolean cached) {
		this.cached = cached;
	}

	public WorkspaceType getType() {
		return type;
	}

	public void setType(WorkspaceType type) {
		this.type = type;
	}
}