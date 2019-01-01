package org.netsharp.db;

import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.Mtable;

public interface IDb {	
	
	/*根据实体元数据生成数据库表*/
	void createTable(Mtable meta);
	
	void reCreateTable(Mtable meta);
	
	void reCreateTable(List<Class<?>> clss);
	
	/*根据实体元数据同步数据库表结构*/
	void updateTable(Mtable meta,boolean isdropColumn);
	
	/*从数据库中drop一张表*/
	void dropTable(String tableName);
	
	/*判断当前数据库是否存在某张表*/
    boolean isTableExsist(String tableName);
    
    /*查询当前数据库所有表*/
    List<String> queryTables();
    
    /*备份数据库*/
    void backup(BackupOption option);
    
    /*还原数据库*/
    void restore(RestoreOption option);
    
    /*查询数据库表结构*/
    DataTable getTableSchema(String tableName);
    
    /*生成列声明*/
    String getColumnDeclare(Column column);
    
    /*得到实体未管理的列*/
    List<String> getUnmanagedColumns(Mtable meta);
    
    /*删除所有不受实体控制的数据库表，返回所有要删除的表名*/
    List<String> cleanTable();
    
    /*得到所有可持久化实体*/
    List<Class<?>> getAllPersistableEntities();
}
