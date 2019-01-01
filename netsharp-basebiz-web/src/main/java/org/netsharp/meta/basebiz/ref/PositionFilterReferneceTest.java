package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.Position;
import org.netsharp.organization.filter.PositionReferenceFilter;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PositionFilterReferneceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  Position.class.getSimpleName();
		datagridName = referenceName = "职务参照【自定义条件】";
		referenceCode = Position.class.getSimpleName()+"Filter";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		filterBuilder = PositionReferenceFilter.class.getName();
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
