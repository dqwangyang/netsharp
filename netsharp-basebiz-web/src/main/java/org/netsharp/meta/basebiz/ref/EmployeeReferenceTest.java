package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  Employee.class.getSimpleName();
		datagridName = referenceName = "人员参照";
		referenceCode = Employee.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name,shorthand";
		gridFilter = "disabled != 1";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"shorthand", "助记码", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
