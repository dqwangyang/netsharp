package org.netsharp.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigItem {
	
	/**
	 * 配置key值
	 * */
	String key();
	
	/**
	 * 默认值
	 * */
	String defaultValue();
	/**
	 * 是否必输
	 * */
	boolean required() default false;
}
