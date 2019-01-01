package org.netsharp.panda.core.workbench;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.controls.other.A;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Image;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Ul;
import org.netsharp.session.SessionManager;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class RightNavigation extends Div {

	@Override
	public void initialize() {
		
		this.setClassName("nav-right");
		this.getControls().add(this.getUserInfo());
		
		this.getControls().add(this.getOption());
		super.initialize();
	}

	private Div getUserInfo() {

		Div userInfo = new Div();
		{
			userInfo.setClassName("user-info");
			
			Span span = new Span();
			{
				Image img = new Image();
				{
					String photo = "";//up.getEmployee().getPhoto();
					if (!StringManager.isNullOrEmpty(photo)) {
						img.src = photo;
					} else {
						img.src = "/panda-res/images/head_default.png";
					}
					span.getControls().add(img);
				}
				userInfo.getControls().add(span);
			}


			span = new Span();
			{
				span.value = SessionManager.getUserName();
			}
			userInfo.getControls().add(span);
		}

		return userInfo;
	}

	private Div getOption() {

		Div option = new Div();
		{
			option.setClassName("icon-option");
			// option.innerValues.put("onclick", "workbench.exit();");
			I i = new I();
			i.setClassName("fa fa-chevron-down");
			option.getControls().add(i);

			Div dropBox = new Div();
			{
				dropBox.setClassName("drop-box");

				Div arrow = new Div();
				arrow.setClassName("arrow");
				dropBox.getControls().add(arrow);
			}

			Ul dropItem = new Ul();
			dropItem.setClassName("drop-item");

			List<WorkbenchDTO> workbenchList = this.getWorkbenchDTOList(SessionManager.getUserId());
			if (workbenchList.size() > 1) {

				LoginResultDTO result = new LoginResultDTO();
				result.setWorkbenchList(workbenchList);
				String json = JsonManage.serialize2(result).replaceAll("\"", "'");
				Li li0 = new Li();
				{
					A a1 = new A();
					a1.value = "切换视图";
					a1.href = "javascript:workbench.switchWorkbench(" + json + ");";
					li0.getControls().add(a1);
					dropItem.getControls().add(li0);
				}
			}
			Li li1 = new Li();
			{

				A a1 = new A();
				a1.value = "意见反馈";
				a1.href = "javascript:workbench.feedback();";
				li1.getControls().add(a1);
				dropItem.getControls().add(li1);
			}
			Li li2 = new Li();
			{

				A a1 = new A();
				a1.value = "修改密码";
				a1.href = "javascript:workbench.changePassword();";
				li2.getControls().add(a1);
				dropItem.getControls().add(li2);
			}

			Li li3 = new Li();
			{

				A a2 = new A();
				a2.value = "注销登录";
				a2.href = "javascript:workbench.exit();";
				li3.getControls().add(a2);
				dropItem.getControls().add(li3);
			}
			dropBox.getControls().add(dropItem);
			option.getControls().add(dropBox);
		}
		return option;
	}
	
	/**
	 * @Title: getWorkbenchPath
	 * @Description: TODO(获取用户工作台路径,取第一个角色上的)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public List<WorkbenchDTO> getWorkbenchDTOList(Integer employeeId) {

		IRoleEmployeeService srvice = ServiceFactory.create(IRoleEmployeeService.class);
		Oql oql = new Oql();
		{
			oql.setType(RoleEmployee.class);
			oql.setSelects("RoleEmployee.*,RoleEmployee.role.*,RoleEmployee.role.workbench.{id,name,path}");
			oql.setFilter(" employeeId=?");
			oql.getParameters().add("employeeId", employeeId, Types.INTEGER);
		}
		List<RoleEmployee> reList = srvice.queryList(oql);

		List<WorkbenchDTO> workbenchDTOList = new ArrayList<WorkbenchDTO>();
		Map<String, String> maps = new HashMap<String, String>();
		for (RoleEmployee re : reList) {

			if (re.getRole() != null && re.getRole().getWorkbench() != null) {

				RoleWorkbench rw = re.getRole().getWorkbench();
				String name = maps.get(rw.getPath());

				if (StringManager.isNullOrEmpty(name)) {

					maps.put(rw.getPath(), rw.getName());
				}
			}
		}

		if (maps.size() == 0) {

			WorkbenchDTO dto = new WorkbenchDTO();
			dto.setPath("/panda/workbench");
			workbenchDTOList.add(dto);
		} else {

			for (String key : maps.keySet()) {

				WorkbenchDTO dto = new WorkbenchDTO();
				dto.setPath(key);
				dto.setName(maps.get(key));
				workbenchDTOList.add(dto);
			}
		}
		return workbenchDTOList;
	}
}
