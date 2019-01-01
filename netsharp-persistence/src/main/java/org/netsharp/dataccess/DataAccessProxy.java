package org.netsharp.dataccess;

import java.sql.ResultSet;

import org.netsharp.core.DataTable;
import org.netsharp.core.QueryParameters;

public class DataAccessProxy  extends DataAccess{
	
	private String name="dao";
	
	DataAccessProxy(DatabaseProperty dbp){
		super(dbp);
	}

	@Override
	public ResultSet executeResultSet(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeResultSet"));
		
		ResultSet ret = super.executeResultSet(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeResultSet",System.currentTimeMillis()-startTime,cmdText));
		
		return ret;
	}

	@Override
	public DataTable executeTable(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeTable"));
		
		DataTable is = super.executeTable(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeTable",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
	}

	@Override
	public DataTable executePaging(PagingObject paging, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executePaging"));
		
		DataTable is = super.executePaging(paging,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executePaging",System.currentTimeMillis()-startTime,""));
		
		return is;
	}

	@Override
	public void executePaging(PagingObject paging, QueryParameters qps, DataTable table) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executePaging"));
		
		super.executePaging(paging,qps,table);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executePaging",System.currentTimeMillis()-startTime,""));
		
	}

	@Override
	public int executeUpdate(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeUpdate"));
		
		int is = super.executeUpdate(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeUpdate",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
	}

	@Override
	public Object[] executeInsert(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeInsert"));
		
		Object[] is = super.executeInsert(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeInsert",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
	}

	@Override
	public Object executeScalar(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeScalar"));
		
		Object is = super.executeScalar(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeScalar",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
	}

	@Override
	public Integer executeInt(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeInt"));
		
		Integer is = super.executeInt(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeInt",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
	}



}
