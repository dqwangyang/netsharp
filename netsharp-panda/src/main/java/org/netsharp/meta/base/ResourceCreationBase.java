package org.netsharp.meta.base;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

public abstract class ResourceCreationBase {

	protected IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	protected String parentNodeName ;
	protected String parentNodeCode ;
	protected String pluginName;
	protected int seq = 0;
	protected Long parentId=null;
	protected Class<?> entityClass;

	@Test
	public void run() {
		ResourceNode node = this.createResourceNodeAddin(parentNodeName, parentNodeCode, pluginName,seq);
		this.createResourceNodeVouchers(node);
	}
	
	//在子类中重写
	protected void createResourceNodeVouchers(ResourceNode node) {
		String className = entityClass.getSimpleName();
		Mtable meta = MtableManager.getMtable(entityClass);

		String serviceName = "com.ykx.base.service.I" + className + "Service";
		node = createResourceNodeVoucher(meta.getEntityId(), "渠道管理", "SalesChannelBDOnline", serviceName, node.getId());
	}
	
	//在子类中重写
	protected void createResourceNodeVouchers(ResourceNode parentNode,Class<?> type,String code) {
		String className = type.getSimpleName();
		Mtable meta = MtableManager.getMtable(type);

		String serviceName = "com.ykx.base.service.I" + className + "Service";


		this.createResourceNodeVoucher(meta.getEntityId(), "渠道管理", code, serviceName, parentNode.getId());
	}

	protected ResourceNode createResourceNodeAddin(String nodeName, String nodeCode, String pluginName,int seq) {
		ResourceNode node = new ResourceNode();
		{
			node.toNew();
			node.setParentId(parentId);
			node.setCode(nodeCode);
			node.setName(nodeName);
			node.setResourceType(ResourceType.ADDIN);
			node.setSeq(seq);
		}
		Plugin plugin = new Plugin();
		{
			plugin.toNew();
			plugin.setAuthor("netsharp");
			plugin.setUrl("http://www.netsharp.org");
			plugin.setName(pluginName);
		}
		node.setPlugin(plugin);
		
		try{
			node = resourceNodeService.save(node);
		}catch(BusinessException e){
			if(e.getMessage().equals("资源code重复")){
				node=resourceNodeService.byCode(node.getCode());
			}else{
				throw e;
			}
		}
		
		return node;
	}

	protected ResourceNode createResourceNodeVoucher(String entityId, String nodeName, String nodeCode, String service, Long parentId) {
		
		ResourceNode node = new ResourceNode();
		{
			node.toNew();
			node.setParentId(parentId);
			node.setCode(nodeCode);
			node.setName(nodeName);
			node.setEntityId(entityId);
			node.setService(service);
			node.setResourceType(ResourceType.VOUCHER);
		}

		return resourceNodeService.save(node);
	}
	
	protected ResourceNode createResourceNodeCategory(String nodeName, String nodeCode,Long parentId) {
		
		ResourceNode node = new ResourceNode();
		{
			node.toNew();
			node.setParentId(parentId);
			node.setCode(nodeCode);
			node.setName(nodeName);
			node.setResourceType(ResourceType.CAT);
		}

		return resourceNodeService.save(node);
	}
}