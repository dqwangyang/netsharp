package org.netsharp.meta.framework;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.core.workbench.Workbench;
import org.netsharp.panda.dic.WorkspaceType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class WorkbenchTest {

	IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		List<Mtable> clss = new ArrayList<Mtable>();

		clss.add(MtableManager.getMtable(PWorkspace.class));
		clss.add(MtableManager.getMtable(PPart.class));
		clss.add(MtableManager.getMtable(PDatagrid.class));
		clss.add(MtableManager.getMtable(PRowStyler.class));
		clss.add(MtableManager.getMtable(PDatagridColumn.class));
		clss.add(MtableManager.getMtable(PForm.class));
		clss.add(MtableManager.getMtable(PFormField.class));
		clss.add(MtableManager.getMtable(PQueryProject.class));
		clss.add(MtableManager.getMtable(PQueryItem.class));

		IDb db = DbFactory.create();
		for (Mtable meta : clss) {
			db.reCreateTable(meta);
		}
	}

	@Test
	public void create() {

		// panda
		IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = service.byCode("workbench");

		this.createWorkspace(node);
//		this.userPath(node);
//		this.mainManu(node);
		
	}
	
	public void createWorkspace(ResourceNode node){
		
		PWorkspace workspace = new PWorkspace();
		{
			workspace.setResourceNode(node);
			workspace.setUrl("/workbench");
			workspace.setServiceController(Workbench.class.getName());
			workspace.setJsController("org.netsharp.panda.Workbench");
			workspace.setName("工作台");
			workspace.setType(WorkspaceType.WORKBENCH);
			workspace.toNew();
		}

		IPWorkspaceService workspaceServcie = ServiceFactory.create(IPWorkspaceService.class);
		workspaceServcie.save(workspace);
	}

	public void userPath(ResourceNode node) {
		
		String path = "panda/workbench/user";

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(path);
			toolbar.setName("用户信息");
			toolbar.setResourceNode(node);
		}

//		PToolbarItem item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("workbenchuser");
//			item.setName("详情");
//			item.setCommand(null);
//			item.setOperationTypeId(-1);
//
//			toolbar.getItems().add(item);
//		}
//		
		PToolbarItem	item = new PToolbarItem();
		{
			
			item.toNew();
			item.setCode("changeMy");
			item.setIcon("icon-user");
			item.setName("个人信息");
			item.setCommand(null);
			item.setOperationTypeId(-1);

			toolbar.getItems().add(item);
		}
		
    	item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("changeMyPassword");
			item.setIcon("icon-lock_edit");
			item.setName("修改密码");
			item.setCommand(null);
			item.setOperationTypeId(-1);

			toolbar.getItems().add(item);
		}

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			toolbarItem.setSeq((i + 1) * 10);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode()
					+ "();");
		}

		service.save(toolbar);
	}
	
	public void mainManu(ResourceNode node) {
		
		String path = "panda/workbench/menu";

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(path);
			toolbar.setName("工作台菜单");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("fullscreen");
			item.setName("全屏");
			item.setCommand(null);
			item.setOperationTypeId(-1);

			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("exit");
			item.setName("退出");
			item.setCommand(null);
			item.setOperationTypeId(-1);

			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("help");
			item.setName("帮助");
			item.setCommand(null);
			item.setOperationTypeId(-1);

			toolbar.getItems().add(item);
		}

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			toolbarItem.setSeq((i + 1) * 10);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode()
					+ "();");
		}

		service.save(toolbar);
	}

}
