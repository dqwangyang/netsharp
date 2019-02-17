package org.netsharp.meta.end;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.meta.basebiz.data.EmployeeDataTest;
import org.netsharp.meta.framework.SessionClose;

@RunWith(Suite.class)
@SuiteClasses({
	ResourcePathCodeTest.class,
	EmployeeDataTest.class,
	UpdatePrincipalOperationTest.class,
	SessionClose.class,
	})
public class AllTests {

}
