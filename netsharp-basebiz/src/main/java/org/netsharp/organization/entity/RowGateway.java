package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_permission_row_gateway",header="数据权限设置")
public class RowGateway extends Entity{

	private static final long serialVersionUID = 1484253774221083900L;
//	//QueryProject
//    @Reference(foreignKey="queryProjectId",referenceCode="QueryProject",referenceName="QueryProject")
//    private PQueryProject queryProject;
//    @Column(name="queryProjectId")
//    private Long queryProjectId;
//    //ResourceNode
//    @Reference(foreignKey="resourceNodeId",referenceCode="ResourceNode",referenceName="ResourceNode")
//    private ResourceNode resourceNode;
//    private Long resourceNodeId;
//    @Column(name="principalId")
//    private Long principalId;
//
//    public Long getQueryProjectId(){
//        return this.queryProjectId;
//    }
//    public RowGateway setQueryProjectId(Long queryProjectId){
//        this.queryProjectId=queryProjectId;
//        return this;
//    }
//    public Long getresourceNodeId(){
//        return this.resourceNodeId;
//    }
//    public RowGateway setresourceNodeId(Long resourceNodeId){
//        this.resourceNodeId=resourceNodeId;
//        return this;
//    }
//    public Long getPrincipalId(){
//        return this.principalId;
//    }
//    public RowGateway setPrincipalId(Long principalId){
//        this.principalId=principalId;
//        return this;
//    }
//	public PQueryProject getQueryProject() {
//		return queryProject;
//	}
//	public void setQueryProject(PQueryProject queryProject) {
//		this.queryProject = queryProject;
//	}
//	public ResourceNode getResourceNode() {
//		return resourceNode;
//	}
//	public void setResourceNode(ResourceNode resourceNode) {
//		this.resourceNode = resourceNode;
//	}
}