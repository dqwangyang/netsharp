package org.netsharp.cache.base.dict;

public enum CacheType {
	none,
	db,//基于数据库的缓存
	redis,//基于radis服务器的缓存
	file//基于文件的缓存
}
