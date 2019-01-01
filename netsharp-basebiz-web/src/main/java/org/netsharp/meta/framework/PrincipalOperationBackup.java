package org.netsharp.meta.framework;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IPrincipalOperationService;

public class PrincipalOperationBackup {
	
	@Test
	public void backup(){
		
		IPrincipalOperationService service = ServiceFactory.create(IPrincipalOperationService.class);
		service.backup();
		
	}
}
