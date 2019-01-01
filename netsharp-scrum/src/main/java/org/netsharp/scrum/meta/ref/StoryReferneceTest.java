package org.netsharp.scrum.meta.ref;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Story;

public class StoryReferneceTest {

	Class<?> type = Story.class;

	@Test
	public void run() {

		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = resourceService.byCode(type.getSimpleName());

		PDatagrid pdatagrid = this.createDatagrid(node);

		PReference pr = new PReference();
		{
			pr.toNew();

			pr.setCode(type.getSimpleName());
			pr.setResourceNode(node);
			pr.setName(MtableManager.getMtable(type).getName());
			pr.setDataGrid(pdatagrid);
			pr.setIntelligentMode(IntelligentMode.LIKE);
			pr.setIntelligentFields("name");
		}
		IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);

		referenceService.save(pr);
	}

	private PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = new PDatagrid();
		{
			datagrid.toNew();
			datagrid.setPagination(true);
			datagrid.setResourceNode(node);
			datagrid.setName(MtableManager.getMtable(type).getName() + "参照");
		}

		PDatagridColumn column = null;

		column = new PDatagridColumn();
		{
			column.toNew();
			column.setPropertyName("name");
			column.setHeader("名称");
			column.setControlType(ControlTypes.TEXT_BOX);
			column.setFrozen(true);
			column.setWidth(120);
		}
		datagrid.getColumns().add(column);

		return datagrid;
	}
}
