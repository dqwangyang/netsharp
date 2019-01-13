package com.kaibo.poetry.meta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kaibo.core.meta.GradeClassWorkspaceTest;
import com.kaibo.core.meta.GradeDataTest;
import com.kaibo.core.meta.StudentWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	Resource.class,

	GradeDataTest.class,
	PoetryWorkspaceTest.class,
	StudentWorkspaceTest.class,
	GradeClassWorkspaceTest.class,
	
	Navigation.class,
	})
public class AllTests {

}