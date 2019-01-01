package org.netsharp.meta.framework;

import org.junit.Test;
import org.netsharp.dataccess.DbSession;

public class SessionClose {
	@Test
	public void execute(){
		DbSession.getSession().manualClose();
	}
}
