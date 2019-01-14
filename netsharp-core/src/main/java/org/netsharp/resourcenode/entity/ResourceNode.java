package org.netsharp.resourcenode.entity;

import java.io.Serializable;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.resourcenode.dic.ResourceType;

//资源节点	
@Table(name="rs_resource_node",header="资源节点")
public class ResourceNode extends CatEntity implements Serializable{

    private static final long serialVersionUID = -2339478156000134433L;
    
    @Column(name="resource_type",header="资源类型")
	private ResourceType resourceType;
	
    @Column(name="entity_id",size=300,header="实体")
    private String entityId;
    
    @Column(name="service",size=300,header="服务")
    private String service;
    
    @Column(name="plugin_id",header="插件")
    private Long pluginId;
    
    @CompositeOne(foreignKey="pluginId")
    private Plugin plugin;
    
    @Column(name="seq",header="排序")
    private Integer seq = 0;
    
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Plugin getPlugin() {
		return plugin;
	}
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Long getPluginId() {
		return pluginId;
	}
	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}