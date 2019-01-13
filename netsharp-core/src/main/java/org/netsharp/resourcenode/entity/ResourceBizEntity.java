package org.netsharp.resourcenode.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.entity.BizEntity;

/*资源管理的业务实体*/
public abstract class ResourceBizEntity extends BizEntity implements IResourceable {
	
	private static final long serialVersionUID = -3180992363495768912L;
	
	@Reference(foreignKey="resourceNodeId",header="资源节点")
	private ResourceNode resourceNode;
	
	@Column(name="resource_node_id",header="资源节点")
	private Long resourceNodeId;
	
	public ResourceNode getResourceNode() {
		return resourceNode;
	}
	public void setResourceNode(ResourceNode resourceNode) {
		this.resourceNode = resourceNode;
		if(this.resourceNode==null){
			this.resourceNodeId=null;
		}
		else{
			this.resourceNodeId=this.resourceNode.getId();
		}
	}
	public Long getResourceNodeId() {
		return resourceNodeId;
	}
	public void setResourceNodeId(Long resourceNodeId) {
		this.resourceNodeId = resourceNodeId;
	}
	
	@JsonIgnore
	public String getEntityId(){
		if(this.resourceNode!=null){
			return this.resourceNode.getEntityId();
		}
		
		return null;
	}
	
	@JsonIgnore
	public String getService(){
		if(this.resourceNode!=null){
			return this.resourceNode.getService();
		}
		
		return null;
	}
}
