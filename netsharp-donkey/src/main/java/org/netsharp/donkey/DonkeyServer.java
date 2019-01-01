package org.netsharp.donkey;

import org.netsharp.communication.registry.ServiceRegistry;

public class DonkeyServer {
	
	public static void main(String[] args) {
		
		ServiceRegistry.initialize();

		JobHelper.starts();
	}
}
