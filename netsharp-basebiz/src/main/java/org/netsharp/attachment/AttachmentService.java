package org.netsharp.attachment;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class AttachmentService extends PersistableService<Attachment> implements IAttachmentService {
	public AttachmentService(){
		super(); 
		this.type = Attachment.class;
	}
}
