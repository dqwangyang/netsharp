package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PostReferenceTest extends ReferenceCreationBase{
	

	@Before
	public void setup() {
		
		resourceNodeCode =  Organization.class.getSimpleName();
		datagridName = referenceName = "岗位参照";
		referenceCode ="Organization-Post";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name,pathName";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter("organization_type="+OrganizationType.POST.getValue());
		addColumn( datagrid,"pathName", "名称", ControlTypes.TEXT_BOX,250,null,false);
		return datagrid;
	}
}
