package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;

public interface IOrganizationEmployeeService extends IPersistableService<OrganizationEmployee> {

	/**   
	 * @Title: getEmpByPostOrgId   
	 * @Description: TODO(按照岗位组织的id获取雇员)   
	 * @param: @param orgid
	 * @param: @return      
	 * @return: Employee      
	 * @throws   
	 */
	Employee getEmpByPostOrgId(Long orgid);
	
	/**   
	 * @Title: deleteByOrganizationId   
	 * @Description: TODO(根据岗位Id删除)   
	 * @param: @param orgId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean deleteByOrganizationId(Long orgId);
	
	/**   
	 * @Title: deleteByEmploeeyId   
	 * @Description: TODO(根据员工Id删除)   
	 * @param: @param orgId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean deleteByEmploeeyId(Long orgId);
}
