package org.netsharp.core.id;

import java.util.UUID;

public class GuidId implements IId {

	private static UUID empty=UUID.fromString("00000000-0000-0000-000000-0000000000");
	
	public Object newId() {
        return UUID.randomUUID();
	}

	public boolean isEmpty(Object id) {
        if(id==null){
        	return true;
        }
        
        if(id instanceof UUID){
        	return id.equals(empty);
        }
        
        UUID uuid=UUID.fromString(id.toString());
        return uuid.equals(empty);
	}
	
	public String getEmptyFilter(String name) {
		return "("+name +" = "+empty.toString()+" or " + name +" is null)";
	}
}
