package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleGroupReferenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = RoleGroup.class.getSimpleName();
		datagridName = referenceName = "角色分组参照";
		referenceCode = RoleGroup.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "code,name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, null, false);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, null, false);
		return datagrid;
	}
}
