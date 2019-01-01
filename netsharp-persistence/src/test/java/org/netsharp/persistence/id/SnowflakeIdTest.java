package org.netsharp.persistence.id;

import org.junit.Test;
import org.netsharp.core.id.SnowflakeId;

public class SnowflakeIdTest {
	
	@Test
	public void generate(){
		
		SnowflakeId ider = new SnowflakeId();
		for(int i=0;i<1000;i++){
			Object id=ider.newId();
			System.out.println(id);
		}
		
	}
}
