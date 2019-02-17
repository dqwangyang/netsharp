package org.netsharp.panda;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	org.netsharp.meta.framework.AllTests.class,
	org.netsharp.meta.platform.AllTests.class,
	org.netsharp.meta.basebiz.AllTests.class,
//	org.netsharp.wx.meta.AllTests.class,
    org.netsharp.cache.plugin.AllTests.class,
    org.netsharp.scrum.meta.AllTests.class,
    com.kaibo.poetry.meta.AllTests.class,
    
	org.netsharp.meta.end.AllTests.class,
	
	})
public class AllTests {

}
