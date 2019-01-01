/**  
* @Title: PluginReferenceTest.java 
* @Package org.netsharp.panda.platform 
* @Description: TODO
* @author hanwei
* @date 2015-6-10 下午5:50:21 
* @version V1.0  
*/ 

package org.netsharp.meta.platform.ref;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

/** 
 * @ClassName: PluginReferenceTest 
 * @Description: TODO
 * @author hanwei 
 * @date 2015-6-10 下午5:50:21 
 *  
 */

public class PluginReferenceTest extends ReferenceCreationBase{
	

	@Before
	public void setup() {
		
		resourceNodeCode =  Plugin.class.getSimpleName();
		datagridName = referenceName = "插件参照";
		referenceCode = Plugin.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
