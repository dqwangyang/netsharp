package org.netsharp.wx.meta.pa.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.PublicAccount;

public class PublicAccountRefTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  PublicAccount.class.getSimpleName();
		datagridName = referenceName = "公众号参照";
		referenceCode = PublicAccount.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,100,null,false);
		addColumn( datagrid,"weixinCode", "微信号", ControlTypes.TEXT_BOX,100,null,false);
		return datagrid;
	}
}

