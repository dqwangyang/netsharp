package org.netsharp.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.dic.DatabaseType;

@Table(name="sys_datatype",header="字段数据类型")
public class DataType extends BizEntity{

	private static final long serialVersionUID = 8118254286345057443L;
	
	@Column(name = "csharp",header="C#类型")
	protected String csharp;
	
	@Column(name = "default_Value",header="默认值")
    protected String defaultValue;
    
	@Column(name = "sqlserver",header="SqlServer类型")
    protected String sqlServer;
    
	@Column(name = "is_size",header="有长度")
    protected boolean isSize;
    
	@Column(name = "control_type",header="控件类型")
    protected String controlType;
    
	@Column(name = "db_type",header="ADONET类型")
    protected String dbType;
    
	@Column(name = "mysql",header="MySql类型")
    protected String mySql;
    
	@Column(name = "sqlite",header="SQLite类型")
    protected String sQLite;
    
	@Column(name = "db2",header="Db2类型")
    protected String db2;
    
	@Column(name = "oracle",header="Oracle类型")
    protected String oracle;
    
	@Column(name = "access",header="Access类型")
    protected String access;
    
	@Column(name = "alias",header="别名")
    protected String alias;
    
	@Column(name = "size_db",header="DB长度")
    protected int sizeDb;
    
	@Column(name = "is_precision",header="有精度")
    protected boolean isPrecision;
    
	@Column(name = "precision_db",header="DB精度")
    protected int precisionDb;
    
	@Column(name = "icon",header="图标")
    protected String icon;
    
	@Column(name = "is_splitter",header="序列化时可分割")
    protected boolean isSplitter;
    
	@Column(name = "size_display",header="显示长度")
    protected int sizeDisplay;
    
	@Column(name = "precision_display",header="显示精度")
    protected int precisionDisplay;
    
	@Column(name = "java",header="java类型")
    protected String java;
    
	@Column(name = "jdbc_type",header="DB长度")
    protected int jdbcType;

    public String getCsharp(){
        return this.csharp;
    }
    public DataType setCsharp(String csharp){
        this.csharp=csharp;
        return this;
    }
    public String getDefaultValue(){
        return this.defaultValue;
    }
    public DataType setDefaultValue(String defaultValue){
        this.defaultValue=defaultValue;
        return this;
    }
    public String getSqlServer(){
        return this.sqlServer;
    }
    public DataType setSqlServer(String sqlServer){
        this.sqlServer=sqlServer;
        return this;
    }
    public boolean isSize(){
        return this.isSize;
    }
    public DataType setSize(boolean isSize){
        this.isSize=isSize;
        return this;
    }
    public String getControlType(){
        return this.controlType;
    }
    public DataType setControlType(String controlType){
        this.controlType=controlType;
        return this;
    }
    public String getDbType(){
        return this.dbType;
    }
    public DataType setDbType(String dbType){
        this.dbType=dbType;
        return this;
    }
    public String getMySql(){
        return this.mySql;
    }
    public DataType setMySql(String mySql){
        this.mySql=mySql;
        return this;
    }
    public String getSQLite(){
        return this.sQLite;
    }
    public DataType setSQLite(String sQLite){
        this.sQLite=sQLite;
        return this;
    }
    public String getDb2(){
        return this.db2;
    }
    public DataType setDb2(String db2){
        this.db2=db2;
        return this;
    }
    public String getOracle(){
        return this.oracle;
    }
    public DataType setOracle(String oracle){
        this.oracle=oracle;
        return this;
    }
    public String getAccess(){
        return this.access;
    }
    public DataType setAccess(String access){
        this.access=access;
        return this;
    }
    public String getAlias(){
        return this.alias;
    }
    public DataType setAlias(String alias){
        this.alias=alias;
        return this;
    }
    public int getSizeDb(){
        return this.sizeDb;
    }
    public DataType setSizeDb(short sizeDb){
        this.sizeDb=sizeDb;
        return this;
    }
    public boolean isPrecision(){
        return this.isPrecision;
    }
    public DataType setPrecision(boolean isPrecision){
        this.isPrecision=isPrecision;
        return this;
    }
    public int getPrecisionDb(){
        return this.precisionDb;
    }
    public DataType setPrecisionDb(short precisionDb){
        this.precisionDb=precisionDb;
        return this;
    }
    public String getIcon(){
        return this.icon;
    }
    public DataType setIcon(String icon){
        this.icon=icon;
        return this;
    }
    public boolean isSplitter(){
        return this.isSplitter;
    }
    public DataType setSplitter(boolean isSplitter){
        this.isSplitter=isSplitter;
        return this;
    }
    public int getSizeDisplay(){
        return this.sizeDisplay;
    }
    public DataType setSizeDisplay(short sizeDisplay){
        this.sizeDisplay=sizeDisplay;
        return this;
    }
    public int getPrecisionDisplay(){
        return this.precisionDisplay;
    }
    public DataType setPrecisionDisplay(short precisionDisplay){
        this.precisionDisplay=precisionDisplay;
        return this;
    }
    public String getJava(){
        return this.java;
    }
    public DataType setJava(String java){
        this.java=java;
        return this;
    }
    
    public int getJdbcType(){
        return this.jdbcType;
    }
    
    public DataType setJdbcType(int jdbcType){
        this.jdbcType=jdbcType;
        return this;
    }
    
    public String getDbType(DatabaseType dbt){
    	if(dbt==DatabaseType.SqlServer){
    		return this.getSqlServer();
    	}
    	else if(dbt==DatabaseType.Oracle){
    		return this.getOracle();
    	}
    	else if(dbt==DatabaseType.SQLite){
    		return this.getSQLite();
    	}
    	else{
    		return this.getMySql();
    	}
    }
}