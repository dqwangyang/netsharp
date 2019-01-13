package org.netsharp.panda.session;

import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableRelation;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.core.PandaConfiguration;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

//这个类主要是平台界面渲染时判断界面权限使用
//只读取权限，不包括权限的写功能
//不要把Session管理的功能加到这个类中
public class PermissionHelper {

	public static boolean isPermission(ResourceNode node, Long operationId, OperationType ot1, OperationType ot2) {

		if (!PandaConfiguration.getInstance().getIsPermission()) {
			return true;
		}

		if (operationId != null) {
			return PermissionHelper.isPermission(operationId);
		}
		
		if (node == null){
			return true;
		}
		else if (ot1 != null && ot2 != null) {
			return PermissionHelper.isPermission(node, ot1, ot2);
		} else if (ot1 != null) {
			return PermissionHelper.isPermission(node, ot1);
		} else {
			return true;
		}
	}

	public static boolean isPermission(ResourceNode node, OperationType ot) {

		PandaSession ps = PandaSessionPersister.get();
		UserPermission up = ps.getPermission();
		for (Operation operation : up.getOperations()) {
			if(operation.getResourceNodeId()==null){
				continue;
			}
			
			if (operation.getOperationTypeId().equals(ot.getId()) && operation.getResourceNodeId().equals(node.getId())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPermission(ResourceNode node, OperationType ot1, OperationType ot2) {

		boolean t1 = PermissionHelper.isPermission(node, ot1);
		boolean t2 = PermissionHelper.isPermission(node, ot2);

		return t1 | t2;
	}

	public static boolean isPermission(Object operationId) {

		UserPermission up = PandaSessionPersister.get().getPermission();
		for (Operation operation : up.getOperations()) {
			if (operation.getId().equals(operationId)) {
				return true;
			}
		}
		return false;
	}

	// 是否有某个字段权限
	// pathName可能为多级属性，如entityId:org.netsharp.test.SalesOrder,propertyName:customer.phoneNumber,实际上控制的是客户的手机号权限
	public static boolean isFieldGetway(String entityId, String propertyName) {

		if (!PandaConfiguration.getInstance().getIsPermission()) {
			return true;
		}
		
		if(StringManager.isNullOrEmpty(entityId)){
			return true;
		}

		UserPermission up = PandaSessionPersister.get().getPermission();
		
		Mtable meta = MtableManager.getMtable(entityId);
		Column column = meta.findProperty(propertyName);
		if (column == null) {
			return false;
		}

		if (propertyName.contains(".")) {
			int index = propertyName.lastIndexOf(".");
			String path = propertyName.substring(index);
			TableRelation relation = meta.findRelation(path);
			if (relation == null) {
				return true;
			}
			entityId = relation.getToEntityId();
		}

		List<String> fields = up.getFieldGeteways().get(entityId);
		if (fields == null) {
			return true;
		}

		for (String field : fields) {
			if (StringManager.equals(field, column.getPropertyName())) {
				return true;
			}
		}

		return false;
	}
}
