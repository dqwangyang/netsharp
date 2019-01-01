package org.netsharp.dataccess;

import java.sql.ResultSet;
import java.sql.Savepoint;

import org.netsharp.core.DataTable;
import org.netsharp.core.QueryParameters;


public interface IDataAccess {
	
	void open();
	void close();
	DatabaseProperty getDatabaseProperty();

	/*执行ResultSet查询*/
	ResultSet executeResultSet(String cmdText, QueryParameters qps);
	
	/*执行DataTable查询*/
	DataTable executeTable(String cmdText, QueryParameters qps);
	
	/*执行分页查询*/
	DataTable executePaging(PagingObject paging,QueryParameters qps);
	
	/*执行分页查询*/
	void executePaging(PagingObject paging, QueryParameters qps,DataTable table);	

	/*执行insert、update、delete等*/
	// 如果在此处添加事务，则无法得到服务的上下文信息
	int executeUpdate(String cmdText, QueryParameters qps);
	
	/*有自增列的插入时使用此方法*/
	public Object[] executeInsert(String cmdText, QueryParameters qps);
	
	/*查询单个值*/
	Object executeScalar(String cmdText, QueryParameters qps);
	
	/*返回单个int值的查询，count,max,min等*/
	Integer executeInt(String cmdText, QueryParameters qps);

	void beginTransaction();
	void commit();
	void rollback();
	void rollback(Savepoint savepoint);
}
