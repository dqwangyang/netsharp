package org.netsharp.cache.plugin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ResourceTest.class,
	NavigationTest.class,
	CachePluginWorkspaceTest.class,
	})
public class AllTests {

}