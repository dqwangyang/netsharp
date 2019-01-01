package org.netsharp.dataccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbSession {
	
	private static final Log logger = LogFactory.getLog(DbSession.class.getName());
	
	private static final ThreadLocal<DbSession> threadLocal = new ThreadLocal<DbSession>();
	private Connection con;
	private DatabaseProperty dbp;
	private boolean isTransaction=false;
	private boolean manualClose = false;//手工关闭数据库连接，服务级别的数据库连接默认自动关闭，如果设置为手工则不会自动关闭

	private DbSession() {
		this.dbp = DatabaseProperty.defaultDatabaseProperty();
	}

	private DbSession(DatabaseProperty dbp) {
		this.dbp = dbp;
	}
	
	public void manualClose() {
		
		try {
			if (this.con!=null && !this.con.isClosed()) {
				this.con.close();
				
				logger.trace("} 连接关闭");
			}
		} catch (SQLException e) {
			throw new DataAccessException("关闭数据库连接异常",  e);
		}
		threadLocal.remove();
		this.dispose();
	}

	public void close() {
		
		if(isManualClose()){
			return;
		}
		
		try {
			if (this.con!=null && !this.con.isClosed()) {
				this.con.close();
				
				logger.trace("} 连接关闭");
			}
		} catch (SQLException e) {
			throw new DataAccessException("关闭数据库连接异常",  e);
		}
		threadLocal.remove();
		this.dispose();
	}

	public void dispose() {
		// this.databaseProperty=null;
		// this.dao.close();
	}

	public void beginTransaction() {
		
		try {

			if (con!=null && con.getAutoCommit()) {
				logger.trace("{ begin");
				this.setTransaction(true);
			}
			else{
				this.isTransaction=true;
			}
		} catch (SQLException e) {
			throw new DataAccessException("启动数据库事务异常",  e);
		}
	}

	public void commit() {
		try {
			if (con!=null){
				logger.trace("} commit");
				con.commit();
				
				this.setTransaction(false);
			}
		} catch (SQLException e) {
			throw new DataAccessException("提交数据库事务异常",  e);
		}
	}

	public void rollback() {
		try {
			if (con!=null){
				logger.trace("} rollback");
				con.rollback();
				
				this.setTransaction(false);
			}
		} catch (SQLException e) {
			throw new DataAccessException("回滚数据库事务异常",  e);
		}
	}

	public void rollback(Savepoint savepoint) {
		try {
			if(this.con!=null){
				con.rollback(savepoint);
				
				this.setTransaction(false);
			}
		} catch (SQLException e) {
			throw new DataAccessException("回滚数据库事务异常",  e);
		}
	}

	Connection getConnection() {
		
		try {
			
			if (this.con == null || this.con.isClosed()) {
				
				this.con = DataSource.getConnection();
			}
			
			this.setTransaction(isTransaction);
			
			return con;
			
		} catch(DataAccessException e){
			throw e;
		}
		catch (Exception e) {
		    
		    throw new DataAccessException("未能创建数据库连接",e);
		}
	}

	public DatabaseProperty getDatabaseProperty() {
		return dbp;
	}

	public static DbSession start() {
		DbSession session = threadLocal.get();
		if (session == null) {
			session = new DbSession();
			threadLocal.set(session);
		}
		return session;
	}

	public static DbSession start(DatabaseProperty dbp) {
		DbSession session = threadLocal.get();
		if (session == null) {
			session = new DbSession(dbp);
			threadLocal.set(session);
		}
		return session;
	}

	public static DbSession getSession() {
		return threadLocal.get();
	}

	public boolean isManualClose() {
		return manualClose;
	}

	public void setManualClose(boolean manualClose) {
		this.manualClose = manualClose;
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) throws SQLException {
		
		if(isTransaction){
			logger.trace("isTransaction:"+isTransaction);
		}
		
		this.isTransaction = isTransaction;
		if(this.con!=null){
			con.setAutoCommit(!isTransaction);
		}
	}
}
