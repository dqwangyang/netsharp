package org.netsharp.persistence.query;

import java.util.ArrayList;

import org.netsharp.util.StringManager;

public class TSet {
	
    public TTable Main;
    public ArrayList<TTable> Tables=new ArrayList<TTable>();
    
    public TTable getTable(String className){
    	
    	for(TTable table : this.Tables){
    		if(StringManager.equals(className, table.Mtable.getEntityId())){
    			return table;
    		}
    	}
    	
    	return null;
    }
    
    public void connect(){
    	
    	for(TTable table : this.Tables){
    		table.connect(this);
    	}
    }
}
