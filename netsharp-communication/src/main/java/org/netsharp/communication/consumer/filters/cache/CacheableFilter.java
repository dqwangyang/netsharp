package org.netsharp.communication.consumer.filters.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.annotation.Cacheable;
import org.netsharp.cache.base.annotation.Cachekey;
import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class CacheableFilter implements IClientFilter {

	private final static Log logger = LogFactory.getLog(CacheableFilter.class);

	private ICacheCommand cmd;
	private String key;

	public void invoking(CiContext ctx) {

		// 缓存查询
		Method method = ctx.getMethod();
		Cacheable cacheable = method.getAnnotation(Cacheable.class);

		Parameter[] pars = method.getParameters();
		List<String> ss = new ArrayList<String>();
		{
			ss.add(cacheable.prefix());
		}

		for (int i = 0; i < pars.length; i++) {
			Parameter par = pars[i];
			Cachekey cachekey = par.getAnnotation(Cachekey.class);
			if (cachekey == null) {
				continue;
			}

			Object value = ctx.getRequest().getPars()[i];
			if (value == null) {
				logger.trace("缓存方法的参数实例值为空" + ctx.getName() + "(" + par.getName() + ")");
				return;
			}

			if (!StringManager.isNullOrEmpty(cachekey.property())) {
				Field field = ReflectManager.getField(value.getClass(), cachekey.property());
				field.setAccessible(true);
				value = ReflectManager.get(field, value);
			}

			if (value == null) {
				logger.trace("缓存方法的参数实例值为空" + ctx.getName() + "([" + par.getName() + "])");
				return;
			}
			ss.add(value.toString());
		}

		if (ss.size() == 1) {
			logger.trace(ctx.getName() + "配置了@Cacheable，但是参数没有配置@Cachekey");
			return;
		}

		this.key = StringManager.join("----", ss);
		this.cmd = CacheCommandFactory.create(CommunicationConfiguration.get().getCache(), "communication");

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

		// 执行缓存
		Object returnObject = ctx.getResponse().getReturnObject();
		if (returnObject != null) {
			logger.trace("执行缓存:" + this.key);
			this.cmd.set(key, returnObject);
		}
	}

}