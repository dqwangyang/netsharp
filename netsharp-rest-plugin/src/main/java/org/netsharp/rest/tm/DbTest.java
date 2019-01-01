package org.netsharp.rest.tm;

import org.junit.Test;

public class DbTest {
	
	@Test
	public void rebuilddb() {
		
//		IDb db = DbFactory.create();
//		db.reCreateTable(MtableManager.getMtable(NoticeItem.class));
		
	}
	
	
	@Test
	public void splitpars() {
		
		String url="page=1&rows=50&annNum=1600&annType=&tmType=&coowner=&recUserName=&allowUserName=&byAllowUserName=&appId=&appIdZhiquan=&bfchangedAgengedName=&changeLastName=&transferUserName=&acceptUserName=&regName=&tmName=&intCls=&fileType=&totalYOrN=true&appDateBegin=&appDateEnd=";
	    String[] pars = url .split("&");
	    
	    for(String par : pars) {
	    	
	    	String[] pair = par.split("=");
	    	
	    	System.out.print("private String "+pair[0]);
	    	if(pair.length>1) {
	    		System.out.print(" = " +pair[1]);
	    	}
	    	
	    	System.out.println(";");
	    }
	}

}
