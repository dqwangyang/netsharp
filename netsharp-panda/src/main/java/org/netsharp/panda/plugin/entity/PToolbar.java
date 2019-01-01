package org.netsharp.panda.plugin.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.plugin.core.Codons;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;

@Table(name="rs_toolbar",header="工具栏")
public class PToolbar extends Pathable {	
	
	private static final long serialVersionUID = -6006620503951598002L;
	
	@Subs(subType=PToolbarItem.class,foreignKey="pathId")
	@Codons(type=PToolbarItem.class)
    private List<PToolbarItem> items;
	
	@Column(name="type")
	private ToolbarType toolbarType;
	
	@Column(name="row_show",header="显示在表格行里")
	private boolean rowShow = false;
	
	@SuppressWarnings("unchecked")
	public List<Codonable> getCodons(){
		
		Object o = this.items;
		return (List<Codonable>)o;
	}

	public List<PToolbarItem> getItems() {
		if(items==null){
			items = new ArrayList<PToolbarItem>();
		}
		return items;
	}

	public void setItems(List<PToolbarItem> items) {
		this.items = items;
	}

	public ToolbarType getToolbarType() {
		return toolbarType;
	}

	public void setToolbarType(ToolbarType toolbarType) {
		this.toolbarType = toolbarType;
	}

	public boolean isRowShow() {
		return rowShow;
	}

	public void setRowShow(boolean rowShow) {
		this.rowShow = rowShow;
	}
	
}
