package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.autoencoder.base.IEncoderRuleService;
import org.netsharp.autoencoder.base.IEncoderService;
import org.netsharp.autoencoder.entity.Encoder;
import org.netsharp.autoencoder.entity.EncoderRule;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.dbm.base.IDbmLogService;
import org.netsharp.dbm.base.IDbmService;
import org.netsharp.dbm.entity.DbmLog;
import org.netsharp.job.base.IJobLogService;
import org.netsharp.job.base.IJobService;
import org.netsharp.job.entity.Job;
import org.netsharp.job.entity.JobLog;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.base.IBeanService;
import org.netsharp.panda.base.IPDatagridColumnService;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.base.IPFormFieldService;
import org.netsharp.panda.base.IPFormService;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.base.IPPartTypeService;
import org.netsharp.panda.base.IPQueryItemService;
import org.netsharp.panda.base.IPQueryProjectService;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.base.IPluginService;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PPartType;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPAccordionService;
import org.netsharp.panda.plugin.base.IPNavigationItemService;
import org.netsharp.panda.plugin.base.IPNavigationService;
import org.netsharp.panda.plugin.base.IPToolbarItemService;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.panda.plugin.entity.PAccordionItem;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.platform.base.IBizDateService;
import org.netsharp.platform.entity.BizDate;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "平台工具";
		parentNodeCode = "platform";
		pluginName = "平台工具";
		seq = 101;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
		ResourceNode node1 = null;

		node1 = this.createResourceNodeCategory("UI工具", "UI_Tool", node.getId());{
		
			this.createResourceNodeVoucher(Plugin.class.getName(), "插件列表", Plugin.class.getSimpleName(), IPluginService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ResourceNode.class.getName(), "资源节点", ResourceNode.class.getSimpleName(), IResourceNodeService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(PAccordion.class.getName(), "模块管理", PAccordion.class.getSimpleName(), IPAccordionService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PAccordionItem.class.getName(), "AccordionItem", PAccordionItem.class.getSimpleName(), IPAccordionService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(PNavigation.class.getName(), "导航菜单", PNavigation.class.getSimpleName(), IPNavigationService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PNavigationItem.class.getName(), "导航菜单", PNavigationItem.class.getSimpleName(), IPNavigationItemService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(PWorkspace.class.getName(), "工作区", PWorkspace.class.getSimpleName(), IPWorkspaceService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PPart.class.getName(), "部件", PPart.class.getSimpleName(), IPPartService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PPartType.class.getName(), "部件类型", PPartType.class.getSimpleName(), IPPartTypeService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(PToolbar.class.getName(), "工具栏",PToolbar.class.getSimpleName(), IPToolbarService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PToolbarItem.class.getName(), "工具栏项目", PToolbarItem.class.getSimpleName(), IPToolbarItemService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(PDatagrid.class.getName(), "表格管理", PDatagrid.class.getSimpleName(), IPDatagridService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PDatagridColumn.class.getName(), "表格列", PDatagridColumn.class.getSimpleName(), IPDatagridColumnService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PRowStyler.class.getName(), "表格行样式", PRowStyler.class.getSimpleName(), IPDatagridService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(PForm.class.getName(), "表单管理", PForm.class.getSimpleName(), IPFormService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PFormField.class.getName(), "表单字段", PFormField.class.getSimpleName(), IPFormFieldService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(PQueryProject.class.getName(), "查询方案", PQueryProject.class.getSimpleName(), IPQueryProjectService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PQueryItem.class.getName(), "查询字段",PQueryItem.class.getSimpleName(), IPQueryItemService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(PReference.class.getName(), "参照管理",PReference.class.getSimpleName(), IPReferenceService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(Operation.class.getName(), "操作管理", Operation.class.getSimpleName(), IOperationService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(Encoder.class.getName(), "编码规则", Encoder.class.getSimpleName(), IEncoderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(EncoderRule.class.getName(), "编码规则明细", EncoderRule.class.getSimpleName(), IEncoderRuleService.class.getName(), node1.getId());
			
//			this.createResourceNodeVoucher(PPads.class.getName(), "工作台面板", PPads.class.getSimpleName(), IPPadsService.class.getName(), node.getId());
//			this.createResourceNodeVoucher(PPad.class.getName(), "Pad", PPad.class.getSimpleName(), IPPadsService.class.getName(), node.getId());
			
			this.createResourceNodeVoucher(BeanPath.class.getName(), "Action管理",BeanPath.class.getSimpleName(), IBeanPathService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Bean.class.getName(), "Action明细", Bean.class.getSimpleName(), IBeanService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(ResourceNode.class.getName(), "二次开发", "platform-tool", IResourceNodeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(OperationType.class.getName(), "操作类型", OperationType.class.getSimpleName(), IOperationTypeService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("作业管理", "JobManager", node.getId());
		{
			this.createResourceNodeVoucher(Job.class.getName(), "作业列表", Job.class.getSimpleName(), IJobService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(JobLog.class.getName(), "作业日志", JobLog.class.getSimpleName(), IJobLogService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("DBA工具", "DBA_Tool", node.getId());{

			this.createResourceNodeVoucher(DbmLog.class.getName(), "执行查询", "DBA_Query", IDbmService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DbmLog.class.getName(), "查询日志", "DBA_Log", IDbmLogService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("基础配置", "Platform_Biz", node.getId());{

			this.createResourceNodeVoucher(BizDate.class.getName(), "日期档案", "Platform_Biz_Date", IBizDateService.class.getName(), node1.getId());
		}
	}
}
