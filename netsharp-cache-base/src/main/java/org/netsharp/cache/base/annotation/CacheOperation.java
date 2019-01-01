package org.netsharp.cache.base.annotation;

public enum CacheOperation {
	cache,//查询时进行缓存处理
	lose;//修改和删除时让缓存失效
}
