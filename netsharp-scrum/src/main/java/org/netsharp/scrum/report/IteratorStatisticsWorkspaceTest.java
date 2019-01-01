package org.netsharp.scrum.report;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.IteratorStatistics;

/**
 * 迭代统计生成报表服务
 * @author MengLingqin
 * 2016-06-07 10:27
 */
public class IteratorStatisticsWorkspaceTest extends WorkspaceCreationBase {

	IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
	IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class);

	@Override
	@Before
	public void setup() {
		super.setup();
		urlList = "/reportmanage/iteratorstatistics/list";
		entity = IteratorStatistics.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "reportmanage-iteratorstatistics-list";
	}
	
	@Test
	public void run() {
		this.createListWorkspace();
	}

	@Override
	public void createListWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);
		PDatagrid datagrid = this.createDatagrid(node);
		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setCode("iteratorstatistics");
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setName("迭代统计");
			workspace.setUrl(urlList);

		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("iteratorstatistics");
			part.setResourceNode(node);
			part.setName("迭代统计");
			part.setPartTypeId(PartType.TREEGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setUrl(urlForm);

		}
		workspace.getParts().add(part);
		workspaceService.save(workspace);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter(listFilter);
		PQueryProject queryProject = this.createQueryProject(node);
		datagrid.setQueryProject(queryProject);
		datagrid.setReadOnly(true);
		
		addColumn(datagrid, "state", "审核状态", ControlTypes.ENUM_BOX, 80, false);
		
		PDatagridColumn column = this.addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 40, false);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = this.addColumn(datagrid, "itemTotalCount", "项目数", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "itemEstimationTime", "项目估时", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "itemRealTime", "项目耗时", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "finishedItemTotalCount", "已完成数", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "finishedItemEstimationTime", "已完成估时", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "finishedItemRealTime", "已完成耗时", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "unFinishedItemTotalCount", "未完成数", ControlTypes.NUMBER_BOX, 100, false);
		{
			column.setStyler("if (value > 0){return 'color:red;';}");
		}
		column = this.addColumn(datagrid, "unFinishedItemEstimationTime", "未完成估时", ControlTypes.NUMBER_BOX, 100, false);
		{
			column.setStyler("if (value > 0){return 'color:red;';}");
		}
		
		column = this.addColumn(datagrid, "supportTotalCount", "支持数", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "supportEstimationTime", "支持估时", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "supportRealTime", "支持耗时", ControlTypes.NUMBER_BOX, 80, false);
		column = this.addColumn(datagrid, "finishedSupportTotalCount", "已完成数", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "finishedSupportEstimationTime", "已完成估时", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "finishedSupportRealTime", "已完成耗时", ControlTypes.NUMBER_BOX, 100, false);
		column = this.addColumn(datagrid, "unfinishedSupportTotalCount", "未完成数", ControlTypes.NUMBER_BOX, 100, false);
		{
			column.setStyler("if (value > 0){return 'color:red;';}");
		}
		column = this.addColumn(datagrid, "unfinishedSupportEstimationTime", "未完成估时", ControlTypes.NUMBER_BOX, 100, false);
		{
			column.setStyler("if (value > 0){return 'color:red;';}");
		}

		datagrid.setQueryProject(this.createQueryProject(node));
		datagrid.setReadOnly(true);
		datagrid.setPagination(false);
		datagrid.setAutoQuery(false);
		datagrid.setOrderby("iterationId desc");
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("迭代统计");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(3);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("iteration.name");
			item.setHeader("迭代");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Iteration.class.getSimpleName());
			item.setReference(reference);
		}
		queryProject.getQueryItems().add(item);
		return queryProject;
	}

	@Test
	public void operation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
