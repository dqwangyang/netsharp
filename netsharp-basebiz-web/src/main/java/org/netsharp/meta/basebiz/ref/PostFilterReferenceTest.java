package org.netsharp.meta.basebiz.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.filter.OrganizationReferenceFilter;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PostFilterReferenceTest extends ReferenceCreationBase{
	

	@Before
	public void setup() {
		
		resourceNodeCode =  Organization.class.getSimpleName();
		datagridName = referenceName = "岗位参照【自定义条件】";
		referenceCode = "Organization-Post-Filter";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "pathName";
		filterBuilder = OrganizationReferenceFilter.class.getName();
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"pathName", "名称", ControlTypes.TEXT_BOX,250,null,false);
		addColumn( datagrid,"resourceNode.code", "资源编码", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
