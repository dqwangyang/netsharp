package org.netsharp.organization.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;

public interface IEmployeeService extends IPersistableService<Employee> {
	// 登录
	
	Employee login(String loginName, String pwd);

	Employee loginByPhone(String phoneNo, String pwd);

	Employee byPhone(String phoneNo);

	// 注册申请时判断是否存在手机号
	int isMobilePhone(String mobilePhone);

	// 注册申请时判断是否存在邮箱号
	int isEamil(String email);

	// 注册申请时判断是否存在登录名
	int isLoginName(String loginName);

	/**
	 * @Definition: 根据组织机构ID，查询此ID下所有员工（包括此ID的组织机构）
	 * @author: TangWenWu
	 * @Created date: 2015-4-27
	 * @param organizationId
	 *            组织机构ID
	 * @return
	 */
	List<Employee> findByOrganizationId(Integer organizationId);

	/**
	 * @Definition: 根据组织机构ID和职责类型，查询此ID下所有员工（包括此ID下所有的组织机构）
	 * @author: TangWenWu
	 * @Created date: 2015-4-27
	 * @param organizationId
	 *            组织机构ID
	 * @param position
	 *            职责类型ID 6店长 7机修工 8美容工 9司机
	 * @return
	 */
	public List<Employee> findByOrgIdAndPosition(Integer organizationId, Integer position);

	boolean resetPassword(Integer id);

	/**
	 * @Title: byLoginNameAndMobile
	 * @Description: 根据登录名和手机号码查询员工（用于找回密码）
	 * @param @param loginName
	 * @param @param mobile
	 * @param @return 设定文件
	 * @return Employee 返回类型
	 * @throws
	 */
	Employee byLoginNameAndMobile(String loginName, String mobile);

	/**
	 * 获取微信企业号中的员工头像
	 * 
	 * @param employeeId
	 * @param isBigImage
	 *            是否大图
	 * 
	 * @return 头像路径
	 */
//	String getWXEmployeeImage(Integer employeeId, boolean isBigImage);

	/**
	 * 修改密码
	 * 
	 * @param employee
	 * @return
	 */
	Employee changPassword(Employee employee);
}
