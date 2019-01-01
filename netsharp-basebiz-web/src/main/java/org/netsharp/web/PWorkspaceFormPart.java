package org.netsharp.web;

import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;

public class PWorkspaceFormPart extends FormPart {
	
	@Override
	public IPersistable save(IPersistable entity) {

		PWorkspace workspace = (PWorkspace)entity;
		for(PPart part : workspace.getParts()){
			if(part.getDatagrid()!=null){
				part.getDatagrid().toTransient();
			}
			if(part.getForm()!=null){
				part.getForm().toTransient();
			}
		}
		
		return super.save(entity);
	}
	
}
