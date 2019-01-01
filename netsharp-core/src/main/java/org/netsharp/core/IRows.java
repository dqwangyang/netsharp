package org.netsharp.core;

import java.io.Serializable;

public interface IRows<T extends IRow> extends Iterable<T>,Serializable {
	
	ITable<T> getTable();
	Class<?> getType();
	int size();
	IRow get(int index);
	IRow newLine();
	T newItem();
	boolean remove(IRow row);
}
