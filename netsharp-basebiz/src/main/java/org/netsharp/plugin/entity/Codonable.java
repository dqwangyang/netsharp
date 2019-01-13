package org.netsharp.plugin.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

/*插件项
 * 
 * */
public abstract class Codonable extends ResourceBizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7879958155811494182L;

	private Boolean disabled=false;       // 停用
	
    private String base;                   // 继承的父code
    
    private String parent;                 // 显示上级的code
    
    @Column(name="operation_id")
    private Long operationId;           // 功能权限
    
    @Column(name="path_id")
    private Long pathId;
    
    @Column(name="operation_type_id")
    private Long operationTypeId;
    
    @Column(name="operation_type_id2")
    private Long operationTypeId2;
    
    private Integer seq = 0;//排序
    
    @Exclusive
    private List<Codonable> childrens;
    
    //操作类型
    @Reference(foreignKey="operationTypeId",header="操作类型")
    private OperationType operationType;
    
    //操作类型2
    @Reference(foreignKey="operationTypeId2",header="操作类型2")
    private OperationType operationType2;

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Long getIdPath() {
		return getPathId();
	}

	public void setIdPath(Long idPath) {
		this.setPathId(idPath);
	}

	public Long getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Long idOperationType) {
		this.operationTypeId = idOperationType;
	}

	public Long getOperationTypeId2() {
		return operationTypeId2;
	}

	public void setOperationTypeId2(Long idOperationType2) {
		this.operationTypeId2 = idOperationType2;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
		if(this.operationType == null){
			this.operationTypeId = null;
		}
		else{
			this.operationTypeId=this.operationType.getId();
		}
	}

	public OperationType getOperationType2() {
		return operationType2;
	}

	public void setOperationType2(OperationType operationType2) {
		this.operationType2 = operationType2;
		
		if(this.operationType2 == null){
			this.operationTypeId2 = null;
		}
		else{
			this.operationTypeId2=this.operationType2.getId();
		}
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public List<Codonable> getChildrens() {
		if(childrens==null){
			childrens = new ArrayList<Codonable>();
		}
		return childrens;
	}

	public void setChildrens(List<Codonable> childrens) {
		this.childrens = childrens;
	}

	public Long getPathId() {
		return pathId;
	}

	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}
}