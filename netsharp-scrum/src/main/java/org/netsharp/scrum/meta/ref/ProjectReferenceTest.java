package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Project;

public class ProjectReferenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = Project.class.getSimpleName();
		datagridName = referenceName = "项目";
		referenceCode = Project.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		
		canNew= true;
		popupUrl = "/scrum/project/form";
		width = 1000;
		height = 600;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter("status not in (8,9,10)");
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
