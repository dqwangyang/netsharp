package org.netsharp.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * orm映射的字段配置
 * @author xufb
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	/**
	 * @return 数据库列名
	 */
	String name() default "";
	String groupName() default "";
	String header() default "";
	String memoto() default "";
	
    /**
     * @return 默认值(字符类型，框架会自动转换成java对应的类型)
     */
    String defaultValue() default "";
    
    
    /**
     * 有的实体在执行复制时有些字段不进行复制，比如业务单据的审核人和单据状态
     * @return 是否复制属性，在调用实体的clone是使用
     */
    boolean noCopy() default false;
    
    /**
     * @return 字段长度
     */
    int size() default 0;
    
    /**
     * @return 字段精度
     */
    int precition() default 0;
    
    /**
     * @return 字段类型名称
     * @see org.netsharp.core.DataType
     */
    String typeName() default "";
    
    /**
     * 必输
     */
    boolean required() default false;
    
    /**
     * 唯一约束
     */
    boolean unique() default false;
    
    /**/
    boolean mobiles() default false;
    
    /**
     * 敏感信息，列表显示的时候*处理
     */
    boolean sensitive() default false;
}
