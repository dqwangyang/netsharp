package org.netsharp.panda.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlNode {
	
	public String html(); //输出到客户端的HTML
	public String name() default "";//名称
	public boolean isValue() default false;
}
