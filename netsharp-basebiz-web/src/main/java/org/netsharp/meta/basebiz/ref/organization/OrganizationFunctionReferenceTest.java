package org.netsharp.meta.basebiz.ref.organization;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrganizationFunctionReferenceTest extends ReferenceCreationBase{
	

	@Before
	public void setup() {
		
		resourceNodeCode =  OrganizationFunction.class.getSimpleName();
		datagridName = referenceName = "业务类型参照";
		referenceCode = OrganizationFunction.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
