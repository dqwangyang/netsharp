package org.netsharp.panda.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PPart;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class WokspaceDockHelper {

	public static HashMap<DockType, ArrayList<Part>> getDockTypeGroup(Workspace workspace) {

		HashMap<DockType, ArrayList<Part>> groups = new HashMap<DockType, ArrayList<Part>>();

		for (PPart ppart : workspace.getPworkspace().getParts()) {
			if (!ppart.isVisible()) {
				continue;
			}

			//
			String partController = ppart.getServiceController();
			if (StringManager.isNullOrEmpty(partController)) {
				partController = ppart.getPartType().getServiceController();
			}
			Class<?> partType = ReflectManager.getType(partController);
			Part part = (Part) ReflectManager.newInstance(partType);
			part.context = ppart;
			part.Workspace = workspace;
			part.setId("part" + part.context.getCode());

			//
			if (!groups.containsKey(ppart.getDockStyle())) {
				groups.put(ppart.getDockStyle(), new ArrayList<Part>());
			}

			ArrayList<Part> partList = groups.get(ppart.getDockStyle());
			partList.add(part);
		}
		
		if(groups.containsKey(DockType.DOCUMENTHOST)){
			ArrayList<Part> documentParts= groups.get( DockType.DOCUMENTHOST );
			groups.remove(DockType.DOCUMENTHOST);
			
			HashMap<DockType, ArrayList<Part>> groups2 = groups;
			groups=new HashMap<DockType, ArrayList<Part>>();
			
			groups.putAll(groups2);
			
			groups.put(DockType.DOCUMENTHOST, documentParts);
		}

		return groups;
	}
}
