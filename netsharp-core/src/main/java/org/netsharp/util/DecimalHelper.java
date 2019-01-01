package org.netsharp.util;

import java.math.BigDecimal;

public class DecimalHelper {
	
	public static String toString(BigDecimal decimal){
		
		String s=decimal.toString();
		
		int index=s.indexOf('.');
		
		if(index<=0){
			return s;
		}
		
		s = StringManager.trimEnd(s, '0');
		s = StringManager.trimEnd(s, '.');
		
		return s;
	}
}
