package org.netsharp.meta.framework;

import org.junit.Test;
import org.netsharp.dataccess.DbSession;

public class SessionStart {
	@Test
	public void execute(){
		
		DbSession.start().setManualClose(true);
	}
}
