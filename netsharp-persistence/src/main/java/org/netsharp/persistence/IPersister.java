package org.netsharp.persistence;

import java.sql.ResultSet;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;

public interface IPersister<T extends IPersistable> {
	
	T byId(T entity);
	T byId(String entityId,Object id);

	//新增实体,不支持关联持久化,不考虑实体状体
	boolean persistNew(T entity);
	//修改实体,不支持关联持久化,不考虑实体状体
	boolean persist(T entity);
	//保存实体,支持关联持久化,考虑实体状体
	boolean save(T entity);
	List<T> queryList(Oql oql);
	
	T queryFirst(Oql oql);
	DataTable queryTable(Oql oql);
	int queryCount(Oql oql);
	Paging queryIndex(Oql oql);
	
	ResultSet executeReader(String cmdText, QueryParameters qps);
	DataTable executeTable(String cmdText, QueryParameters qps);
	int executeNonQuery(String cmdText, QueryParameters qps);
	Object executeScalar(String cmdText, QueryParameters qps);
	/*返回单个int值的查询，count,max,min等*/
	Integer executeInt(String cmdText, QueryParameters qps);
	
	/*批量插入，第二个参数为true时，使用数据库自增*/
	void bulk(List<T> list,boolean isDbAuto);
}
