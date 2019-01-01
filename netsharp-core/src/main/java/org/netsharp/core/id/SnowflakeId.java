package org.netsharp.core.id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.application.Application;

public class SnowflakeId implements IId {

	private static IdWorker id = null;
	private static final Log logger = LogFactory.getLog(SnowflakeId.class.getName());

	public Object newId() {

		if (id == null) {
			
			Long workId = Application.getInstance().getWorkid();
			Long dataCenterId = Application.getInstance().getDatacenterid();

			id = new IdWorker(workId, dataCenterId);

		}
		return id.nextId();
	}

	public boolean isEmpty(Object id) {
		if (id == null) {
			return true;
		}

		if (id instanceof Long) {
			Long i = (Long) id;
			return i <= 0;
		} else {
			try {
				Long i = Long.valueOf(id.toString());
				return i <= 0;
			} catch (Exception e) {
				logger.warn("id转换异常" + id);
			}
			return false;
		}
	}

	public String getEmptyFilter(String name) {
		return "(" + name + " <=0 or " + name + " is null)";
	}
	
	
}
