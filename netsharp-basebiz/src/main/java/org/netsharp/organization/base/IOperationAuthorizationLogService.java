package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.dic.UiOperationType;
import org.netsharp.organization.entity.OperationAuthorizationLog;

public interface IOperationAuthorizationLogService extends IPersistableService<OperationAuthorizationLog> {

	/**
	 * <p>方法名称：createOperationHistory</p>
	 * <p>方法描述：根据授权主题、操作、授权类型记录授权日志</p>
	 * @param principalId 授权主题id
	 * @param principalType 授权主体类型
	 * @param operationIds操作id 以'_'分割
	 * @param operationType 增加权限或删除操作
	 * @author gaomeng
	 * @param principalType 
	 * @since  2016年2月2日
	 * <p> history 2016年2月2日 gaomeng  创建   <p>
	 */
	void createOperationHistory(Integer principalId, PrincipalType principalType, String operationIds, UiOperationType operationType);
	
}
