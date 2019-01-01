package org.netsharp.meta.platform.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceNodeReferenceTest extends ReferenceCreationBase{


	@Before
	public void setup() {
		
		resourceNodeCode =  ResourceNode.class.getSimpleName();
		datagridName = referenceName = "资源节点参照";
		referenceCode = ResourceNode.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "code,pathName,entityId";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(20);
		addColumn( datagrid,"id", "id", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"code", "编码", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"pathName", "名称", ControlTypes.TEXT_BOX,200,null,false);
		addColumn( datagrid,"entityId", "实体", ControlTypes.TEXT_BOX,200,null,false);
		return datagrid;
	}
}
