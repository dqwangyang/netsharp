package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: BillTransaction</P>
 * <p>Description: 单据交易字段注解，如果配置此注解，@BillCode注解不能配置billType的值，否则此配置无效</p>
 * <p>Copyright: 易快修 </p>
 * @author gaomeng
 * @version 1.0
 * @since 2016年3月16日
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizTransaction {
}
