package org.netsharp.persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.TableCompositeOne;
import org.netsharp.core.TableReference;
import org.netsharp.core.TableSubs;
import org.netsharp.dataccess.DataAccessException;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.interceptor.IPersistInterceptor;
import org.netsharp.persistence.interceptor.PersistInterceptorArg;
import org.netsharp.persistence.interceptor.PersistInterceptorManager;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.persistence.oql.parser.table.TableParser;
import org.netsharp.persistence.query.Query;
import org.netsharp.persistence.query.QueryTable;
import org.netsharp.persistence.query.TSet;
import org.netsharp.persistence.sql.ISqlGenerator;
import org.netsharp.persistence.sql.ParamField;
import org.netsharp.persistence.sql.SqlGeneratorFactory;
import org.netsharp.persistence.sql.SqlGeneratorType;
import org.netsharp.persistence.sql.SqlStruct;
import org.netsharp.session.SessionManager;
import org.netsharp.util.ObjectManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class Persister<T extends IPersistable> implements IPersister<T> {
	
	protected static Log logger = LogFactory.getLog(Persister.class);
	//
	IDataAccess dao;
	private boolean isImport;
	private boolean isAutoClose;
	private boolean isClosed;

	Persister(DatabaseProperty dbp){
		this.dao = DataAccessFactory.create(dbp);
	}

	public T byId(T entity) {
		return byId(entity, SqlGeneratorType.FullSelect);
	}

	@SuppressWarnings("unchecked")
	public T byId(String entityId, Object id) {

		IPersistable obj = (IPersistable) ReflectManager.newInstance(entityId);
		Mtable mtable = MtableManager.getMtable(entityId);
		mtable.setId(obj, id);

		return this.byId((T) obj);
	}

	@SuppressWarnings("unchecked")
	public T byId(Class<?> type, Object id) {

		IPersistable obj = (IPersistable) ReflectManager.newInstance(type);
		Mtable mtable = MtableManager.getMtable(type);
		mtable.setId(obj, id);

		return this.byId((T) obj);
	}

	public T byId(T entity, SqlGeneratorType operationType) {
		Mtable meta = MtableManager.getMtable(entity.getClass());

		ISqlGenerator sqlGenerator = SqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());

		SqlStruct ss = sqlGenerator.generate(meta, operationType);

		Object id = meta.getId(entity);
		Column keyColumn = meta.getKeyColumn();
		String propertyName = keyColumn.getColumnName();
		
		Oql oql = new Oql();
		{
			oql.setEntityId(meta.getEntityId());
			oql.setSelects(ss.getSqlString());
			oql.setFilter(propertyName + "=?");

			oql.getParameters().add("@" + propertyName, id, keyColumn.getDataType().getJdbcType());
		}

		List<T> items = this.queryList(oql);

		if (items == null || items.size() == 0) {
			return null;
		}

		return items.get(0);
	}

	public boolean persistNew(T entity) {

		if (entity instanceof IEntity) {
			IEntity sys = (IEntity) entity;

			if (sys.getCreateTime() == null) {
				sys.setCreateTime(new Date());
			}

			if (sys.getCreatorId() == null || sys.getCreatorId().intValue() <= 0) {
				sys.setCreatorId(SessionManager.getUserId());
				sys.setCreator(SessionManager.getUserName());
			}
		}

		return this.persistNew(entity, this.isImport);
	}

	public boolean persistNew(T entity, boolean isImport) {
		PersistInterceptorArg arg = null;
		if (!isImport) {
			arg = new PersistInterceptorArg();

			for (IPersistInterceptor interceptor : PersistInterceptorManager.BeforeInterceptors) {
				interceptor.persistNew(entity, null, dao, arg);
			}
		}

		int result = this.execute(entity, SqlGeneratorType.Insert, false);

		if (!isImport) {
			arg.ExecutedCount = result;
			for (IPersistInterceptor interceptor : PersistInterceptorManager.AfterInterceptors) {
				interceptor.persistNew(entity, null, dao, arg);
			}
		}

		entity.setEntityState(EntityState.Transient);

		return result > 0;
	}

	public boolean persist(T entity) {
		if (entity instanceof IEntity) {

			IEntity sys = (IEntity) entity;
			if (sys.getUpdateTime() == null) {
				sys.setUpdateTime(new Date());
			}

			if (sys.getUpdatorId() == null || sys.getUpdatorId().intValue() <= 0) {
				sys.setUpdatorId(SessionManager.getUserId());
				sys.setUpdator(SessionManager.getUserName());
			}
		}
		return this.persist(entity, this.isImport);
	}

	public boolean persist(T entity, boolean isimport) {
		PersistInterceptorArg arg = new PersistInterceptorArg();

		for (IPersistInterceptor interceptor : PersistInterceptorManager.BeforeInterceptors) {
			interceptor.persist(entity, null, dao, arg);
		}

		arg.ExecutedCount = execute(entity, SqlGeneratorType.Update, isimport);

		if (!isimport) {
			for (IPersistInterceptor interceptor : PersistInterceptorManager.AfterInterceptors) {
				interceptor.persist(entity, null, dao, arg);
			}
		}

		entity.setEntityState(EntityState.Transient);

		return arg.ExecutedCount > 0;
	}

	public boolean persist(T entity, String[] properties) {
		return this.persist(entity, properties, this.isImport);
	}

	public boolean persist(T entity, String[] properties, boolean isimport) {
		if (properties != null && properties.length == 0) {
			return true;
		}

		Mtable meta = MtableManager.getMtable(entity.getClass());
		PersistInterceptorArg arg = new PersistInterceptorArg();

		for (IPersistInterceptor interceptor : PersistInterceptorManager.BeforeInterceptors) {
			interceptor.persist(entity, null, dao, arg);
		}

		QueryParameters qps = new QueryParameters();
		ArrayList<String> wheres = new ArrayList<String>();
		Object id = meta.getId(entity);
		Column keyColumn = meta.getKeyColumn();
		wheres.add(keyColumn.getColumnName() + "=?");
		qps.add("@" + keyColumn.getPropertyName(), id, keyColumn.getDataType().getJdbcType());

		List<String> ps = new ArrayList<String>();
		for (String item : ps) {
			ps.add(item);
		}

		if (!ps.contains("updateTime")) {
			ps.add("updateTime");
		}

		if (!ps.contains("idUpdator")) {
			ps.add("idUpdator");
		}

		if (!ps.contains("updator")) {
			ps.add("updator");
		}

		//
		ArrayList<String> setters = new ArrayList<String>();
		for (String propertyName : ps) {
			if (propertyName == "ts") {
				continue;
			}

			if (keyColumn.getPropertyName() == propertyName) {
				continue;
			}

			Column column = meta.getProperty(propertyName);
			if (column == null) {
				TableSubs composite = meta.getSub(propertyName);

				if (composite != null) {
					continue;
				}

				TableReference reference = meta.getReference(propertyName);
				if (reference == null) {
					continue;
				} else {
					propertyName = reference.getForeignkey();
					column = meta.getProperty(propertyName);
				}
			}

			String param = propertyName + "=?";
			if (!setters.contains(param)) {
				setters.add(param);

				qps.add("@" + propertyName, column.getProperty().dbField(entity), column.getDataType().getJdbcType());
			}
		}

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ").append(meta.getTableName()).append(StringManager.NewLine).append(" SET ").append(StringManager.NewLine).append(StringManager.join(",", setters)).append(StringManager.NewLine).append(" WHERE ").append(StringManager.NewLine).append(StringManager.join(" AND ", wheres)).append(StringManager.NewLine);

		String cmdText = builder.toString();

		this.open();
		arg.ExecutedCount = dao.executeUpdate(cmdText, qps);
		this.autoClose();

		if (!isimport) {
			for (IPersistInterceptor interceptor : PersistInterceptorManager.AfterInterceptors) {
				interceptor.persist(entity, null, dao, arg);
			}
		}

		return arg.ExecutedCount > 0;
	}

	public boolean delete(T entity) {
		// 未做功能，应该把所有组合关系的对象都批量删除吧？
		// 现在已经有了并发控制选项和引用检查选项则可以执行此操作了

		Mtable meta = MtableManager.getMtable(entity.getClass());
		PersistInterceptorArg arg = new PersistInterceptorArg();
		for (IPersistInterceptor interceptor : PersistInterceptorManager.BeforeInterceptors) {
			interceptor.delete(entity, meta, dao, arg);
		}

		arg.ExecutedCount = this.execute(entity, SqlGeneratorType.Delete, false);

		for (IPersistInterceptor interceptor : PersistInterceptorManager.AfterInterceptors) {
			interceptor.delete(entity, meta, dao, arg);
		}

		entity.setEntityState(EntityState.Transient);

		return arg.ExecutedCount > 0;
	}

	public boolean delete(String entityName, String filter, QueryParameters qps) {
		Mtable meta = MtableManager.getMtable(entityName);

		String cmdText = "DELETE FROM " + meta.getTableName();

		if (!StringManager.isNullOrEmpty(filter)) {
			cmdText += " WHERE " + filter;
		}

		int result = this.executeNonQuery(cmdText, qps);

		return result > 0;
	}

	public boolean isExsist(T entity) {
		Mtable meta = MtableManager.getMtable(entity.getClass());

		ISqlGenerator sqlGenerator = SqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());
		SqlStruct ss = sqlGenerator.generate(meta, SqlGeneratorType.IsExsit);

		QueryParameters qps = new QueryParameters();
		for (ParamField pf : ss.getParamFields()) {
			Column column = meta.getProperty(pf.getFieldName());
			qps.add(pf.getParamName(), column.getProperty().dbField(entity), column.getDataType().getJdbcType());
		}

		Object scalar = this.executeScalar(ss.getSqlString(), qps);
		int result = Integer.getInteger(scalar.toString()).intValue();

		return result > 0;
	}

	protected int execute(T entity, SqlGeneratorType operationType, boolean isimport) {
		Mtable meta = MtableManager.getMtable(entity.getClass());

		if (operationType == SqlGeneratorType.Insert) {
			entity.toNew();
		}

		ISqlGenerator sqlGenerator = SqlGeneratorFactory.create(this.dao.getDatabaseProperty().getType());

		SqlStruct ss = sqlGenerator.generate(meta, operationType);

		QueryParameters qps = new QueryParameters();

		for (ParamField pf : ss.getParamFields()) {
			Column column = meta.getProperty(pf.getFieldName());
			qps.add(pf.getParamName(), column.getProperty().dbField(entity), column.getDataType().getJdbcType());
		}

		int result = 0;
		if (operationType == SqlGeneratorType.Insert) {
			
			Object id = meta.getId(entity);
			if(meta.getAutoColumn() != null)
			{
				Object[] autos = this.dao.executeInsert(ss.getSqlString(), qps);
				
				id = autos[0];
				
				meta.getAutoColumn().getProperty().dbField(entity, id);
				
				result= autos.length;
			}else{
				result = this.executeNonQuery(ss.getSqlString(), qps);
			}


			for (TableSubs relation : meta.getSubs().values()) {
				Column fkColumn = relation.getFkColumn();

				@SuppressWarnings("unchecked")
				Iterable<T> subs = (Iterable<T>) relation.getSubs(entity);
				if (subs == null) {
					continue;
				}

				for (T sub : subs) {
					fkColumn.getProperty().dbField(sub, id);
				}
			}
			
			return 1;
			
		} else {
			result = this.executeNonQuery(ss.getSqlString(), qps);
		}
		
		return result;
	}

	public boolean save(T entity) {
		return this.save(entity, false);

	}

	public boolean save(T entity, boolean changedProperties) {
		EntityState es = entity.getEntityState();
		if (es == EntityState.New) {
			this.saveNew(entity);
		} else if (es == EntityState.Deleted) {
			this.saveDelete(entity);
		} else {
			this.saveUpdate(entity, changedProperties);
		}

		return true;
	}

	private void saveNew(T entity) {

		Mtable meta = MtableManager.getMtable(entity.getClass());

		for (TableCompositeOne relation : meta.getCompositeOnes().values()) {
			@SuppressWarnings("unchecked")
			T ref = (T) relation.getReference(entity);
			if (ref == null) {
				continue;
			}

			EntityState es = ref.getEntityState();
			if (es == EntityState.New) {

				this.saveNew(ref);

				this.syncCompositeOne(relation, entity, ref);

			} else if (es == EntityState.Deleted) {
				this.saveDelete(ref);
			} else {
				this.saveUpdate(ref, false);
			}
		}

		this.persistNew(entity);

		for (TableSubs relation : meta.getSubs().values()) {
			@SuppressWarnings("unchecked")
			Iterable<T> subs = (Iterable<T>) relation.getSubs(entity);
			if (subs == null) {
				continue;
			}

			for (T sub : subs) {
				this.saveNew(sub);
			}
		}
	}

	private void saveDelete(T entity) {

		Mtable meta = MtableManager.getMtable(entity.getClass());

		for (TableSubs relation : meta.getSubs().values()) {
			@SuppressWarnings("unchecked")
			Iterable<T> subs = (Iterable<T>) relation.getSubs(entity);
			if (subs == null) {
				continue;
			}

			for (T sub : subs) {
				this.saveDelete(sub);
			}
		}

		for (TableCompositeOne relation : meta.getCompositeOnes().values()) {
			@SuppressWarnings("unchecked")
			T ref = (T) relation.getReference(entity);
			if (ref != null) {
				this.delete(ref);
			}
		}

		this.delete(entity);
	}

	private void saveUpdate(T entity, boolean changedProperties) {

		Mtable meta = MtableManager.getMtable(entity.getClass());

		for (TableCompositeOne relation : meta.getCompositeOnes().values()) {
			@SuppressWarnings("unchecked")
			T ref = (T) relation.getReference(entity);
			if (ref == null) {
				continue;
			}

			EntityState es = ref.getEntityState();
			if (es == EntityState.New) {

				this.saveNew(ref);

				this.syncCompositeOne(relation, entity, ref);

			} else if (es == EntityState.Deleted) {
				this.saveDelete(ref);
			} else {
				this.saveUpdate(ref, changedProperties);
			}
		}

		if (!changedProperties) {
			this.persist(entity);
		} else {
			// String[] properties=entity.getChanges().keySet().toArray(new
			// String[]{});
			// persist(entity,properties);
		}

		for (TableSubs relation : meta.getSubs().values()) {
			@SuppressWarnings("unchecked")
			Iterable<T> subs = (Iterable<T>) relation.getSubs(entity);
			if (subs == null) {
				continue;
			}

			for (T sub : subs) {
				EntityState es = sub.getEntityState();
				if (es == EntityState.New) {
					this.saveNew(sub);
				} else if (es == EntityState.Deleted) {
					this.saveDelete(sub);
				} else {
					this.saveUpdate(sub, changedProperties);
				}
			}
		}
	}

	//新增时，compositeone的对象保存后，把id同步到引用他的实体上去
	private void syncCompositeOne(TableCompositeOne relation, T entity, T ref) {
		Mtable refMeta = (Mtable) relation.getToTable();
		
	    Column fkColumn = relation.getFkColumn();
		fkColumn.getProperty().field(entity, refMeta.getId(ref));
	}

	/* 批量插入，第二个参数为true时，使用数据库自增 */
	public void bulk(List<T> list, boolean isDbAuto) {

		if (list == null || list.size() == 0) {
			return;
		}

		Mtable meta = MtableManager.getMtable(list.get(0).getClass());

		List<Column> columns = new ArrayList<Column>();

		List<String> properties = new ArrayList<String>();
		for (Column column : meta.getColumns()) {
			if (column.isAuto() && !isDbAuto) {
				continue;
			}
			properties.add(column.getColumnName());
			columns.add(column);
		}

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ").append(meta.getTableName()).append(" (");
		builder.append(StringManager.join(",", properties));
		builder.append(") ");

		builder.append(" VALUES ");

		for (int i = 0; i < list.size(); i++) {

			Object item = list.get(i);

			List<String> values = new ArrayList<String>();

			for (Column column : columns) {
				Object propertyValue = column.getProperty().field(item);
				String value = column.getConvertor().toDbString(propertyValue);

				values.add(value);
			}

			builder.append("(").append(StringManager.join(",", values)).append(")");

			int lastIndex = list.size() - 1;
			if (i < lastIndex) {
				builder.append(",");
			} else {
				builder.append(";");
			}
			
			builder.append(StringManager.NewLine);
		}

		String cmdText = builder.toString();

		this.dao.executeUpdate(cmdText, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryList(Oql oql) {
		Query query = new Query(this.dao);
		TSet set = query.QuerySet(oql);

		set.connect();

		return (List<T>) set.Main.Items;
	}

	public DataTable queryTable(Oql oql) {

		Query query = new Query(this.dao);
		DataTable table = query.ExecuteTable(oql);

		return table;
	}

	public T queryFirst(Oql oql) {

		oql.setPaging(new Paging());
		oql.getPaging().setPageSize(1);
		oql.getPaging().setPageNo(1);

		List<T> items = this.queryList(oql);

		if (items == null || items.size() == 0) {
			return null;
		}

		return items.get(0);
	}

	public int queryCount(Oql oql) {
		TableParser parser = new TableParser();
		OqlStruct os = parser.parseMain(oql);

		QueryTable query = new QueryTable(dao);

		return query.getTotalCount(os, oql);
	}

	public Paging queryIndex(Oql oql) {
		if (oql.getTag() == null) {
			logger.error("QueryIndex查询，tag需要设置实体的Id");
		}
		Mtable meta = MtableManager.getMtable(oql.getEntityId());

		oql.setPaging(null);
		oql.setSelects(meta.getKeyColumn().getColumnName());

		TableParser parser = new TableParser();
		String cmdText = parser.parseTable(oql);

		dao.open();

		Paging paging = new Paging();

		int totalCount = 0;

		ResultSet rs = dao.executeResultSet(cmdText, oql.getParameters());

		try {
			while (rs.next()) {
				
				totalCount++;
				Object id = (Object) oql.getTag();

				if (ObjectManager.equals(rs.getObject(1), id)) {
					paging.setPageNo(totalCount);
				}
			}

			rs.close();
		} catch (Exception e) {
			throw new DataAccessException("IPersister.queryIndex", cmdText, oql.getParameters(), e);
		}

		paging.setTotalCount(totalCount);

		this.autoClose();

		return paging;
	}

	public ResultSet executeReader(String cmdText, QueryParameters qps) {
		this.open();
		return dao.executeResultSet(cmdText, qps);
	}

	public DataTable executeTable(String cmdText, QueryParameters qps) {

		this.open();
		DataTable table = dao.executeTable(cmdText, qps);
		this.autoClose();
		return table;
	}

	public int executeNonQuery(String cmdText, QueryParameters qps) {
		this.open();
		int count = dao.executeUpdate(cmdText, qps);
		this.autoClose();
		return count;
	}

	public Object[] executeInsert(String cmdText, QueryParameters qps) {
		this.open();
		Object[] count = dao.executeInsert(cmdText, qps);
		this.autoClose();
		return count;
	}

	public Object executeScalar(String cmdText, QueryParameters qps) {

		this.open();
		Object ret = dao.executeScalar(cmdText, qps);
		this.autoClose();
		return ret;

	}

	/* 返回单个int值的查询，count,max,min等 */
	public Integer executeInt(String cmdText, QueryParameters qps) {
		this.open();
		Integer i = this.dao.executeInt(cmdText, qps);
		this.autoClose();

		return i;
	}

	public void open() {
		dao.open();
		this.isClosed = false;
	}

	public void autoClose() {

		if (this.isAutoClose) {
			dao.close();
			this.isClosed = true;
		}
	}

	public void close() {

		if (!this.isClosed) {
			dao.close();
			this.isClosed = true;
		}
	}

	public boolean isClosed() {
		return isClosed;
	}

	public boolean isAutoClose() {
		return isAutoClose;
	}

	public void isAutoClose(boolean isAutoClose) {
		this.isAutoClose = isAutoClose;
	}
}
