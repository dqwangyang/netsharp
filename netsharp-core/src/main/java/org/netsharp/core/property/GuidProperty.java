package org.netsharp.core.property;

import java.util.UUID;

import org.netsharp.util.StringManager;

public class GuidProperty extends EntityProperty {
	
	private static final long serialVersionUID = -5073589866328210385L;

	public void set(Object owner, Object value) {
		
		if(!(value instanceof UUID)){
			String id=(String)value;
			if(id.length()<30){
				value=null;
			}
			else{
				value=UUID.fromString(id);
			}
		}
		
		super.set(owner,value);
	}
	
	@Override
	public void field(Object owner, Object value) {
		
		if(value instanceof String){
			String id=(String)value;
			if(StringManager.isNullOrEmpty(id)){
				value=null;
			}
			else
			{
				value=UUID.fromString(id);
			}
		}

		super.field(owner,value);
	}
}
