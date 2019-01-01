package org.netsharp.organization.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;

public interface IOrganizationService extends IPersistableService<Organization> {
	
	/**   
	 * @Title: getPosts   
	 * @Description: TODO(得到所有的岗位，好像是在员工注册时候使用 )   
	 * @param: @return      
	 * @return: Map<Integer,String>      
	 * @throws   
	 */
	Map<Integer, String> getPosts();

	/**   
	 * @Title: getByFunction   
	 * @Description: TODO(根据部门职能类型查询组织节点，比如查询所有的门店)   
	 * @param: @param organizationFunctionType
	 * @param: @return      
	 * @return: List<Organization>      
	 * @throws   
	 */
	List<Organization> getByFunction(String organizationFunctionType);

	/**   
	 * @Title: getDirectDepartmentByEmployeeId   
	 * @Description: TODO(getDirectDepartmentByEmployeeId)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: List<Organization>      
	 * @throws   
	 */
	List<Organization> getDirectDepartmentByEmployeeId(Integer employeeId);


	/**   
	 * @Title: getDirectLeaderByDepartmentId   
	 * @Description: TODO(按照部门id获取直接领导)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: Employee      
	 * @throws   
	 */
	Employee getDirectLeader(Integer departmentId);

	/**   
	 * @Title: getFitableEmployeeByPositonAndOrgNodeName   
	 * @Description: TODO(按照岗位字符串获取唯一雇员,用于工作流,position对应PositionType枚举)   
	 * @param: @param position
	 * @param: @param departmentName
	 * @param: @return      
	 * @return: Employee      
	 * @throws   
	 */
	Employee getFitableEmployeeByPositonAndOrgNodeName(String position, String departmentName);


	/**   
	 * @Title: getCorprationByDepartment   
	 * @Description: TODO(按照部门的pathCode获取所直属公司)   
	 * @param: @param pathCode
	 * @param: @return      
	 * @return: Organization      
	 * @throws   
	 */
	Organization getCorprationByDepartment(String pathCode);

	/**   
	 * @Title: getEmployeesByCurrentDepartment   
	 * @Description: TODO(找到当前部门的所有岗位的所有员工，不递归查询 )   
	 * @param: @param departmentId
	 * @param: @param disabled
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws   
	 */
	List<Employee> getEmployeesByCurrentDepartment(Integer departmentId, boolean disabled);

	/**   
	 * @Title: getMainDepartment   
	 * @Description: TODO(根据员工id获取员工所在部门，取主岗所在部门)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: Organization      
	 * @throws   
	 */
	Organization getMainDepartment(Integer employeeId);

	/**   
	 * @Title: getParentDepartment   
	 * @Description: TODO(根据部门id查询直接上级部门)   
	 * @param: @param department
	 * @param: @return      
	 * @return: Organization      
	 * @throws   
	 */
	Organization getParentDepartment(Organization department);

	/**   
	 * @Title: getChildDepartment   
	 * @Description: TODO(获取部门下的所有子部门Id,不包含岗位(递归))   
	 * @param: @param id
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getChildDepartment(Integer id);
	
	/**   
	 * @Title: getChildPost   
	 * @Description: TODO(获取部门下所有岗位Id)   
	 * @param: @param id
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getChildPostIds(Integer departmentId);
	
	
	/**   
	 * @Title: getEmployeeIds   
	 * @Description: TODO(获取部门下所有员工Id，不包含直属岗位)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getEmployeeIds(Integer departmentId);

	/**   
	 * @Title: getEmployeeIds   
	 * @Description: TODO(获取部门下所有员工Id)   
	 * @param: @param departmentId
	 * @param: @param isDirectlyPost 是否包含直属岗位
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> getEmployeeIds(Integer departmentId,Boolean isDirectlyPost);

	/**   
	 * @Title: changeParent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param nodeId
	 * @param: @param newParentId      
	 * @return: void      
	 * @throws   
	 */
	void changeParent(Integer nodeId, Integer newParentId);

	/**   
	 * @Title: disabled   
	 * @Description: TODO(停用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean disabled(Integer id);

	/**   
	 * @Title: hasChild   
	 * @Description: TODO(判断是否存在下级)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean hasChild(Integer id);
}
