package org.netsharp.cache.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*缓存Key配置*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cachekey {
	
	//缓存key取值字段
	//参数为基本类型时，参数直接作为key，参数为IPersistable子类时候需要配置属性名称作为key
	String property() default "";
	
}
