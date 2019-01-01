package org.netsharp.meta.platform.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PPartType;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PartTypeReferenceTest extends ReferenceCreationBase{
	

	@Before
	public void setup() {
		
		resourceNodeCode =  PPartType.class.getSimpleName();
		datagridName = referenceName = "部件类型参照";
		referenceCode = PPartType.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
