package org.netsharp.meta.base;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.plugin.base.IPAccordionService;
import org.netsharp.panda.plugin.base.IPNavigationService;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.panda.plugin.entity.PAccordionItem;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class NavigationBase {

	protected String treeName;
	protected String treePath;
	protected String treeCode;
	protected String resourceNode;

	protected IPNavigationService treeService = ServiceFactory.create(IPNavigationService.class);
	protected IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
	protected IPAccordionService accordionService = ServiceFactory.create(IPAccordionService.class);
	protected IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	@Test
	public void create() {
		
		this.createAccodions();
		this.createPTree();
	}

	public void createAccodions() {

		// this.doCreateAccodions("", "","", -1);

	}

	/*demo使用*/
	public void doCreateAccodions(String code, String name, String icon,int seq) {

		String accordionPath = "panda/workbench/accordion";
		ResourceNode rn = this.resourceNodeService.byCode(this.resourceNode);

		if( rn == null ) {
			throw new PandaException("未能找到资源节点：" + this.resourceNode);
		}

		IPAccordionService accordionService = ServiceFactory.create(IPAccordionService.class);

		PAccordion accordion = new PAccordion();
		{
			accordion.setPath(accordionPath);
			accordion.setName(name);
			accordion.setCode(code);
			accordion.toNew();
			accordion.setResourceNode(rn);
		}

		PAccordionItem item = null;
		item = new PAccordionItem();
		{
			item.toNew();
			item.setName(name);
			item.setCode(code);
			item.setTreePath(this.treePath);
			item.setSeq(seq);
			item.setIcon(icon);
			item.setResourceNode(rn);
			accordion.getItems().add(item);
		}

		accordionService.save(accordion);

	}

	protected void createPTree() {
		
		ResourceNode rn = this.resourceNodeService.byCode(this.resourceNode);
		
		if( rn == null ) {
			throw new PandaException("未能找到资源节点：" + this.resourceNode);
		}
		
		PNavigation tree = new PNavigation();
		{
			tree.toNew();
			tree.setName(treeName);
			tree.setPath(treePath);
			tree.setCode(treeCode);
			tree.setResourceNode(rn);
		}

		this.doCreateTree(tree);

		grantPermission(tree);
		treeService.save(tree);
	}

	protected void doCreateTree(PNavigation tree) {

	}

	protected void grantPermission(PNavigation tree) {

		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		for (PNavigationItem pnode : tree.getTreeNodes()) {

			if (StringManager.isNullOrEmpty(pnode.getUrl())) {
				continue;
			}

			IResourceNodeService nodeService = ServiceFactory.create(IResourceNodeService.class);
			ResourceNode rn = nodeService.byCode(pnode.getCode());
			pnode.setOperationType(operationType);
			pnode.setResourceNode(rn);
			
			if(!StringManager.isNullOrEmpty(pnode.getCode()) && rn == null){
				throw new PandaException("TreeNode的编码没有对应资源节点的编码："+pnode.getCode());
			}
		}
	}

	/*
	 * code 必须对应资源编码，否则授权无效
	 * */
	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url, int seq) {

		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setCode(code);
			node.setName(name);
			node.setUrl(url);
			node.setParent(parentCode);
			node.setSeq(seq);
			node.setIcon(icon);
			
			tree.getTreeNodes().add(node);
		}

		return node;
	}

	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url) {

		return this.createPTreeNode(tree, parentCode, icon,code, name, url, 0);
	}

	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url, OpenMode openMode, Integer width, Integer height, int seq) {

		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setCode(code);
			node.setName(name);
			node.setUrl(url);
			node.setParent(parentCode);
			node.setOpenMode(openMode);
			node.setWindowWidth(width);
			node.setWindowHeight(height);
			node.setSeq(seq);
			node.setIcon(icon);
			tree.getTreeNodes().add(node);
		}

		return node;
	}

	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url, OpenMode openMode, Integer width, Integer height) {

		return this.createPTreeNode(tree, parentCode,icon, code, name, url, openMode, width, height, 0);
	}
}
