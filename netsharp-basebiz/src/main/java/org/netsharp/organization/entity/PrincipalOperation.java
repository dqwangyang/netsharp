package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_permission_principal_operation",header="授权主体操作")
public class PrincipalOperation extends Entity{


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6228872005877869129L;

	@Column(name="principal_id",header="")
    private Integer principalId;

    @Reference(foreignKey="operationId")
    private Operation operation;
    
    @Column(name="operation_id",header="操作Id")
    private Integer operationId;
    
    @Column(name = "operation_code",header="操作编码")
    private String operationCode;
    
    @Column(name = "resourcenode_code",header="资源节点编码")
    private String resourcenodeCode;

    public Integer getPrincipalId(){
        return this.principalId;
    }
    public PrincipalOperation setPrincipalId(Integer principalId){
        this.principalId=principalId;
        return this;
    }
    public Integer getOperationId(){
        return this.operationId;
    }
    public PrincipalOperation setOperationId(Integer operationId){
        this.operationId=operationId;
        return this;
    }

	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
		if(this.operation==null){
			this.operationId=null;
		}
		else{
			this.operationId=this.getOperation().getId();
		}
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getResourcenodeCode() {
		return resourcenodeCode;
	}

	public void setResourcenodeCode(String resourcenodeCode) {
		this.resourcenodeCode = resourcenodeCode;
	}
    
}