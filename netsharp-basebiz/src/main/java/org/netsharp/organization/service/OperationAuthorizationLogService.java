package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.dataccess.InsqlGeneratorFactory;
import org.netsharp.dic.DatabaseType;
import org.netsharp.organization.base.IOperationAuthorizationLogService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.dic.UiOperationType;
import org.netsharp.organization.entity.OperationAuthorizationLog;
import org.netsharp.service.PersistableService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

@Service
public class OperationAuthorizationLogService extends PersistableService<OperationAuthorizationLog> implements IOperationAuthorizationLogService {

	public OperationAuthorizationLogService() {
		super();
		this.type = OperationAuthorizationLog.class;
	}

	@Override
	public void createOperationHistory(Integer principalId, PrincipalType principalType, String operationIds, UiOperationType operationType) {

		String cmdText = null;
		if (principalType == PrincipalType.POST) {
			cmdText = "SELECT path_name FROM sys_permission_organization org WHERE EXISTS(SELECT 1 FROM sys_permission_authorization_principal ap "
					+ "WHERE ap.principal_id=org.id AND ap.principal_type="+PrincipalType.POST.getValue()+" AND ap.id=" + principalId + ")";
		} else if (principalType == PrincipalType.STATION) {
			cmdText = "SELECT CONCAT('职务:',pos.name) AS path_name FROM sys_permission_position pos WHERE EXISTS(SELECT 1 FROM sys_permission_authorization_principal ap "
					+ "WHERE ap.principal_id=pos.id AND ap.principal_type="+PrincipalType.STATION.getValue()+" AND ap.id=" + principalId + ")";
		} else {
			return;
		}
		DataTable position = this.pm.executeTable(cmdText, null);

		String[] ids = operationIds.split("_");
		DatabaseType dbType = DatabaseProperty.defaultDatabaseProperty().getType();
		IInsqlGenerator generator = InsqlGeneratorFactory.create(dbType);
		cmdText = "SELECT CONCAT(res.path_name,':',GROUP_CONCAT(ope.name SEPARATOR ',')) AS operationText FROM sys_permission_operation ope "
				+ "LEFT JOIN rs_resource_node res ON ope.resource_node_id=res.id WHERE ope.id IN(" + generator.inSql(ids, null) + ") GROUP BY res.path_name";

		DataTable operationText = this.pm.executeTable(cmdText, null);

		OperationAuthorizationLog oah = new OperationAuthorizationLog();
		{
			oah.toNew();
			oah.setPositionId(principalId);
			oah.setPositionName(position.get(0).getString("path_name"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0, len = operationText.size(); i < len; i++) {
				sb.append(operationText.get(i).getString("operationText")).append(StringManager.NewLine);
			}
			sb.deleteCharAt(sb.length() - 1);
			oah.setOperationAuthorizationText(sb.toString());
			oah.setOperationType(operationType);
			oah.setCreatorId(SessionManager.getUserId());
			oah.setCreator(SessionManager.getUserName());
		}
		super.save(oah);
	}

}
