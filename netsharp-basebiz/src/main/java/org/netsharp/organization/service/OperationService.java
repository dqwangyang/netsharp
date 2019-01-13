package org.netsharp.organization.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.service.PersistableService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

@Service
public class OperationService extends PersistableService<Operation> implements IOperationService {

	public OperationService() {
		super();
		this.type = Operation.class;
	}

	@Override
	public Operation save(Operation entity) {

		IOperationTypeService optService = ServiceFactory.create(IOperationTypeService.class);
		OperationType opt = optService.byId(entity.getOperationTypeId());
		entity.setCode(opt.getCode());
		entity.setName(opt.getName());
		entity.setSeq(opt.getSeq());
		super.save(entity);
		return entity;
	}

	public void addOperation(ResourceNode node, OperationTypes type) {

		if (node == null) {
			throw new NetsharpException("资源节点不能为空！");
		}

		IOperationTypeService typeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType ot = typeService.byCode(type);
		Operation operation = new Operation();
		{
			operation.setResourceNode(node);
			operation.setOperationType(ot);
			operation.setCode(ot.getCode());
			operation.setName(ot.getName());
			operation.setSeq(ot.getSeq());
			operation.toNew();
		}

		super.save(operation);
	}

	public void addOperation(ResourceNode node, String code, String name) {

		Operation operation = new Operation();
		{
			operation.toNew();

			operation.setResourceNode(node);
			operation.setOperationType(null);
			operation.setCode(code);
			operation.setName(name);
			operation.setSeq(100);
		}

		super.save(operation);
	}

	public void addOperations(ResourceNode node) {

		for (OperationTypes type : OperationTypes.values()) {
			if (type == OperationTypes.operation || type == OperationTypes.audit || type == OperationTypes.unaudit || type == OperationTypes.exportExcel || type == OperationTypes.importExcel) {
				continue;
			}

			this.addOperation(node, type);
		}
	}

	public void addAllOperations(ResourceNode node) {

		for (OperationTypes type : OperationTypes.values()) {
			if (type == OperationTypes.operation || type == OperationTypes.exportExcel || type == OperationTypes.importExcel) {
				continue;
			}

			this.addOperation(node, type);
		}
	}

	public void addOperations(ResourceNode node, List<OperationTypes> ots) {

		for (OperationTypes type : ots) {

			this.addOperation(node, type);
		}
	}

	// #region 查询功能权限资源树

	// / <summary>
	// / 查询有功能权限的资源节点
	// / </summary>
	public List<ResourceNode> queryOperationResourceNodes(Boolean isClassification) {
		// ---------------------------------------
		// 查找所有有Operation的资源节点
		// ---------------------------------------
		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);

		Oql oql = new Oql();
		{
			oql.setType(ResourceNode.class);
			oql.setSelects("ResourceNode.{Id,Code,Name,parentId,ResourceType,EntityId,Disabled,PathCode},plugin.{Id,disabled}");
			oql.setFilter("ResourceNode.disabled=0");// AND ( plugin.id is not
														// null and
														// plugin.disabled=0)
		}

		// 数据库中所有未停用的ResourceNode
		List<ResourceNode> dbNodes = resourceService.queryList(oql);

		List<ResourceNode> treeNodes = CatEntity.listToTree(dbNodes);
		List<ResourceNode> listNodes = CatEntity.treeToList(treeNodes);

		// ---------------------------------------
		// 查找所有有Operation的资源节点
		// ---------------------------------------
		List<String> filters = new ArrayList<String>();

		filters.add("ResourceNode.Disabled=0");

		// 分级授权
		if (isClassification) {
			List<Long> principalIds = queryPricipals();
			filters.add("Operation.Id IN ( SELECT operation_id FROM " + MtableManager.getMtable(PrincipalOperation.class).getTableName() + " WHERE principal_id IN (" + StringManager.join(",", principalIds) + ") )");
		}

		oql = new Oql();
		{
			oql.setType(Operation.class);
			oql.setSelects("DISTINCT Operation.resourceNodeId");
			oql.setFilter(StringManager.join(" AND ", filters));
			oql.getParameters().add("employeeId", this.getUserId(), Types.BIGINT);
		}

		// 设置ResourceNode是否为携带操作的节点
		List<Operation> dataTable = this.pm.queryList(oql);

		// ---------------------------------------
		// 有Operation的ResourceNode，设置tag为true，且这个ResourceNode的parent的tag也设置为true
		// ---------------------------------------
		for (Operation row : dataTable) {
			// 根据id找到ResourceNode
			Object id = row.getResourceNodeId();
			ResourceNode node = null;

			for (ResourceNode x : listNodes) {
				if (x.getId().equals(id)) {
					node = x;
					break;
				}
			}

			// 设置当前ResourceNode以及所有上级的tag为true
			ResourceNode parent = node;
			while (parent != null) {
				parent.setTag(true);

				parent = (ResourceNode) parent.getParent();
			}
		}

		// ---------------------------------------
		// 删除tag不为true的ResourceNode
		// ---------------------------------------
		List<ResourceNode> removedNodes = new ArrayList<ResourceNode>();
		for (ResourceNode node : listNodes) {
			if (node.getTag() == null) {
				removedNodes.add(node);
			}
		}

		for (ResourceNode node : removedNodes) {
			listNodes.remove(node);
		}

