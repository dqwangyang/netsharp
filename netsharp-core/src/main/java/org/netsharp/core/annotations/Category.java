package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.netsharp.core.id.SnowflakeId;

/*分类实体映射,配置到分类实体上*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Category {

	String code() default ""; // 编码字段名称

	String name() default "name"; // 名称字段名称

	String pathCode() default ""; // 分类编码字段名称

	String pathName() default ""; // 分类名称字段名称

	String leafName() default ""; // 末级节点标识字段

	String leafValue() default ""; // 末级节点为此值时的末级节点

	Class<?> idtype() default SnowflakeId.class;
}