package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Deploy;

public class DeployReferneceTest extends ReferenceCreationBase{
	
	
	@Before
	public void setup() {
		
		resourceNodeCode =  Deploy.class.getSimpleName();
		datagridName = referenceName = "部署计划";
		referenceCode = Deploy.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"deployTime", "起始日期", ControlTypes.DATE_BOX,80,null,false);
		return datagrid;
	}
	
}
