package org.netsharp.entity;

import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.Column;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.TableRelation;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.id.IId;
import org.netsharp.core.property.IProperty;
import org.netsharp.util.StringManager;

/**
 * @author xufangbo 可持久化实体基类，不包括任何持久化字段 entityState用来设置实体的持久化状态
 */
public abstract class Persistable implements IPersistable {

	private static final long serialVersionUID = -1324838587185543878L;

	@Exclusive
	private EntityState entityState;

	@Exclusive
	@JsonIgnore
	protected HashMap<String, Object> innerValues = new HashMap<String, Object>();

	@Exclusive
	@JsonIgnore
	private Object tag;

	public EntityState getEntityState() {
		return entityState;
	}

	public void setEntityState(EntityState entityState) {
		if(entityState==EntityState.New){
			this.toNew();
		}
		else if(entityState==EntityState.Persist){
			this.toPersist();
		}
		else if(entityState==EntityState.Deleted){
			this.toDeleted();
		}
		else{
			this.entityState=entityState;
		}
	}

	public void clone(IPersistable row) {

		Mtable meta = MtableManager.getMtable(this.getClass());

		ArrayList<Column> columns = meta.getColumns();
		for (Column column : columns) {
			IProperty p = column.getProperty();
			Object value = p.field(row);
			p.field(this, value);
		}

		this.entityState = row.getEntityState();
	}

	/* 读取属性,支持多级属性，如：order.get("customer.category.name"); */
	public Object get(String property) {

		Mtable meta = MtableManager.getMtable(this.getClass());

		Object obj = this;
		if (property.contains(".")) {

			String[] ps = property.split("\\.");

			for (int i = 0; i < ps.length - 1; i++) {
				TableRelation ref = meta.getReference(ps[i]);
				if (ref == null) {
					ref = meta.getCompositeOne(ps[i]);
					if (ref == null) {
						throw new NetsharpException(meta.getEntityId() + "." + ps[i] + "引用不存在！");
					}
				}

				obj = ref.getPropertyValue(obj);

				if (obj == null) {
					return null;
				}

				meta = (Mtable) ref.getToTable();
			}

			property = ps[ps.length - 1];
		}

		Column column = meta.getPropertyOrColumn(property);
		if (column == null) {
			return this.getInnerValues().get(property);
		} else {
			IProperty p = column.getProperty();
			Object value = p.field(obj);

			return value;
		}
	}

	/* 设置属性,支持多级属性，如：order.set("customer.category.name","客户"); */
	public void set(String property, Object value) {

		if (StringManager.isNullOrEmpty(property)) {
			throw new NetsharpException("属性名不能为空");
		}
		if (property.contains(".")) {
			throw new NetsharpException("暂不支持多级属性");
		}

		Mtable meta = MtableManager.getMtable(this.getClass());

		Column column = meta.getPropertyOrColumn(property);
		if (column == null) {
			this.getInnerValues().put(property, value);
		} else {
			column.getProperty().field(this, value);
		}
	}

	public void toNew() {
		
		this.entityState = EntityState.New;
		
		Mtable meta = MtableManager.getMtable(this.getClass());
		Column keyColumn = meta.getKeyColumn();
		if(keyColumn!=null && !keyColumn.isAuto()){
			
			IId iid = meta.getId();
			Object id = meta.getId(this);
			if(iid.isEmpty(id)){
				meta.setId(this, iid.newId());
			}
		}
	}

	public void toPersist() {
		this.entityState = EntityState.Persist;
	}

	public void toDeleted() {
		this.entityState = EntityState.Deleted;
	}

	public void toTransient() {
		this.entityState = EntityState.Transient;
	}

	@JsonIgnore
	public HashMap<String, Object> getInnerValues() {
		return innerValues;
	}

	@JsonIgnore
	public Object getTag() {
		return tag;
	}

	@JsonIgnore
	public void setTag(Object tag) {
		this.tag = tag;
	}
}
