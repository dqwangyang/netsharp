package org.netsharp.core.id;

import org.netsharp.util.StringManager;

public class StringId implements IId {

	@Override
	public Object newId() {
		return null;
	}

	@Override
	public boolean isEmpty(Object id) {
		return StringManager.isNullOrEmpty((String)id);
	}

	@Override
	public String getEmptyFilter(String name) {
		return "("+name +" = '' or " + name +" is null)";
	}

}
