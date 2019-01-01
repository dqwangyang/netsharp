package org.netsharp.panda.core.workbench;

import java.util.List;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.accordion.Accordion;
import org.netsharp.panda.controls.accordion.AccordionItem;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.tab.TabItem;
import org.netsharp.panda.controls.tree.JTreeNodeSerializor;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Tree;
import org.netsharp.panda.controls.tree.Ul;
import org.netsharp.panda.plugin.entity.PPads;
import org.netsharp.plugin.core.AddInTree;
import org.netsharp.util.StringManager;

/**   
 * @ClassName:  LeftNavigation   
 * @Description:TODO
 * @author: 韩伟
 * @date:   2017年9月17日 下午4:30:52   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class LeftNavigation extends Ul{

    @Override
    public void initialize()
    {
		this.setClassName("left-nav");
		this.setId("nav");
		
		Li li = null;
		Span span = null;
		String padPath = "panda/workbench/pad";
		List<Object> items = AddInTree.buildItems(PPads.class, null, padPath,"pads");
		if (items != null && items.size() == 1) {

			TabItem tabItem = (TabItem) items.get(0);
			if (tabItem != null) {
				if (tabItem.getControls().size() == 1) {

					Accordion accordion = (Accordion) tabItem.getControls().get(0);
					if(accordion.getControls().size() == 0){
						
						//BUG，没有权限时
						this.getControls().add(new I());
					}else{
						
						for (Control item : accordion.getControls()) {
							
							if (item != null) {

								AccordionItem accordionItem = (AccordionItem) item;
								li = new Li();
								{
									li.onclick="workbench.selectNav(this);";
									li.setId(accordionItem.getId());
									if(!StringManager.isNullOrEmpty(accordionItem.iconCls)){

										I icon = new I();
										icon.setClassName(accordionItem.iconCls);
										li.getControls().add(icon);
									}
									
									span = new Span();
									span.value = accordionItem.title;
									li.getControls().add(span);
									
									this.getControls().add(li);
									Tree tree = (Tree) accordionItem.getControls().get(0);
									{
								        JTreeNodeSerializor j = new JTreeNodeSerializor();
								        String json = j.Serialize(tree);
								        if (!StringManager.isNullOrEmpty(json))
								        {
								            json = j.SubJson(json);
								            li.getInnerValues().put("data", json);
								        }
									}
								}
							}
						}
					}
				}
			}
		}
        super.initialize();
    }
}
