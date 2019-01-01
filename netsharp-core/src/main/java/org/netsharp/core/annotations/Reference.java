package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Reference {

	String primaryKey() default "Id";

	String foreignKey();
	String groupName() default "";
	String header() default "";

	// 引用约束：联查时有一些引用对象可能为多个的场景，进行更精确的限制（参见下面：and order.status='payied'）：
	// select * from order_item left join order on order_item.order_id =
	// order.id and order.status='payied'
	String constraint() default "";

}
