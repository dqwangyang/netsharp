package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Iteration;

public class IterationReferenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = Iteration.class.getSimpleName();
		datagridName = referenceName = "迭代";
		referenceCode = Iteration.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		addColumn(datagrid, "isCurrent", "当前迭代", ControlTypes.BOOLCOMBO_BOX, 60, null, false);
		addColumn(datagrid, "startTime", "起始日期", ControlTypes.DATE_BOX, 80, OrderbyMode.DESC, false);
		addColumn(datagrid, "endTime", "结束日期", ControlTypes.DATE_BOX, 80, null, false);
		return datagrid;
	}
}
