package org.netsharp.wx.meta.ea;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.ea.entity.WxeaApp;

public class WxeaAppReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  WxeaApp.class.getSimpleName();
		datagridName = referenceName = "微信企业号APP";
		referenceCode = WxeaApp.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"code", "编码", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
