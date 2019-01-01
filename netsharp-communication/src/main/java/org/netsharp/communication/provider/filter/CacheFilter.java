package org.netsharp.communication.provider.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.provider.IServiceFilter;
import org.netsharp.communication.provider.SiContext;

/*缓存拦截*/
public class CacheFilter implements IServiceFilter {

	protected final Log logger = LogFactory.getLog(this.getClass());

//	private Cacheable cacheable = null;
//	private CacheEvict cacheEvict = null;
//	private String key = null;
//	private boolean disabled = true;
//	private ICacheCommand cacheCommand = null;

	// 读取缓存
	public void invoking(SiContext ctx) {

//		disabled = CacheConfigruation.getDisabled(CacheType.redis);
//
//		if (disabled) {
//			return;
//		}
//
//		cacheCommand = CacheCommandFactory.create(CacheType.redis);
//
//		Method method = ctx.Method;
//
//		this.cacheable = method.getAnnotation(Cacheable.class);
//		this.cacheEvict = method.getAnnotation(CacheEvict.class);
//
//		if (this.cacheable == null && this.cacheEvict == null) {
//			return;
//		}
//
//		try {
//			this.key = CacheKeyHandler.createCacheKey(this.cacheable, this.cacheEvict, ctx.Name, method, ctx.Parameters);
//		} catch (Exception e) {
//			throw new CacheException("生成缓存KEY出错");
//		}
//
//		if (this.cacheable != null && this.cacheEvict == null) {
//			if (method.getReturnType() == void.class) {
//				throw new CacheException("使用缓存的方法必须有返回值");
//			}
//
//			Object obj = cacheCommand.get(this.key.getBytes());
//
//			if (obj != null) {
//				ctx.ReturnObject = obj;
//				ctx.IsCached = true;
//				ctx.IsSucceed = true;
//
//				logger.info("########### 命中缓存：" + key);
//			} else {
//				logger.info("########### 缓存未命中：" + key);
//			}
//		}
//
//		if (this.cacheable == null && this.cacheEvict != null) {
//			if (cacheCommand.exists(this.key.getBytes())) {
//				cacheCommand.del(this.key.getBytes());
//
//				logger.info("########### 清除了缓存：" + key);
//			}
//		}
	}

	// 添加缓存
	public void invoked(SiContext ctx) {

//		if (disabled) {
//			return;
//		}
//
//		if (this.cacheable == null && this.cacheEvict == null) {
//			return;
//		}
//
//		if (this.cacheable != null && this.cacheEvict == null) {
//			if (ctx.IsCached) {
//				return;
//			}
//
//			Object obj = ctx.ReturnObject;
//			// 如果没有设置过期时间,则无限期缓存
//			if (this.cacheable.expire() <= 0) {
//				cacheCommand.set(this.key.getBytes(), obj);
//			} else {
//				cacheCommand.set(this.key.getBytes(), this.cacheable.expire(), obj);
//			}
//
//			logger.info("########### 缓存添加：" + key);
//		}
	}
}
