package org.netsharp.panda.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*panda的rest接口是否需要session控制，默认所有的接口都需要session控制*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
	
	boolean is() default true;
	
}
