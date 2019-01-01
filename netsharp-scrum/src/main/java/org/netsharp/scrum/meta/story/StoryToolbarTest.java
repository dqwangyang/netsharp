package org.netsharp.scrum.meta.story;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Story;

public class StoryToolbarTest {
	
	IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	@Test
	public void form_voucher() {

		ResourceNode node = this.resourceNodeService.byCode(Story.class.getSimpleName());

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath("story/list/toolbar");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("traces");
			item.setName("跟进列表");
			item.setCommand("{controller}.traces();");
			item.setOperationTypeId(-1);
			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addtrace");
			item.setName("添加跟进");
			item.setCommand("{controller}.addtrace();");
			item.setOperationTypeId(-1);
			toolbar.getItems().add(item);
		}

		service.save(toolbar);
	}
}
