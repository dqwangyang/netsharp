package org.netsharp.meta.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	SessionStart.class,
	DbSchemaTest.class,
	PrincipalOperationBackup.class,
	ResourceNodeTest.class,
	
	PartTypeTest.class,
	WorkbenchTest.class,
	Navigation.class,
	PadsTest.class,
	ReferenceTest.class,
	})
public class AllTests {

}
