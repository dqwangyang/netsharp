package org.netsharp.weui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.netsharp.weui.controls.IControlPropertySerializer;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlAttr{
	
    public String html(); //输出到客户端的HTML
    public String name() default ""; //名称
    public boolean mustWrite() default false; //必须输出
    public String defaultValue() default "";
    
    public Class<?> serializeType() default IControlPropertySerializer.class;
}
