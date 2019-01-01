package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.Role;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleReferenceTest  extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = Role.class.getSimpleName();
		datagridName = referenceName = "角色参照";
		referenceCode = Role.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "code,name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "roleGroup.name", "分组", ControlTypes.TEXT_BOX, 100, null, false);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, null, false);
		return datagrid;
	}
}
