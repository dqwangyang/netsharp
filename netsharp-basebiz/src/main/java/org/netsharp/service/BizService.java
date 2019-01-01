package org.netsharp.service;

import org.netsharp.autoencoder.core.EncoderManager;
import org.netsharp.core.EntityState;
import org.netsharp.entity.BizEntity;

public class BizService<T extends BizEntity> extends PersistableService<T> {
	
	public BizService() {
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T save(T entity) {
		if(this.type==null){
			this.type = (Class<T>)entity.getClass();
		}
		if (EntityState.New.equals(entity.getEntityState())) {
			EncoderManager.createBillCode(entity);
		}

		this.pm.save(entity);

		return entity;
	}
}
