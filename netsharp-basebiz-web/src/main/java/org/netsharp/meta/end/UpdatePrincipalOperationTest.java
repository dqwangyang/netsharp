package org.netsharp.meta.end;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IPrincipalOperationService;

public class UpdatePrincipalOperationTest {

	@Test
	public void run(){
		
		IPrincipalOperationService service = ServiceFactory.create(IPrincipalOperationService.class);
		
		service.restore();
	}
}
