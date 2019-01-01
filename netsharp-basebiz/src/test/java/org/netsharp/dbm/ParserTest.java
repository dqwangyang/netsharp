package org.netsharp.dbm;

import org.junit.Test;
import org.netsharp.dbm.service.parse.DbmParser;
import org.netsharp.dbm.service.parse.DbmQL;
import org.netsharp.organization.entity.Employee;

public class ParserTest {
	
	@Test
	public void commonOql() {
		
		String cmdText="select Employee.* from " + Employee.class.getName() ;
		
		DbmParser parser = new DbmParser();
		DbmQL oql = parser.parse(cmdText);
		System.out.println(oql.toString());
	}
	
	@Test
	public void commonOqlFilter() {
		
		String cmdText="select Employee.* from " + Employee.class.getName() + " where Employee.mobile='{crpt18613804375!crpt}'";
		
		DbmParser parser = new DbmParser();
		DbmQL oql = parser.parse(cmdText);
		
		System.out.println(oql.toString());
	}
}
