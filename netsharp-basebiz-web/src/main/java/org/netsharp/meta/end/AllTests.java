package org.netsharp.meta.end;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.meta.framework.SessionClose;

@RunWith(Suite.class)
@SuiteClasses({
	ResourcePathCodeTest.class,
	UpdatePrincipalOperationTest.class,
	SessionClose.class,
	})
public class AllTests {

}
