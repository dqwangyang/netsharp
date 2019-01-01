package org.netsharp.dataccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.StringManager;

public class DataAccessException extends NetsharpException {
	
	private static final Log logger = LogFactory.getLog(DataAccessException.class.getName());
	private static final long serialVersionUID = 1L;
	
	private String cmdText;
	private String name;
	private QueryParameters qps;

	public DataAccessException(){
		super();
	}
	
	public DataAccessException(String message){
		super(message);
		
		logger.error(message);
	}
	
	public DataAccessException(Throwable throwable){
		super(throwable);
		
		logger.error(throwable);
	}
	
	public DataAccessException(String message,Throwable throwable){
		super(message,throwable);
		
		logger.error(message,throwable);
	}
	
	public DataAccessException(String name,String cmdText,QueryParameters qps, Throwable throwable){
		
		super(throwable);
		
		this.name=name;
		this.cmdText=cmdText;
		this.qps=qps;
		
		logger.error( StringManager.NewLine + "数据库访问异常，"+name+StringManager.NewLine+cmdText,throwable);
		
		if(qps!=null){
			logger.error("------------------------------");
			for(QueryParameter qp : qps){
				logger.error(StringManager.padLeft(qp.getName(), 20, ' ')+" : "+qp.getValue());
			}
		}
	}

	public String getCmdText() {
		return cmdText;
	}

	public String getName() {
		return name;
	}

	public QueryParameters getQps() {
		return qps;
	}
}
