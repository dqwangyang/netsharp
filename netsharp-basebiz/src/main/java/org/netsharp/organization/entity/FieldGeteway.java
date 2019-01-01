package org.netsharp.organization.entity;

import java.io.Serializable;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="sys_permission_field_geteway",header="字段权限")
public class FieldGeteway extends BizEntity implements Serializable{

    private static final long serialVersionUID = 6284500604201575087L;
    
    @Column(name="entity_id",header="实体Id")
	protected String entityId;
	
	@Column(name="property_name",header="实体属性")
    protected String propertyName;

    @Reference(foreignKey="operationId",header="Operation")
	private Operation operation;
    
    @Column(name="operation_id",header="操作")
    protected Integer operationId;

    public FieldGeteway(){
        super();
    }

    public String getEntityId(){
        return this.entityId;
    }
    public FieldGeteway setEntityId(String entityId){
        this.entityId=entityId;
        return this;
    }
    public String getPropertyName(){
        return this.propertyName;
    }
    public FieldGeteway setPropertyName(String propertyName){
        this.propertyName=propertyName;
        return this;
    }
    public Integer getOperationId(){
        return this.operationId;
    }
    public FieldGeteway setOperationId(Integer operationId){
        this.operationId=operationId;
        return this;
    }

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}