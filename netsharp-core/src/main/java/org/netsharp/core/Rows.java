package org.netsharp.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Rows<T extends IRow> implements IRows<T> {
	
	private static final long serialVersionUID = 502813468342477190L;
	protected final Log logger = LogFactory.getLog(this.getClass());
	//
	private Class<?> type;
	protected ITable<T> table;
	protected ArrayList<T> innerList = new ArrayList<T>();

	@Override
	public Iterator<T> iterator() {
		return innerList.iterator();
	}

	public Class<?> getType() {
		return this.type;
	}

	// protected
	void setType(Class<?> type) {
		this.type = type;
	}

	@Override
	public ITable<T> getTable() {
		return table;
	}

	// protected
	@SuppressWarnings("unchecked")
	void setTable(ITable<?> table) {
		this.table = (ITable<T>) table;
	}

	@SuppressWarnings("unchecked")
	boolean add(IRow row) {
		return this.innerList.add((T) row);
	}

	@Override
	public int size() {
		return this.innerList.size();
	}

	@Override
	public IRow get(int index) {
		return this.innerList.get(index);
	}

	@SuppressWarnings("unchecked")
	public T newLine() {

		try {
			// T row = (T)this.getType().newInstance();

			Constructor<?> c = this.getType().getDeclaredConstructor();
			c.setAccessible(true);
			T row = (T) c.newInstance();

			((Row) row).setTable(this.table);

			this.innerList.add(row);

			return row;

		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public T newItem() {

		T item = this.newLine();
		item.setEntityState(EntityState.New);

		Row row = (Row) item;
		row.setTable(this.getTable());

		return item;
	}

	public boolean remove(IRow row) {
		return this.innerList.remove(row);
	}
}
