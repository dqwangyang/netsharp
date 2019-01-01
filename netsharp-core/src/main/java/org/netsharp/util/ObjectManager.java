package org.netsharp.util;

public class ObjectManager {
	
    public static boolean equals(Object obj1,Object obj2){
    	
    	if(obj1==null){
    		return obj2==null;
    	}
    	else{
    		return obj1.equals(obj2);
    	}
    }
}
