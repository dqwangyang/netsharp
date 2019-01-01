package org.netsharp.meta.end;

import org.junit.Test;
import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourcePathCodeTest {
	
	@Test
	public void run(){
		ICatEntityService catService = ServiceFactory.create(ICatEntityService.class);
		catService.generatePathCode(ResourceNode.class.getName());
	}
}
