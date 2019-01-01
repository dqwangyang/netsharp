package com.netsharp.communication.local;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({  
	NestTest.class,
	ServiceTest.class,
	TransactionTest.class,
	DataTableTest.class
	})
public class AllTests {
	
}
