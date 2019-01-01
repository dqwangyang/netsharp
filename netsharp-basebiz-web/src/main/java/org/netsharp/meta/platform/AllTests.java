package org.netsharp.meta.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.meta.basebiz.ToolbarTest;
import org.netsharp.meta.platform.biz.BizDateWorkspaceTest;
import org.netsharp.meta.platform.data.PlatformToolbarTest;
import org.netsharp.meta.platform.dbm.DbmLogWorkspace;
import org.netsharp.meta.platform.dbm.DbmWorkspace;
import org.netsharp.meta.platform.job.JobLogWorkspaceTest;
import org.netsharp.meta.platform.job.JobWorkspaceTest;
import org.netsharp.meta.platform.operation.OperationTypeTest;
import org.netsharp.meta.platform.operation.OperationTypeWorkspaceTest;
import org.netsharp.meta.platform.ref.DatagridReferenceIdTest;
import org.netsharp.meta.platform.ref.FormReferenceTest;
import org.netsharp.meta.platform.ref.OperationTypeRefTest;
import org.netsharp.meta.platform.ref.PartTypeReferenceTest;
import org.netsharp.meta.platform.ref.PluginReferenceTest;
import org.netsharp.meta.platform.ref.QueryProjectReferenceTest;
import org.netsharp.meta.platform.ref.ReferenceReferenceTest;
import org.netsharp.meta.platform.ref.ResourceNodeReferenceTest;

@RunWith(Suite.class)
@SuiteClasses({
	ResourceTest.class,
	
	OperationTypeTest.class,
	
	ToolbarTest.class,
	PlatformToolbarTest.class,
	QueryProjectReferenceTest.class,
	ReferenceReferenceTest.class,
	ResourceNodeReferenceTest.class,
	DatagridReferenceIdTest.class,
	PartTypeReferenceTest.class,
	FormReferenceTest.class,
	PluginReferenceTest.class,
	OperationTypeRefTest.class,
	
	ListWorkspaceTest.class,
	FormWorkspaceTest.class,
	ResourceNodeWorkspace.class,
	PluginWorkspaceTest.class,
	ReferenceWorkspace.class,
	PWorkspaceWorkspaceTest.class,
	ToolbarWorkspaceTest.class,
	PAccordionWorkspaceTest.class,
	TreeWorkspaceTest.class,
	QueryProjectWorkspace.class,
	OperationWorkspaceTest.class,
//	PadsWorkspaceTest.class,
	BeanPathWorkspaceTest.class,
	DbmWorkspace.class,
	DbmLogWorkspace.class,
    JobWorkspaceTest.class,
	JobLogWorkspaceTest.class,
	EncoderWorkspaceTest.class,
	OperationTypeWorkspaceTest.class, 
	BizDateWorkspaceTest.class,
	
	NavigationTest.class,
	})
public class AllTests {

}