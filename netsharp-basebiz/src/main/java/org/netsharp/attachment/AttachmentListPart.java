package org.netsharp.attachment;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;


public class AttachmentListPart{
	protected IPersister<Attachment> pm = PersisterFactory.create();
	IAttachmentService AttachmentService = ServiceFactory.create(IAttachmentService.class);

	public List<Attachment> query(String enityId,Integer foreignKey){
		
		Oql oql = new Oql();{
			oql.setEntityId(Attachment.class.getName());
			oql.setSelects("*");
			oql.setFilter("entityId=? and foreignKey=?");
			oql.setParameters(new QueryParameters());
			oql.getParameters().add("@entityId", enityId, Types.VARCHAR);
			oql.getParameters().add("@foreignKey", foreignKey, Types.INTEGER);
		}
		
		return AttachmentService.queryList(oql);
	}
	

	public boolean delete(String ids) {

		String[] arr = ids.split("_");
		for (String id : arr) {
			
			Attachment entity = new Attachment();
			{
				entity.toDeleted();
				entity.setId(Long.valueOf(id));
			}
		
			this.AttachmentService.save(entity);
			
			//删除文件
			
		}
		return true;
	}
	
	public Boolean updateDownLoadCount(Long id){
		
    	String cmdText = "UPDATE sys_attachment SET downLoad_count=downLoad_count+1 WHERE id=?";
    	QueryParameters qps = new QueryParameters();
    	qps.add("@id", id, Types.BIGINT);
        return this.pm.executeNonQuery(cmdText, qps)>0;
		
	}
	
	public Attachment save(Attachment entity){
		
		return AttachmentService.save(entity);
	}
}
