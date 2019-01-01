package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.ArchiveEntity;
import org.netsharp.entity.IPersistable;
import org.netsharp.util.PinyinUtil;

public class PersistInterceptorArchive implements IPersistInterceptor {

	public void persistNew(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg) {
		this.setShorthand(entity);
	}

	public void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg) {
		this.setShorthand(entity);
	}

	public void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg) {
	}

	private void setShorthand(IPersistable entity) {
		if (entity instanceof ArchiveEntity) {
			ArchiveEntity ae = (ArchiveEntity) entity;

			String shorthand = PinyinUtil.getPinyinFirst(ae.getName());
			ae.setShorthand(shorthand);
		}
	}
}
