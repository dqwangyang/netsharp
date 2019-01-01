package org.netsharp.meta.platform.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ReferenceReferenceTest extends ReferenceCreationBase{


	@Before
	public void setup() {
		
		resourceNodeCode =  PReference.class.getSimpleName();
		datagridName = referenceName = "参照的参照";
		referenceCode = PReference.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"code", "编码", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"resourceNode.code", "资源编码", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
