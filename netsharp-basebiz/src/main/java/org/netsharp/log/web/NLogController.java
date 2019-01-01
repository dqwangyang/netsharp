package org.netsharp.log.web;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.log.base.INLogService;
import org.netsharp.log.entity.NLog;
import org.netsharp.log.entity.NLogType;
import org.netsharp.session.SessionManager;

public class NLogController {
	
	private Log logger = LogFactory.getLog(NLogController.class);
	
	INLogService service=ServiceFactory.create(INLogService.class);
	
	public void log(String opType,String opWorkspaceId,String formUrl,String entityId){
		NLog entity=new NLog();{
			entity.toNew();
			
			entity.setLogType(NLogType.info);
			entity.setOperaitonName(opType);
			entity.setAssociateId(opWorkspaceId);
			entity.setMessage(formUrl);
			entity.setEntityId(entityId);
			
			entity.setCreator(SessionManager.getUserName());
			entity.setCreatorId(SessionManager.getUserId());
			entity.setCreateTime(new Date());
		}
		try{
			service.save(entity);
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	public void logQuery(String opType,String opWorkspaceId,String formUrl,String entityId,String filter){
		NLog entity=new NLog();{
			entity.toNew();
			
			entity.setLogType(NLogType.info);
			entity.setOperaitonName(opType);
			entity.setAssociateId(opWorkspaceId);
			entity.setMessage(formUrl);
			entity.setEntityId(entityId);
			entity.setMemoto(filter);
			
			entity.setCreator(SessionManager.getUserName());
			entity.setCreatorId(SessionManager.getUserId());
			entity.setCreateTime(new Date());
		}
		try{
			service.save(entity);
		}catch(Exception e){
			logger.error(e);
		}
	}
}
