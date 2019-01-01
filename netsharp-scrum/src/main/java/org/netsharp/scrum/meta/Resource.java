package org.netsharp.scrum.meta;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.attachment.Attachment;
import org.netsharp.attachment.IAttachmentService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.base.IBugLogService;
import org.netsharp.scrum.base.IBugService;
import org.netsharp.scrum.base.IDeployService;
import org.netsharp.scrum.base.IGoalService;
import org.netsharp.scrum.base.IIteratorStatisticsService;
import org.netsharp.scrum.base.IProductDemandService;
import org.netsharp.scrum.base.IProductService;
import org.netsharp.scrum.base.IProjectService;
import org.netsharp.scrum.base.IRoadmapDetailService;
import org.netsharp.scrum.base.IRoadmapService;
import org.netsharp.scrum.base.IStoryService;
import org.netsharp.scrum.base.IStoryTraceService;
import org.netsharp.scrum.base.ISupportService;
import org.netsharp.scrum.base.ITraceNoDailyService;
import org.netsharp.scrum.base.IUseCaseDetailService;
import org.netsharp.scrum.base.IUseCaseService;
import org.netsharp.scrum.base.IViewpointService;
import org.netsharp.scrum.entity.ActivityPlan;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.scrum.entity.BugLog;
import org.netsharp.scrum.entity.Deploy;
import org.netsharp.scrum.entity.Goal;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.IteratorStatistics;
import org.netsharp.scrum.entity.Product;
import org.netsharp.scrum.entity.ProductDemand;
import org.netsharp.scrum.entity.Project;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.scrum.entity.RoadmapDetail;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.entity.TraceNoDaily;
import org.netsharp.scrum.entity.UseCase;
import org.netsharp.scrum.entity.UseCaseDetail;
import org.netsharp.scrum.entity.Viewpoint;

public class Resource extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "研发SCRUM";
		parentNodeCode = "scrum";
		pluginName = "研发SCRUM";
		entityClass = Story.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		List<Class<?>> types = new ArrayList<Class<?>>();

		types.add(Story.class);
		types.add(StoryTrace.class); 
		types.add(Iteration.class);
		types.add(StoryParticipant.class);
		// types.add(ProjectTrace.class);
		types.add(Support.class);
		types.add(ActivityPlan.class);

		ResourceNode node1 = null;

		node1 = this.createResourceNodeCategory("项目管理", "prj-prj", node.getId());
		for (Class<?> type : types) {

			Mtable meta = MtableManager.getMtable(type);
			String serviceName = "org.netsharp.scrum.base.I" + type.getSimpleName() + "Service";
			this.createResourceNodeVoucher(type.getName(), meta.getName(), type.getSimpleName(), serviceName, node1.getId());
		}

		node1 = this.createResourceNodeCategory("我的", "prj-my", node.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "当前迭代", "my-scrum-current", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "未完成项目", "my-scrum-unfinished", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "已完成项目", "my-scrum-finished", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Support.class.getName(), "未完成支持", "my-support-unfinished", ISupportService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Support.class.getName(), "已完成支持", "my-support-finished", ISupportService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Support.class.getName(), "我提的支持", "my-support-putor", ISupportService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(StoryTrace.class.getName(), "我的跟进", "mytrace", IStoryTraceService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Bug.class.getName(), "我的缺陷", "bug-devp-my", IBugService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Viewpoint.class.getName(), "我的观点", "viewpoint-my", IViewpointService.class.getName(), node1.getId());
		
		this.createResourceNodeVoucher(Attachment.class.getName(), "附件", "support-Attachment", IAttachmentService.class.getName(), node1.getId());

		node1 = this.createResourceNodeCategory("部门工作", "department", node.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "当前迭代", "department-scrum-current", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "上个迭代", "department-scrum-last", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "未完成任务", "department-scrum-unfinished", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "已完成任务", "department-scrum-finished", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Support.class.getName(), "未完成支持", "department-support-unfinished", ISupportService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Support.class.getName(), "全部支持", "department-support-all", ISupportService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(StoryTrace.class.getName(), "跟进列表", "department-trace", IStoryTraceService.class.getName(), node1.getId());

		node1 = this.createResourceNodeCategory("迭代项目", "prj-iteration", node.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "当前迭代", "currentIterationProject", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "上个迭代", "lastIterationProject", IStoryService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Story.class.getName(), "下个迭代", "nextIterationProject", IStoryService.class.getName(), node1.getId());

		node1 = this.createResourceNodeCategory("缺陷管理", "bug", node.getId());
		this.createResourceNodeVoucher(Bug.class.getName(), "我的缺陷", "bug-testor-my", IBugService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Bug.class.getName(), "未完成缺陷", "bug-all-unfinished", IBugService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Bug.class.getName(), "缺陷列表", "bugList", IBugService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(BugLog.class.getName(), "缺陷日志", "bug-devp-log", IBugLogService.class.getName(), node1.getId());

		node1 = this.createResourceNodeCategory("日常管理", "prj-richang", node.getId());
		this.createResourceNodeVoucher(StoryTrace.class.getName(), "前天跟进", "trace2", IStoryTraceService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(StoryTrace.class.getName(), "昨天跟进", "trace1", IStoryTraceService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(StoryTrace.class.getName(), "今天跟进", "trace0", IStoryTraceService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(TraceNoDaily.class.getName(), "每日未跟进", "notracedaily", ITraceNoDailyService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Goal.class.getName(), MtableManager.getMtable(Goal.class).getName(), Goal.class.getSimpleName(), IGoalService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Viewpoint.class.getName(), "观点列表", "viewpoint-daily", IViewpointService.class.getName(), node1.getId());
		
		node1 = this.createResourceNodeCategory("项目管理", "prj-project", node.getId());
		this.createResourceNodeVoucher(Project.class.getName(), MtableManager.getMtable(Project.class).getName(), Project.class.getSimpleName(), IProjectService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Product.class.getName(), MtableManager.getMtable(Product.class).getName(), Product.class.getSimpleName(), IProductService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Deploy.class.getName(), MtableManager.getMtable(Deploy.class).getName(), Deploy.class.getSimpleName(), IDeployService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Roadmap.class.getName(), MtableManager.getMtable(Roadmap.class).getName(), Roadmap.class.getSimpleName(), IRoadmapService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(RoadmapDetail.class.getName(), MtableManager.getMtable(RoadmapDetail.class).getName(), RoadmapDetail.class.getSimpleName(), IRoadmapDetailService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(IteratorStatistics.class.getName(), "迭代统计", "reportmanage-iteratorstatistics-list", IIteratorStatisticsService.class.getName(), node1.getId());	
		this.createResourceNodeVoucher(ProductDemand.class.getName(), "需求", ProductDemand.class.getSimpleName(), IProductDemandService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(UseCase.class.getName(), "用例", UseCase.class.getSimpleName(), IUseCaseService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(UseCaseDetail.class.getName(), "子用例", UseCaseDetail.class.getSimpleName(), IUseCaseDetailService.class.getName(), node1.getId());
	}
}