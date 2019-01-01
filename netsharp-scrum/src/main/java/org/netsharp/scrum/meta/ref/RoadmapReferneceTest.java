package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Roadmap;

public class RoadmapReferneceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  Roadmap.class.getSimpleName();
		datagridName = referenceName = "计划路线图";
		referenceCode = Roadmap.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}

}
