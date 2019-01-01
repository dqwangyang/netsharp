package org.netsharp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;

public class Oql implements Serializable {

	private static final long serialVersionUID = -8908632501147460202L;

	public static Oql newOne() {
		return new Oql();
	}

	private Class<?> type;
	public String entityId;
	public String selects;
	public String filter;
	public String orderby;
	public String groupby;
	public Boolean isRowGateway;
	public Paging paging;
	public QueryParameters parameters;
	public Object tag;

	public Oql() {

	}

	public Oql(String entityId, String selects, String filter, QueryParameters parameters) {

		this.entityId = entityId;
		this.selects = selects;
		this.filter = filter;
		this.parameters = parameters;

	}

	public String getSelects() {
		return selects;
	}

	public Oql setSelects(String selects) {
		this.selects = selects;
		return this;
	}

	public String getFilter() {
		return filter;
	}

	public Oql setFilter(String filter) {
		this.filter = filter;
		return this;
	}

	public String getEntityId() {
		return entityId;
	}

	public Oql setEntityId(String entityId) {
		this.entityId = entityId;
		return this;
	}

	public String getOrderby() {
		return orderby;
	}

	public Oql setOrderby(String orderby) {
		this.orderby = orderby;
		return this;
	}

	public String getGroupby() {
		return groupby;
	}

	public Oql setGroupby(String groupby) {
		this.groupby = groupby;
		return this;
	}

	public Boolean getIsRowGateway() {
		return isRowGateway;
	}

	public Oql setIsRowGateway(Boolean isRowGateway) {
		this.isRowGateway = isRowGateway;
		return this;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public QueryParameters getParameters() {
		if (parameters == null) {
			this.parameters = new QueryParameters();
		}
		return parameters;
	}

	public Oql setParameters(QueryParameters qps) {
		this.parameters = qps;
		return this;
	}

	public Object getTag() {
		return tag;
	}

	public Oql setTag(Object tag) {
		this.tag = tag;
		return this;
	}

	public static String WithNolock() {
		return "";
	}

	public Class<?> getType() {
		return type;
	}

	public Oql setType(Class<?> type) {
		this.type = type;
		if(this.type!=null) {
			this.entityId = this.type.getName();
		}

		return this;
	}

	@Override
	public String toString() {
		
		List<String> ss = new ArrayList<String>();
		if(!StringManager.isNullOrEmpty(this.entityId)) {
			ss.add("entityId:"+entityId);
		}
		if(!StringManager.isNullOrEmpty(this.selects)) {
			ss.add("selects:"+selects);
		}
		if(!StringManager.isNullOrEmpty(this.filter)) {
			ss.add("filter:"+filter);
		}
		if(!StringManager.isNullOrEmpty(this.orderby)) {
			ss.add("orderby:"+orderby);
		}
		
		return StringManager.join("|",ss);
	}
}
