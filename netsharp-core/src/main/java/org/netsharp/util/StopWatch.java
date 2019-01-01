package org.netsharp.util;

public class StopWatch {
	
	long start;
	long end;
	
    public void start(){
    	start=System.currentTimeMillis();
    }
    
    public void stop(){
    	
    	end=System.currentTimeMillis();
    }
    
    public long getEclipsed(){
    	
    	long eclipsed= end-start;
    	
    	return eclipsed;
		
    }
}
