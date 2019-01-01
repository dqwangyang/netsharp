package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.netsharp.core.id.SnowflakeId;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	
    String name();
    String header() default "";
    String icon() default "";
    boolean concurrent() default false;
    boolean checkReference() default false;
    boolean rowGateway() default false;
    String orderBy() default "";
    String service() default "";                   //实体对应的服务接口，如果不配置com.ykx.entity.SalesOrder的服务接口默认为com.ykx.base.ISalesOrderService
    Class<?> idType() default SnowflakeId.class;   //id处理器
    boolean isView() default false;               //是否是试图,试图是查询试题
 
}
