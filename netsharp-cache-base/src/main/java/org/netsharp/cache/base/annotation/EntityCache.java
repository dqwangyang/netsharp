package org.netsharp.cache.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 缓存配置 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityCache {
	
	String prefix(); 
	String property() default "id";
	String get() default "byId";
	String del() default "save";
	
}
