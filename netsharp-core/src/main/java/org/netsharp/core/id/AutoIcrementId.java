package org.netsharp.core.id;

/*数据库自增Id*/
public class AutoIcrementId implements IId {

	public Object newId() {
		return -1;
	}

	public boolean isEmpty(Object id) {
		if (id == null) {
			return true;
		}

		if (id instanceof Integer) {
			Integer i = (Integer) id;
			return i <= 0;
		} else {
			Integer i = Integer.valueOf(id.toString());
			return i <= 0;
		}
	}

	public String getEmptyFilter(String name) {
		return "("+name +" <=0 or " + name + " is null)";
	}
}
