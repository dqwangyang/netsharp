package org.netsharp.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.StringManager;

public class MtableManager {
	private static final Log logger = LogFactory.getLog(MtableManager.class);
//	private static ReentrantLock tableLock = new ReentrantLock();
	//
	private static Set set = new Set();

	public static Mtable getMtable(String entityId) {
		
		if(StringManager.isNullOrEmpty(entityId)){
			throw new NetsharpException("entityId为空，不能读取Mtable");
		}

		try {
			
			Class<?> type = Class.forName(entityId);
			return getMtable(type);
			
		} catch (ClassNotFoundException e) {
			logger.error("未能读取实体元数据:"+entityId,e);
		}

		return null;
	}
	
	public static Mtable getMtable(IRow entity) {

		String entityId= entity.getTable().getEntityId();
		Mtable mtable=getMtable(entityId);
		
		return mtable;
	}
	
	public static Mtable getMtable(Class<?> type) {
		
//		tableLock.lock();
		
		Mtable mtable = null;
		
		synchronized(set.tables){
			
			mtable = (Mtable) set.getTable(type);

			if (mtable == null) {
				mtable = parse(type);
				
				set.add(mtable);
			}
		}

//		tableLock.unlock();

		return mtable;
	}

	public static Mtable parse(Class<?> type) {
		
		if(DataTypes.String==null){
			DatatypeManager.initialize();
		}
		
		Mtable mtable = MtableParser.parse(type);
		return mtable;
	}
	
	public static Set getSet() {
		return set;
	}
}
