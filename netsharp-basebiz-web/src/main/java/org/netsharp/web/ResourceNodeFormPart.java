package org.netsharp.web;

import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceNodeFormPart extends FormPart {
	@Override
	public IPersistable save(IPersistable entity) {

		ResourceNode node = (ResourceNode)entity;
		if(node.getPlugin()!=null){
			node.getPlugin().toTransient();
		}

		return super.save(entity);
	}
}
