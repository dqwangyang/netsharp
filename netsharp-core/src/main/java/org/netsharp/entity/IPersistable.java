package org.netsharp.entity;

import java.io.Serializable;

import org.netsharp.core.EntityState;

public interface IPersistable extends Serializable {
	
	EntityState getEntityState();
	void setEntityState(EntityState entityState);

	/*读取属性,支持多级属性，如：order.get("customer.category.name");*/
	Object get(String property);
	
	/*设置属性,支持多级属性，如：order.set("customer.category.name","客户");*/
	void set(String property,Object value);
	
	/**
	 * 把对象的值复制到当前对象，只支持基本属性，不支持组合和引用关系的复制
	 * @param row
	 */
	void clone(IPersistable row);
	
	/**
	 * 设置实体为新增状态
	 */
	void toNew();
	
	/**
	 * 设置实体为修改状态
	 */
	void toPersist();
	
	/**
	 * 设置实体为删除状态
	 */
	void toDeleted();
	
	/**
	 * 设置实体为瞬时状态
	 */
	void toTransient();
}
