package org.netsharp.web;

import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.entity.PDatagrid;

public class PDatagridFormPart extends FormPart {
	@Override
	public IPersistable save(IPersistable entity) {

		PDatagrid datagrid = (PDatagrid)entity;
		if(datagrid.getQueryProject()!=null){
			datagrid.getQueryProject().toTransient();
		}
		
		if(datagrid.getAdvancedQueryProject()!=null){
			datagrid.getAdvancedQueryProject().toTransient();
		}
		
		return super.save(entity);
	}
}
