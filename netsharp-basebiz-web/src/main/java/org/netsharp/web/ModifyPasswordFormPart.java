package org.netsharp.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.session.SessionManager;

public class ModifyPasswordFormPart extends FormPart{

	public boolean save(String originalPassword,String newPassword,String confirmPassword){

		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);

		Employee employee = service.byId(SessionManager.getUserId());
		
		if(!employee.getPwd().equals(originalPassword)){
			
			throw new BusinessException("原始密码输入错误！");
		}
		
		if(!newPassword.equals(confirmPassword)){
			
			throw new BusinessException("两次输入不一致！");
		}
		
		if(originalPassword.equals(confirmPassword)){
			
			throw new BusinessException("不能和原始密码一样");
		}
		
		employee.setPwd(confirmPassword);
		
		employee = service.changPassword(employee);
		
		//修改密码后，需要重新登录
		PandaSessionPersister.remove();
		
		return true;
	}
}
