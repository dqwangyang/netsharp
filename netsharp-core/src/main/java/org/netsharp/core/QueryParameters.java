package org.netsharp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class QueryParameters implements Iterable<QueryParameter>, Serializable {

	private static final long serialVersionUID = -1321891341681899116L;
	
	private ArrayList<QueryParameter> innerList = new ArrayList<QueryParameter>();

	public void add(QueryParameter qp) {
		this.innerList.add(qp);
	}

	public QueryParameter add(String name, Object value, int dbType) {

		QueryParameter qp = new QueryParameter();
		{
			qp.setName(name);
			qp.setValue(value);
			qp.setDbType(dbType);
		}

		this.innerList.add(qp);

		return qp;
	}

	public QueryParameter add(String name, Object value, int dbType, boolean encryption) {

		QueryParameter qp = new QueryParameter();
		{
			qp.setName(name);
			qp.setValue(value);
			qp.setDbType(dbType);
			qp.setEncryption(encryption);
		}

		this.innerList.add(qp);

		return qp;
	}

	public void clear() {
		this.innerList.clear();
	}

	public int size() {
		return this.innerList.size();
	}

	public QueryParameter get(int index) {
		return this.innerList.get(index);
	}

	public QueryParameter by(String name) {
		for (QueryParameter qp : this.innerList) {
			if (qp.getName() == name) {
				return qp;
			}
		}

		return null;
	}

	@Override
	public Iterator<QueryParameter> iterator() {
		return this.innerList.iterator();
	}
}
