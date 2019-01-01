package org.netsharp.panda.plugin.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.plugin.core.Codons;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;

@Table(name="rs_accordion",header="导航抽屉")
public class PAccordion extends Pathable{
	
	private static final long serialVersionUID = 5329660030880098239L;
	@Subs(subType=PAccordionItem.class,foreignKey="pathId")
	@Codons(type=PAccordionItem.class)
	private List<PAccordionItem> items;
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public List<Codonable> getCodons(){
		
		Object o = this.items;
		
		return (List<Codonable>)o;
	}

	public List<PAccordionItem> getItems() {
		if(items == null){
			items = new ArrayList<PAccordionItem>();
		}
		return items;
	}

	public void setItems(List<PAccordionItem> items) {
		this.items = items;
	}
}
