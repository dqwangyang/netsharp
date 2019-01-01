package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.dic.UiOperationType;
import org.netsharp.organization.entity.PrincipalOperation;

public interface IPrincipalOperationService extends IPersistableService<PrincipalOperation> {

	/**   
	 * @Title: backup   
	 * @Description: 权限备份
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	void backup();
	
	/**   
	 * @Title: restore   
	 * @Description:同步授权表的操作
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	void restore();
	
	/**   
	 * @Title: 更新某个操作的权限   
	 * @Description:修改，增加授权主体类型，区分职务 
	 * @param principalId 授权主体
	 * @param principalType 授权类型
	 * @param operationIds 授权的操作
	 * @param operationType      
	 * @return: void      
	 * @throws   
	 */
	void updatePrincipalOperation(Object principalId, PrincipalType principalType, String operationIds, UiOperationType operationType);
}
