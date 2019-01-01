package org.netsharp.meta.basebiz.ref.organization;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * 部门参照
 * 
 * @author wangyang
 *
 */
public class OrganizationReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  Organization.class.getSimpleName();
		datagridName = referenceName = "部门参照";
		referenceCode = "Organization-Department";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "pathName";
		gridFilter = "organizationType = "+OrganizationType.DEPARTMENT.getValue();
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"pathName", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
