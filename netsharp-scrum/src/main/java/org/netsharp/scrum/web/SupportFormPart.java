/**  
 * @Title: SupportFormPart.java 
 * @Package org.netsharp.devprj.web
 * @Description: TODO
 * @author hanwei
 * @date 2015-6-5 下午2:05:52 
 * @version V1.0  
 */

package org.netsharp.scrum.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Row;
import org.netsharp.entity.IPersistable;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.scrum.base.ISupportService;
import org.netsharp.scrum.entity.Support;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

/*支持表单controller*/
public class SupportFormPart extends FormPart {

	ISupportService supportService = ServiceFactory.create(ISupportService.class);
	
	@Override
	public IPersistable newInstance(Object par) {

		Support entity = supportService.newInstance();
		entity.setCode(this.getCode(MtableManager.getMtable(Support.class.getName()).getTableName()));

		Employee employee = new Employee();
		{
			employee.setName(SessionManager.getUserName());
			employee.setId(SessionManager.getUserId());
		}

		entity.setPutor(employee);
		entity.setPutorId(employee.getId());
		
		return entity;
	}
	
	public Support save(Support entity) {

		entity = supportService.save(entity);
		entity = supportService.byId(entity);
		return entity;
	}

	private String getCode(String tableName) {
		String sqlString = "select max(id) as c from " + tableName;
		DataTable table = supportService.executeTable(sqlString, null);
		Object code = null;
		for (Row row : table) {
			code = row.get("c");
		}
		if (code == null) {
			code = 0;
		}
		Long id = Long.valueOf(code.toString());
		code = id + 1;
		String voucherCode = StringManager.padLeft(code.toString(), 6, '0');

		sqlString = " select id from " + tableName + " where code ='" + voucherCode + "'";
		table = supportService.executeTable(sqlString, null);
		if (table.size() != 0) {
			Integer c = Integer.parseInt(voucherCode) + 1;
			voucherCode = StringManager.padLeft(c.toString(), 6, '0');
		}

		return voucherCode;
	}

}
