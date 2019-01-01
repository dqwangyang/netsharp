package org.netsharp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.netsharp.util.SAXWriter;

public interface ITable<T extends IRow> extends IRows<T>,Iterable<T>,Serializable {

	/**
	 * 得到当前实体/表所有字段信息
	 * @return
	 */
	ArrayList<Column> getColumns();
	
	/**
	 * 根据属性名得到字段信息，大小写敏感
	 * @param propertyName
	 * @return
	 */
	Column getProperty(String propertyName);
	
	/**
	 * 根据数据库字段名得到字段信息，大小写不敏感
	 * @param columnName
	 * @return
	 */
	Column getColumn(String columnName);
	
	/**
	 * 根据属性名或者数据库字段名得到字段信息，大小写不敏感
	 * @param propertyName
	 * @return
	 */
	Column getPropertyOrColumn(String name);
	T byUid(Object ... uid);
	
	void setColumns(ArrayList<Column> columns);

	String getEntityId();
	void setEntityId(String entityId);
	
	String getOrderby();
	void setOrderby(String orderby);
	
	String getCode();
	void setCode(String code);
	
	String getTableName() ;

	void setTableName(String tableName);
	
	Column getKeyColumn();
	void setKeyColumn(Column keyColumn);
	Column getAutoColumn();
	void setAutoColumn(Column autoColumn);
	
	String getName();
	void setName(String name);
	
	int getTotalCount();
	void setTotalCount(int totalCount);
	
	Set getSet();
	void setSet(Set set);
	
    HashMap<String, TableSubs> getSubs();
    HashMap<String, TableReference> getReferences();
    
    TableSubs getSub(String subCode);
    TableReference getReference(String refCode);
    
    boolean getIsView();
	void setIsView(boolean isView);
	boolean getIsConcurrency();
	void setIsConcurrency(boolean isConcurrency);
	boolean getIsRefcheck() ;
	void setIsRefcheck(boolean isRefcheck);
	void write(SAXWriter writer);
}
