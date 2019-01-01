package org.netsharp.panda.plugin.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.plugin.core.Codons;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;

@Table(name="rs_pads",header="工作台面板")
public class PPads  extends Pathable {	
	
	private static final long serialVersionUID = -4525213920913171483L;
	
	@Subs(subType=PPad.class,foreignKey="pathId")
	@Codons(type=PPad.class)
    private List<PPad> items;
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public List<Codonable> getCodons(){
		
		Object o = this.items;
		
		return (List<Codonable>)o;
		
	}

	public List<PPad> getItems() {
		if(items==null){
			items = new ArrayList<PPad>();
		}
		return items;
	}

	public void setItems(List<PPad> items) {
		this.items = items;
	}
}