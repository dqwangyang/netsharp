package org.netsharp.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
	TestDropTable.class,
	TestCreateTable.class,
	PersistTest.class,
	QueryTest.class,
	})
public class AllTests {

}
