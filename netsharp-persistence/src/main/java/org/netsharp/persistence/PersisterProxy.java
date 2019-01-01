package org.netsharp.persistence;

import java.sql.ResultSet;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.entity.IPersistable;

public class PersisterProxy<T extends IPersistable> extends Persister<T> {
	
	private String name="persister";

	PersisterProxy(DatabaseProperty dbp){
		super(dbp);
	}

	@Override
	public T byId(T entity) {
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"byId"));
		
		entity = super.byId(entity);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"byId",System.currentTimeMillis()-startTime,""));
		
		return entity;
	}

	@Override
	public T byId(String entityId, Object id) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"byId"));
		
		T entity = super.byId(entityId,id);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"byId",System.currentTimeMillis()-startTime,""));
		
		return entity;
	}

	@Override
	public boolean persistNew(T entity) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"persistNew"));
		
		boolean is = super.persistNew(entity);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"persistNew",System.currentTimeMillis()-startTime,""));
		
		return is;
	}

	@Override
	public boolean persist(T entity) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"persist"));
		
		boolean is = super.persist(entity);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"persist",System.currentTimeMillis()-startTime,""));
		
		return is;
	}

	@Override
	public boolean save(T entity) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"save"));
		
		boolean is = super.save(entity);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"save",System.currentTimeMillis()-startTime,""));
		
		return is;
	}

	@Override
	public List<T> queryList(Oql oql) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"queryList"));
		
		List<T> is = super.queryList(oql);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"queryList",System.currentTimeMillis()-startTime,oql));
		
		return is;
	}

	@Override
	public T queryFirst(Oql oql) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"queryFirst"));
		
		T is = super.queryFirst(oql);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"queryFirst",System.currentTimeMillis()-startTime,oql));
		
		return is;
	}

	@Override
	public DataTable queryTable(Oql oql) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"queryTable"));
		
		DataTable is = super.queryTable(oql);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"queryTable",System.currentTimeMillis()-startTime,oql));
		
		return is;
	}

	@Override
	public int queryCount(Oql oql) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"queryCount"));
		
		int is = super.queryCount(oql);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"queryCount",System.currentTimeMillis()-startTime,oql));
		
		return is;
	}

	@Override
	public Paging queryIndex(Oql oql) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"queryIndex"));
		
		Paging is = super.queryIndex(oql);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"queryIndex",System.currentTimeMillis()-startTime,oql));
		
		return is;
	}

	@Override
	public ResultSet executeReader(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeReader"));
		
		ResultSet is = super.executeReader(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeReader",System.currentTimeMillis()-startTime,cmdText));
		
		return is;
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
	public int executeNonQuery(String cmdText, QueryParameters qps) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"executeNonQuery"));
		
		int is = super.executeNonQuery(cmdText,qps);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"executeNonQuery",System.currentTimeMillis()-startTime,cmdText));
		
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

	@Override
	public void bulk(List<T> list, boolean isDbAuto) {
		
		long startTime = System.currentTimeMillis();
		logger.debug(String.format("%s,%s,%s", "<",this.name,"bulk"));
		
		super.bulk(list,isDbAuto);
		
		logger.debug(String.format("%s,%s,%s,%s,%s", ">",this.name,"bulk",System.currentTimeMillis()-startTime,""));
		
	}
	
	
}
