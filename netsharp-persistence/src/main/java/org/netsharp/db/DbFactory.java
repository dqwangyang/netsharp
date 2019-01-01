package org.netsharp.db;

public class DbFactory {
	
    public static IDb create(){
    	
    	Db db=new Db();
    	
    	return db;
    	
    }
}
