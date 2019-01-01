package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Product;

public class ProductReferenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = Product.class.getSimpleName();
		datagridName = referenceName = "产品";
		referenceCode = Product.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}

}
