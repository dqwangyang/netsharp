package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * 岗位name属性参照
 * 
 * @author wangyang 
 *
 */
public class PostNameFilterReferenceTest extends ReferenceCreationBase{


	@Before
	public void setup() {
		
		resourceNodeCode =  Organization.class.getSimpleName();
		datagridName = referenceName = "岗位参照";
		referenceCode = "Organization-Post-Name-Filter";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name"; 
		gridFilter = "organizationType="+OrganizationType.POST.getValue();
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
