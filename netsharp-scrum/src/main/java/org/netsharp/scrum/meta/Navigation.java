package org.netsharp.scrum.meta;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.scrum.entity.ActivityPlan;
import org.netsharp.scrum.entity.Deploy;
import org.netsharp.scrum.entity.Goal;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.Product;
import org.netsharp.scrum.entity.ProductDemand;
import org.netsharp.scrum.entity.Project;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.scrum.entity.RoadmapDetail;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.entity.UseCase;

public class Navigation extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "研发SCRUM";
		this.treeCode = "scrum";
		this.treePath = "panda/navigation/scrum";
		this.resourceNode = "scrum";
	}

	public void createAccodions() {

		this.doCreateAccodions("scrum", "研发SCRUM", "fa fa-connectdevelop fa-fw",998);
	}

	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "", "my", "我的", "", 1);
		{
			createPTreeNode(tree, "my", null, "my-scrum-current", "当前迭代", "/scrum/my/current/list", 1);
			createPTreeNode(tree, "my", null, "my-scrum-unfinished", "未完成任务", "/scrum/my/unfinished/list", 2);
			createPTreeNode(tree, "my", null, "my-scrum-finished", "已完成任务", "/scrum/my/finished/list", 3);
			createPTreeNode(tree, "my", null, "my-support-unfinished", "未完成支持", "/scrum/my/support/unfinished/list", 4);
			createPTreeNode(tree, "my", null, "my-support-finished", "已完成支持", "/scrum/my/support/finished/list", 5);
			createPTreeNode(tree, "my", null, "my-support-putor", "我提的支持", "/scrum/my/support/putor/list", 5);
			createPTreeNode(tree, "my", null, "mytrace", "我的跟进", "/scrum/my/trace/list", 6);
			createPTreeNode(tree, "my", null, "bug-devp-my", "我的缺陷", "/scrum/my/bug/list", 7);
			createPTreeNode(tree, "my", null, "viewpoint-my", "我的观点", "/scrum/my/viewpoint/list", 7);
		}
		
//		createPTreeNode(tree, null, "", "department", "部门工作", "", 2);
//		{
//			createPTreeNode(tree, "department", null, "department-scrum-current", "当前迭代", "/scrum/department/current/list", 1);
//			createPTreeNode(tree, "department", null, "department-scrum-last", "上个迭代", "/scrum/department/last/list", 2);
//			createPTreeNode(tree, "department", null, "department-scrum-unfinished", "未完成任务", "/scrum/department/unfinished/list", 3);
//			createPTreeNode(tree, "department", null, "department-scrum-finished", "已完成任务", "/scrum/department/finished/list", 4);
//			createPTreeNode(tree, "department", null, "department-support-unfinished", "未完成支持", "/scrum/department/support/unfinished/list", 5);
//			createPTreeNode(tree, "department", null, "department-support-all", "全部支持", "/scrum/department/support/finished/list", 6);
//			createPTreeNode(tree, "department", null, "department-trace", "跟进列表", "/scrum/department/trace/list", 7);
//		}

		createPTreeNode(tree, null, "", "iteration", "迭代管理", "", 3);
		{
			createPTreeNode(tree, "iteration", null, "currentIterationProject", "当前迭代", "/scrum/story/CurrentIterationProject/list", 1);
			createPTreeNode(tree, "iteration", null, "lastIterationProject", "上个迭代", "/scrum/story/lastIterationProject/list", 2);
			createPTreeNode(tree, "iteration", null, "nextIterationProject", "下个迭代", "/scrum/story/nextIterationProject/list", 3);
			createPTreeNode(tree, "iteration", null, Support.class.getSimpleName(), "支持列表", "/scrum/support/list", 4);
			createPTreeNode(tree, "iteration", null, Story.class.getSimpleName(), "任务列表", "/scrum/story/list", 5);
			createPTreeNode(tree, "iteration", null, Iteration.class.getSimpleName(), "迭代列表", "/scrum/iteration/list", 6);
		}

		createPTreeNode(tree, null, "", "days", "日常管理", "", 4);
		{
			createPTreeNode(tree, "days", null, StoryTrace.class.getSimpleName(), "任务跟进", "/scrum/trace/list", 1);
			createPTreeNode(tree, "days", null, StoryParticipant.class.getSimpleName(), "任务参与表", "/scrum/participant/list", 2);
			createPTreeNode(tree, "days", null, "trace2", "前天跟进", "/scrum/trace2/list", 3);
			createPTreeNode(tree, "days", null, "trace1", "昨天跟进", "/scrum/trace1/list", 4);
			createPTreeNode(tree, "days", null, "trace0", "今天跟进", "/scrum/trace0/list", 5);
			createPTreeNode(tree, "days", null, "notracedaily", "每日未跟进", "/scrum/notracedaily/list", 5);
			createPTreeNode(tree, "days", null, Goal.class.getSimpleName(), "目标管理", "/scrum/goal/list", 6);
			createPTreeNode(tree, "days", null, "viewpoint-daily", "观点列表", "/scrum/viewpoint/list", 7);
		}

		createPTreeNode(tree, null, "", "product", "产品项目", "", 5);
		{
			createPTreeNode(tree, "product", null, Project.class.getSimpleName(), "项目列表", "/scrum/project/list", 1);
			createPTreeNode(tree, "product", null, Product.class.getSimpleName(), "产品列表", "/scrum/product/list", 2);
			createPTreeNode(tree, "product", null, Deploy.class.getSimpleName(), "上线计划", "/scrum/deployplan/list", 3);
			createPTreeNode(tree, "product", null, ActivityPlan.class.getSimpleName(), "活动计划", "/scrum/activityplan/list", 4);
			createPTreeNode(tree, "product", null, "reportmanage-iteratorstatistics-list", "迭代统计", "/reportmanage/iteratorstatistics/list", 5);
			createPTreeNode(tree, "product", null, Roadmap.class.getSimpleName(), "路线图", "/scrum/roadmap/list", 6);
			createPTreeNode(tree, "product", null, RoadmapDetail.class.getSimpleName(), "路线图明细", "/scrum/roadmap/detail/list", 7);
			createPTreeNode(tree, "product", null, ProductDemand.class.getSimpleName(), "需求列表", "/scrum/product/demand/list", 8);
			createPTreeNode(tree, "product", null, UseCase.class.getSimpleName(), "用例列表", "/scrum/case/list", 9);
		}

		createPTreeNode(tree, null, "", "bug", "缺陷管理", "", 6);
		{
			createPTreeNode(tree, "bug", null, "bug-testor-my", "我的缺陷", "/bug/testor/my/list", 1);
			createPTreeNode(tree, "bug", null, "bug-all-unfinished", "未完成缺陷", "/bug/unfinished/list", 2);
			createPTreeNode(tree, "bug", null, "bugList", "缺陷列表", "/bug/list", 3);
		}
	}
}