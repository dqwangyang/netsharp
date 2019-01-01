package org.netsharp.organization.entity;

import java.io.Serializable;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.resourcenode.entity.ResourceNode;

@Table(name="sys_permission_operation_type",header="操作类型")
public class OperationType extends BizEntity implements Serializable{

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4980119056853086248L;

	@Column(name="icon",header="图标")
	private String icon;
	
    @Column(name="seq",header="顺序")
    private Integer seq;

    @Reference(foreignKey="resourceNodeId",header="")
	private ResourceNode resourceNode;
    
    @Column(name="resource_node_id",header="资源节点ID")
    private Integer resourceNodeId;

    public String getIcon(){
        return this.icon;
    }
    public OperationType setIcon(String icon){
        this.icon=icon;
        return this;
    }
    public Integer getresourceNodeId(){
        return this.resourceNodeId;
    }
    public OperationType setresourceNodeId(Integer resourceNodeId){
        this.resourceNodeId=resourceNodeId;
        return this;
    }

	public ResourceNode getResourceNode() {
		return resourceNode;
	}

	public void setResourceNode(ResourceNode resourceNode) {
		this.resourceNode = resourceNode;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}