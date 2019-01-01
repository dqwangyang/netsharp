package org.netsharp.base;

import org.netsharp.entity.IPersistable;

public interface IBillCodeStrategy<T extends IPersistable> {
	public String run(String code,T entity);
}