		for (ResourceNode node : listNodes) {
			node.setItems(null);
		}

		return listNodes;
	}

	// #endregion 查询功能权限资源树

	// #region 功能权限设置时，查询可设置的功能权限

	// / <summary>
	// / 功能权限设置时，查询可设置的功能权限
	// / </summary>
	// / <param name="employeeId">授权主体的Id</param>
	// / <returns></returns>
	public List<Operation> querySettingOperations(Boolean isClassification) {
		List<String> filters = new ArrayList<String>();

		// 启用插件的资源节点才出来
		String filter = this.getOperationFilter();
		if (!StringManager.isNullOrEmpty(filter)) {
			filters.add(filter);
		}

		// 分级授权
		if (isClassification) {
			List<Long> principalIds = queryPricipals();
			filters.add("Operation.Id IN ( SELECT operation_id FROM " + MtableManager.getMtable(PrincipalOperation.class).getTableName() + " WHERE principal_id IN (" + StringManager.join(",", principalIds) + ") )");
		}

		Oql oql = new Oql();
		{
			oql.setType(Operation.class);
			oql.setSelects("Id,Code,Name,Seq,ResourceNode.Id,OperationType.{Id,Seq}");
			oql.setFilter(StringManager.join(" AND ", filters));

			oql.getParameters().add("@employeeId", this.getUserId(), Types.BIGINT);
		}

		List<Operation> operations = this.pm.queryList(oql);

		return operations;
	}

	private String getOperationFilter() {
		List<ResourceNode> resourceNodes = this.queryOperationResourceNodes(false);
		List<String> resourceIds = new ArrayList<String>();

		for (ResourceNode resourceNode : resourceNodes) {
			if (resourceNode.getResourceType() == ResourceType.VOUCHER && resourceNode.getParent() != null) {
				resourceIds.add(resourceNode.getId().toString());
			}
		}

		if (resourceIds.size() > 0) {
			String filter = "resourceNodeId IN (" + StringManager.join(",", resourceIds.toArray(new String[] {})) + ")";

			return filter;
		} else {
			return null;
		}
	}

	// #endregion 功能权限设置时，查询可设置的功能权限

	// #region 查询一个授权主体的所有功能权限

	public List<Operation> queryAuthorizedOperation() {
		List<Long> pricipals = this.queryPricipals();

		if (pricipals == null || pricipals.size() == 0) {
			return null;
		}

		List<Operation> List1 = this.queryAuthorizedOperation(StringManager.join(",", pricipals));
		// List<FieldGeteway> List2 = this.queryUnFields(pricipals);

		return List1;
	}

	private List<Operation> queryAuthorizedOperation(String pricipals) {
		List<String> filters = new ArrayList<String>();
		String filter = this.getOperationFilter();
		if (!StringManager.isNullOrEmpty(filter)) {
			filters.add(filter);
		}

		filters.add("PrincipalOperations.principalId IN (" + pricipals + ")");

		// 查询操作权限
		Oql oql = new Oql();
		{
			oql.setType(Operation.class);
			oql.setSelects("Id,resourceNodeId,operationTypeId");
			oql.setFilter(StringManager.join(" AND ", filters));
		}

		List<Operation> set = pm.queryList(oql);

		return set;
	}

	/**
	 * <p>
	 * 方法名称：queryPricipals
	 * </p>
	 * <p>
	 * 方法描述：查询当前登录人的授权主体，授权主体目前为两个，岗位和职务，分别对应组织和职务实体，需要查询这两个对应在授权主体实体中的映射
	 * </p>
	 * 
	 * @return
	 * @author gaomeng
	 * @since 2016年3月14日
	 *        <p>
	 *        history 2016年3月14日 gaomeng 创建
	 *        <p>
	 */
	private List<Long> queryPricipals() {
		Object userId = this.getUserId();
		// 查询授权主体,将组织和职务对应的授权主体id合并
		String cmdText = "SELECT id FROM sys_permission_authorization_principal ap " 
		+ " WHERE EXISTS (SELECT 1 FROM sys_permission_organization_employee oe WHERE "
		+ " ap.principal_id=oe.organization_id AND oe.employee_id=?) AND ap.principal_type="+PrincipalType.POST.getValue()
		+ " UNION SELECT id FROM sys_permission_authorization_principal ap " 
		+ " WHERE EXISTS (SELECT 1 FROM sys_Permission_Organization org WHERE ap.principal_id=org.position_id "
		+ " AND EXISTS (SELECT 1 FROM sys_Permission_organization_employee oe WHERE org.id=oe.organization_id AND employee_id=?)) AND principal_type="+PrincipalType.STATION.getValue();
		QueryParameters qps = new QueryParameters();
		qps.add("@oe.employeeId", userId, Types.BIGINT);
		qps.add("@oe.employeeId", userId, Types.BIGINT);
		IDataAccess dao = DataAccessFactory.create();
		DataTable table = dao.executeTable(cmdText, qps);
		List<Long> pricipalIds = new ArrayList<Long>();
		for (int i = 0, len = table.size(); i < len; i++) {
			pricipalIds.add(table.get(i).getLong("id").longValue());
		}
		return pricipalIds;

	}

	// #endregion 查询一个授权主体的所有功能权限

	private Long getUserId() {
		return SessionManager.getUserId();
	}
}
