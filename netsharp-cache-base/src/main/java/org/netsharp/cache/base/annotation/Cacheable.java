package org.netsharp.cache.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 缓存配置 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
	
	String prefix(); //缓存前缀，有些缓存的key为id时候，会重复，通过prefix来区分
	
}
