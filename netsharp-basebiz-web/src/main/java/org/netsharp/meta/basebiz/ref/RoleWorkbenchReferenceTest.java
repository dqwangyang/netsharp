package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleWorkbenchReferenceTest  extends ReferenceCreationBase{
	
	@Before
	public void setup() {

		resourceNodeCode = RoleWorkbench.class.getSimpleName();
		datagridName = referenceName = "工作台参照";
		referenceCode = RoleWorkbench.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "path,name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, null, false);
		return datagrid;
	}
}
