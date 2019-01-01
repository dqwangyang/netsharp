package org.netsharp.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.dataccess.InsqlGeneratorFactory;
import org.netsharp.dic.DatabaseType;
import org.netsharp.organization.base.IOperationAuthorizationLogService;
import org.netsharp.organization.base.IPrincipalOperationService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.dic.UiOperationType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

@Service
public class PrincipalOperationService extends PersistableService<PrincipalOperation> implements IPrincipalOperationService {

	public PrincipalOperationService() {
		super();
		this.type = PrincipalOperation.class;
	}

	/**   
	 * <p>Title: backup</p>   
	 * <p>Description: </p>      
	 * @see org.netsharp.organization.base.IPrincipalOperationService#backup()   
	 */
	public void backup() {
		String cmdText = "UPDATE sys_permission_principal_operation po SET po.`resourcenode_code`=NULL,po.`operation_code`=NULL";
		pm.executeNonQuery(cmdText, null);

		cmdText = "UPDATE sys_permission_principal_operation po" + StringManager.NewLine
				+ "LEFT JOIN sys_permission_operation o ON o.id=po.operation_id" + StringManager.NewLine
				+ "LEFT JOIN rs_resource_node rn ON o.resource_node_id = rn.id" + StringManager.NewLine
				+ "SET po.`resourcenode_code`=rn.`code`,po.`operation_code`=o.`code`";

		pm.executeNonQuery(cmdText, null);
	}

	/**   
	 * <p>Title: restore</p>   
	 * <p>Description:同步授权表的操作 </p>      
	 * @see org.netsharp.organization.base.IPrincipalOperationService#restore()   
	 */
	public void restore() {

		// 根据sys_permission_principal_operation
		// 的操作code和资源code更新操作id(operation_id)
		String cmdText = "UPDATE sys_permission_principal_operation po LEFT JOIN (SELECT op.id,op.code AS opcode,rs.code AS rscode "
				+ "FROM sys_permission_operation op LEFT JOIN rs_resource_node rs ON op.resource_node_id=rs.id) opera "
				+ "ON po.operation_code=opera.opcode AND po.resourcenode_code=opera.rscode SET po.operation_id=opera.id";

		this.pm.executeNonQuery(cmdText, null);

		// 删除资源为空的操作
		cmdText = "DELETE FROM sys_permission_principal_operation WHERE resourcenode_code IS NULL";
		this.pm.executeNonQuery(cmdText, null);
	}
	
	/**   
	 * <p>Title: updatePrincipalOperation</p>   
	 * <p>Description: </p>   
	 * @param principalId 授权主体
	 * @param principalType 授权类型
	 * @param operationIds 授权的操作
	 * @param operationType   
	 * @see org.netsharp.organization.base.IPrincipalOperationService#updatePrincipalOperation(java.lang.Object,  org.netsharp.organization.dic.PrincipalType, java.lang.String, org.netsharp.organization.dic.UiOperationType)   
	 */
	public void updatePrincipalOperation(Object principalId, PrincipalType principalType, String operationIds, UiOperationType operationType) {
		if (StringManager.isNullOrEmpty(operationIds)) {
			return;
		}

		// ----------------------------------------------
		// 根据岗位Id找到对应的PrincipalOperation的Id
		// ----------------------------------------------
		Oql oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setFilter("principalType=" + principalType.getValue() + " AND principalId = " + principalId);
		}

		IPersister<AuthorizationPrincipal> appm = PersisterFactory.create();
		AuthorizationPrincipal ap = appm.queryFirst(oql);
		if(ap == null){
			
			throw new BusinessException("授权主体不存在");
		}
		principalId = ap.getId();

		// ----------------------------------------------
		// 之前的授权删除
		// ----------------------------------------------
		String[] ids = operationIds.split("_");
		DatabaseType dbType = DatabaseProperty.defaultDatabaseProperty().getType();
		IInsqlGenerator generator = InsqlGeneratorFactory.create(dbType);
		String filter = "principal_id='" + principalId + "' AND operation_id IN (" + generator.inSql(ids, null) + ")";
		String cmdText = "delete from " + MtableManager.getMtable(PrincipalOperation.class).getTableName() + " where " + filter;
		this.pm.executeNonQuery(cmdText, null);

		try {
			IOperationAuthorizationLogService oahService = ServiceFactory.create(IOperationAuthorizationLogService.class);
			oahService.createOperationHistory(Integer.valueOf(principalId.toString()), principalType, operationIds, operationType);
		} catch (Exception e) {
		}

		if (operationType != UiOperationType.ADD) {
			// 删除
			return;
		}

		// ----------------------------------------------
		// 批量插入
		// ----------------------------------------------
		List<PrincipalOperation> pos = new ArrayList<PrincipalOperation>();
		// IOperationService operationService =
		// ServiceFactory.create(IOperationService.class);

		// 批量查询Operation
		oql = new Oql();
		{
			oql.setType(Operation.class);
			oql.setSelects("Operation.*");
			oql.setFilter("id in (" + generator.inSql(ids, null) + ")");
		}

		IPersister<Operation> opm = PersisterFactory.create();
		List<Operation> ops = opm.queryList(oql);
		Map<String, Operation> map = new HashMap<String, Operation>();
		for (Operation o : ops) {
			map.put(o.getId().toString(), o);
		}

		for (String id : ids) {
			
			Operation operation = map.get(id);
			// 新增
			PrincipalOperation po = new PrincipalOperation();
			{
				po.toNew();
				po.setPrincipalId((Integer) principalId);
				po.setOperationId(Integer.valueOf(id));
			}
			if (operation != null) {
				po.setOperationCode(operation.getCode());
				if (operation.getResourceNode() != null) {
					po.setResourcenodeCode(operation.getResourceNode().getCode());
				}
			}

			// super.save(po);
			pos.add(po);
		}
		this.pm.bulk(pos, true);
	}
}
