package org.netsharp.plugin.bean;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.plugin.core.Codons;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;

@Table(name="rs_bean_path",header="Action")
@Doozer(type=DoozerBean.class)
public class BeanPath extends Pathable {	
	
	private static final long serialVersionUID = -6006620503951598002L;
	@Subs(subType=Bean.class,foreignKey="pathId")
	@Codons(type=Bean.class)
    private List<Bean> items = new ArrayList<Bean>();
	
	@SuppressWarnings("unchecked")
	public List<Codonable> getCodons(){
		
		Object o = this.items;
		
		return (List<Codonable>)o;
		
	}

	public List<Bean> getItems() {
		if(items==null){
			items = new ArrayList<Bean>();
		}
		return items;
	}

	public void setItems(List<Bean> items) {
		this.items = items;
	}
}
