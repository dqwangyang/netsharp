package org.netsharp.scrum.meta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.scrum.meta.bug.BugWorkspaceTest;
import org.netsharp.scrum.meta.bug.UnfinishedBugWorkspaceTest;
import org.netsharp.scrum.meta.iteration.IterationCurrentStoryWorkspaceTest;
import org.netsharp.scrum.meta.iteration.IterationLastStoryWorkspaceTest;
import org.netsharp.scrum.meta.iteration.IterationNextStoryWorkspaceTest;
import org.netsharp.scrum.meta.iteration.IterationWorkspaceTest;
import org.netsharp.scrum.meta.my.MyTraceWorkspaceTest;
import org.netsharp.scrum.meta.my.StoryCurrentWorkspaceTest;
import org.netsharp.scrum.meta.my.StoryFinishedWorkspaceTest;
import org.netsharp.scrum.meta.my.StoryUnFinishedWorkspaceTest;
import org.netsharp.scrum.meta.my.SupportCreatedWorkspace;
import org.netsharp.scrum.meta.my.SupportFinishedWorkspace;
import org.netsharp.scrum.meta.my.SupportUnFinishedWorkspace;
import org.netsharp.scrum.meta.other.GoalWorkspaceTest;
import org.netsharp.scrum.meta.other.RoadmapDetailWorkspaceTest;
import org.netsharp.scrum.meta.other.RoadmapWorkspaceTest;
import org.netsharp.scrum.meta.project.ActivityPlanWorkspaceTest;
import org.netsharp.scrum.meta.project.DeployWorkspaceTest;
import org.netsharp.scrum.meta.project.ProductDemandWorkspaceTest;
import org.netsharp.scrum.meta.project.ProductWorkspaceTest;
import org.netsharp.scrum.meta.project.ProjectWorkspaceTest;
import org.netsharp.scrum.meta.ref.DeployReferneceTest;
import org.netsharp.scrum.meta.ref.IterationReferenceTest;
import org.netsharp.scrum.meta.ref.ProductReferenceTest;
import org.netsharp.scrum.meta.ref.ProjectReferenceTest;
import org.netsharp.scrum.meta.ref.RoadmapReferneceTest;
import org.netsharp.scrum.meta.ref.StoryReferneceTest;
import org.netsharp.scrum.meta.story.StoryParticipantWorkspaceTest;
import org.netsharp.scrum.meta.story.StoryToolbarTest;
import org.netsharp.scrum.meta.story.StoryTraceWorkspaceTest;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;
import org.netsharp.scrum.meta.story.SupportWorkspaceTest;
import org.netsharp.scrum.meta.trace.Trace0Test;
import org.netsharp.scrum.meta.trace.Trace1Test;
import org.netsharp.scrum.meta.trace.Trace2Test;
import org.netsharp.scrum.meta.trace.TraceNoDailyWorkspaceTest;
import org.netsharp.scrum.meta.usecase.CaseWorkspaceTest;
import org.netsharp.scrum.meta.viewpoint.MyViewpointWorkspaceTest;
import org.netsharp.scrum.meta.viewpoint.ViewpointWorkspaceTest;
import org.netsharp.scrum.report.IteratorStatisticsWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({
	
	Resource.class,
	StoryToolbarTest.class,
	StoryReferneceTest.class,
	IterationReferenceTest.class,
	ProductReferenceTest.class,
	ProjectReferenceTest.class,
	DeployReferneceTest.class,
	RoadmapReferneceTest.class,
	
//	//部门相关
//	DepartmentStoryCurrentTest.class,
//	DepartmentStoryFinishedTest.class,
//	DepartmentStoryLastTest.class,
//	DepartmentStoryUnfinishedTest.class,
//	DepartmentSupportAllTest.class,
//	DepartmentSupportUnfinishedTest.class,
//	DepartmentTraceTest.class,
	
	
	GoalWorkspaceTest.class,
	RoadmapWorkspaceTest.class,
	RoadmapDetailWorkspaceTest.class,
	StoryWorkspaceTest.class,
	StoryParticipantWorkspaceTest.class,
	IterationWorkspaceTest.class,
	SupportWorkspaceTest.class,
	StoryTraceWorkspaceTest.class,
	Trace0Test.class,
	Trace1Test.class,
	Trace2Test.class,
	TraceNoDailyWorkspaceTest.class,
	
	BugWorkspaceTest.class,
	org.netsharp.scrum.meta.bug.MyBugWorkspaceTest.class,
	org.netsharp.scrum.meta.my.MyBugWorkspaceTest.class,
	UnfinishedBugWorkspaceTest.class,
	
	ProductWorkspaceTest.class,
	ProjectWorkspaceTest.class,
	
	StoryCurrentWorkspaceTest.class,
	StoryUnFinishedWorkspaceTest.class,
	StoryFinishedWorkspaceTest.class,
	SupportUnFinishedWorkspace.class,
	SupportFinishedWorkspace.class,
	SupportCreatedWorkspace.class,
	
	IterationCurrentStoryWorkspaceTest.class,
	IterationLastStoryWorkspaceTest.class,
	IterationNextStoryWorkspaceTest.class,
	IteratorStatisticsWorkspaceTest.class,
	
	ActivityPlanWorkspaceTest.class,
	DeployWorkspaceTest.class,
	MyTraceWorkspaceTest.class,
	ProductDemandWorkspaceTest.class,
	
	ViewpointWorkspaceTest.class,
	MyViewpointWorkspaceTest.class,
	
	CaseWorkspaceTest.class,
	
	Navigation.class,
	})
public class AllTests {

}
