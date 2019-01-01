package org.netsharp.organization.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

@Table(name="sys_permission_operation",header="操作")
public class Operation extends ResourceBizEntity implements Serializable{

    private static final long serialVersionUID = 2806098529583560551L;
    
    @Column(name="field_geteway",header="字段权限")
	private boolean fieldGeteway = false;

    @Subs(subType=FieldGeteway.class,foreignKey="operationId",header="字段权限")
    private List<FieldGeteway> fieldGeteways;

    @Reference(foreignKey="operationTypeId",header="")
    private OperationType operationType;
    
    @Column(name="operation_type_id",header="操作类型")
    private Integer operationTypeId;

	@Column(name="seq",header="顺序")
    private Integer seq;

    public boolean isFieldGeteway() {
		return fieldGeteway;
	}
	public void setFieldGeteway(boolean fieldGeteway) {
		this.fieldGeteway = fieldGeteway;
	}
	public List<FieldGeteway> getFieldGeteways(){
        if(this.fieldGeteways==null){
            this.fieldGeteways=new ArrayList<FieldGeteway>();
        }
        return this.fieldGeteways;
    }
    public Integer getOperationTypeId(){
        return this.operationTypeId;
    }
    public Operation setOperationTypeId(Integer operationTypeId){
        this.operationTypeId=operationTypeId;
        return this;
    }
    
	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
		
		if(this.operationType==null){
			this.operationTypeId=null;
		}
		else{
			this.operationTypeId=operationType.getId();
		}
	}

	public void setFieldGeteways(List<FieldGeteway> fieldGeteways) {
		this.fieldGeteways = fieldGeteways;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}