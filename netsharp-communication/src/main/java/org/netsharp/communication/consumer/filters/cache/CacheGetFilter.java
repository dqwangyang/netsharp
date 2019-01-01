package org.netsharp.communication.consumer.filters.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.entity.IPersistable;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class CacheGetFilter implements IClientFilter {

	private final static Log logger = LogFactory.getLog(CacheableFilter.class);

	private ICacheCommand cmd;
	private String key;

	public void invoking(CiContext ctx) {
		
		this.key = getKey(ctx);
		if(this.key == null) {
			return;
		}
		
		this.cmd = CacheCommandFactory.create( CommunicationConfiguration.get().getCache(), "communication");
		Object returnObject = this.cmd.get(this.key);
		ctx.setIsCacheHint(returnObject != null);

		if (!ctx.getIsCacheHint()) {
			logger.trace("缓存未命中:" + this.key);
			return;
		} else {
			logger.trace("命中:" + this.key);
		}

		InvokeResponse response = new InvokeResponse();
		{
			response.setSucceed(true);
			response.setReturnObject(returnObject);
		}
		ctx.setResponse(response);
	}

	public void invoked(CiContext ctx) {

		if (ctx.getIsCacheHint()) {
			return;
		}
		
		if(this.key == null) {
			return;
		}

		// 执行缓存
		Object returnObject = ctx.getResponse().getReturnObject();
		if (returnObject != null) {
			logger.trace("执行缓存:" + this.key);
			this.cmd.set(key, returnObject);
		}
	}
	
	static String getKey(CiContext ctx) {
		
		Class<?> type = ReflectManager.getType(ctx.getRequest().getType());
		EntityCache entityCache = type.getAnnotation(EntityCache.class);
		
		List<String> ss = new ArrayList<String>();{
			ss.add(entityCache.prefix());
		}

		Object value = ctx.getRequest().getPars()[0];
		if (value == null) {
			logger.trace("缓存方法的参数实例值为空" + ctx.getName() + "()");
			return null;
		}
		
		if(IPersistable.class.isInstance(value)) {
			IPersistable entity = (IPersistable)value;
			value = entity.get(entityCache.property());
		}
		
		if (value == null) {
			logger.trace("缓存方法的参数实例值为空" + ctx.getName() + "(entity[" + entityCache.property() + "])");
			return null;
		}
		ss.add(value.toString());

		return StringManager.join("----", ss);
	}

}