package org.netsharp.communication.consumer.filters.cache;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.util.ReflectManager;

public class CacheFactory {
	
	private final static Log logger = LogFactory.getLog(CacheFactory.class);

	public static IClientFilter create(CiContext ctx) {

		CacheType cacheType = CommunicationConfiguration.get().getCache();
		if (cacheType == CacheType.none) {
			return null;
		}

		// 缓存查询
		Method method = ctx.getMethod();
		Cacheable cacheable = method.getAnnotation(Cacheable.class);
		if (cacheable != null) {
			return new CacheableFilter();
		}

		Class<?> type = ReflectManager.getType(ctx.getRequest().getType());
		EntityCache entityCache = type.getAnnotation(EntityCache.class);
		if (entityCache == null) {
			return null;
		}
		
		if(ctx.getRequest().getPars().length!=1) {
			logger.trace(ctx.getName() + ",@EntityCache只能读取一个参数的方法作为缓存key");
			return null;
		}
		
		if(method.getName().equals(entityCache.get())) {
			return new CacheGetFilter();
		}
		
		if(method.getName().equals(entityCache.del())) {
			return new CacheDeleteFilter();
		}
		
		return null;
	}
}
