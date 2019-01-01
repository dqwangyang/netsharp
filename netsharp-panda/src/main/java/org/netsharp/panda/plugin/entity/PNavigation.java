package org.netsharp.panda.plugin.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.plugin.core.Codons;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;

@Table(name="rs_navigation",header="树插件")
public class PNavigation extends Pathable {
	
	private static final long serialVersionUID = -261835450428317438L;
	@Subs(subType=PNavigationItem.class,foreignKey="pathId")
	@Codons(type=PNavigationItem.class)
	private List<PNavigationItem> treeNodes;
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public List<Codonable> getCodons(){
		
		Object o = this.treeNodes;
		
		return (List<Codonable>)o;
	}

	public List<PNavigationItem> getTreeNodes() {
		if(treeNodes == null){
			treeNodes = new ArrayList<PNavigationItem>();
		}
		return treeNodes;
	}

	public void setTreeNodes(List<PNavigationItem> treeNodes) {
		this.treeNodes = treeNodes;
	}
}
