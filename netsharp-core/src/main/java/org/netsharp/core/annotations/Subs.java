package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subs {
	
	Class<?> subType();
    String primaryKey() default "Id";
    String foreignKey();
    String groupName() default "";
    String header() default "";
}
