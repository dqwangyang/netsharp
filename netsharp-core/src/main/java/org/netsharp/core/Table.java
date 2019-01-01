package org.netsharp.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.id.IId;
import org.netsharp.core.property.IProperty;
import org.netsharp.util.ObjectManager;
import org.netsharp.util.SAXWriter;
import org.netsharp.util.StringManager;

public class Table<T extends IRow> extends Rows<T> implements ITable<T> {
	
	private static final long serialVersionUID = -5772632632655609310L;
	
	private ArrayList<Column> columns;
	private String entityId;
	private String tableName;
	private Set set;
	private String code;
	private String name;
	private String orderby;
	private int totalCount;
	private boolean isView;
	private boolean isConcurrency;
	private boolean isRefcheck;
	private Category category;
	private IId id;

	private Column keyColumn = null;
	private Column autoColumn = null;
	private HashMap<String, TableSubs> subs = new HashMap<String, TableSubs>();
	private HashMap<String, TableReference> references = new HashMap<String, TableReference>();
	private HashMap<String, TableCompositeOne> compositeOnes = new HashMap<String, TableCompositeOne>();

	protected Table() {

		this.columns = new ArrayList<Column>();
		this.table = this;
	}

	public Column getProperty(String propertyName) {

		for (Column column : this.columns) {

			if (column.getPropertyName().equalsIgnoreCase(propertyName)) {
				return column;
			}
		}

		logger.debug(this.getEntityId() + "." + propertyName + " 列信息未能获取到！");

		return null;
	}

	public Column getColumn(String columnName) {
		for (Column column : this.columns) {

			if (column.getColumnName().equalsIgnoreCase(columnName)) {
				return column;
			}
		}

		return null;
	}
	
	public Column getPropertyOrColumn(String name){
		
		for (Column column : this.columns) {

			if (column.getColumnName().equalsIgnoreCase(name)) {
				return column;
			}
			
			if (column.getPropertyName().equalsIgnoreCase(name)) {
				return column;
			}
		}

		return null;
	}

	public T byUid(Object... uid) {

		for (T row : this.innerList) {

			if (this.idequals(row, uid))

				return row;
		}

		return null;
	}

	public Object getId(Object obj) {
		return this.getKeyColumn().getProperty().field(obj);
	}

	public void setId(Object obj, Object id) {
		this.getKeyColumn().getProperty().field(obj, id);
	}

	private boolean idequals(IRow row, Object uid) {

		Column c = this.getKeyColumn();

		Object obj = c.getProperty().field(row);

		return ObjectManager.equals(uid, obj);
	}

	public ArrayList<Column> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public ITable<T> clone(boolean isData) {
		Table<T> table = new Table<T>();

		this.clone(table);

		if (isData) {
			for (IRow row : this) {
				IRow newRow = table.newItem();

				for (Column column : table.getColumns()) {
					IProperty property = column.getProperty();

					property.field(newRow, property.field(row));
				}
			}
		}

		return table;
	}

	public void clone(ITable<?> table) {
		table.setEntityId(entityId);
		table.setTableName(tableName);
		table.setCode(code);
		table.setName(name);
		table.setOrderby(orderby);
		table.setIsView(getIsView());
		table.setIsConcurrency(getIsConcurrency());
		table.setIsConcurrency(getIsConcurrency());

		table.getColumns().addAll(this.getColumns());
		table.setKeyColumn(this.keyColumn);
		table.setAutoColumn(autoColumn);

		Table<?> t = (Table<?>) table;
		t.setType(this.getType());

		for (TableReference reference : references.values()) {
			TableReference newObject = reference.clone();
			newObject.setFromTable(table);
			newObject.setToTable(null);
			table.getReferences().put(newObject.getReferenceCode(), newObject);
		}

		for (TableSubs reference : subs.values()) {
			TableSubs newObject = reference.clone();
			newObject.setFromTable(table);
			newObject.setToTable(null);
			table.getSubs().put(newObject.getSubCode(), newObject);
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Column getKeyColumn() {
		return this.keyColumn;
	}

	public void setKeyColumn(Column keyColumn) {
		this.keyColumn = keyColumn;
	}

	public HashMap<String, TableSubs> getSubs() {
		return subs;
	}

	public HashMap<String, TableReference> getReferences() {
		return references;
	}
	
	public HashMap<String, TableCompositeOne> getCompositeOnes() {
		return compositeOnes;
	}

	public TableSubs getSub(String subCode) {
		 for(TableSubs tc : this.getSubs().values()){
        	if(StringManager.equals(tc.getSubCode(), subCode,true)){
        		return tc;
        	}
	      }
		 return null;
	}

	public TableReference getReference(String refCode) {
		
        for(TableReference ref : getReferences().values()){
        	if(StringManager.equals(ref.getReferenceCode(), refCode,true)){
        		return ref;
        	}
        }
        
        return null;
	}
	
	public TableCompositeOne getCompositeOne(String code) {
        for(TableCompositeOne ref : getCompositeOnes().values()){
        	if(StringManager.equals(ref.getReferenceCode(), code,true)){
        		return ref;
        	}
        }
        return null;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		if(StringManager.isNullOrEmpty(this.name)){
			return this.code;
		}
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public ITable<T> getTable() {
		return this;
	}

	public boolean getIsRefcheck() {
		return isRefcheck;
	}

	public void setIsRefcheck(boolean isRefcheck) {
		this.isRefcheck = isRefcheck;
	}

	public boolean getIsView() {
		return isView;
	}

	public void setIsView(boolean isView) {
		this.isView = isView;
	}

	public boolean getIsConcurrency() {
		return isConcurrency;
	}

	public void setIsConcurrency(boolean isConcurrency) {
		this.isConcurrency = isConcurrency;
	}

	public Column getAutoColumn() {
		return autoColumn;
	}

	public void setAutoColumn(Column autoColumn) {
		this.autoColumn = autoColumn;
	}
	
	public void write(SAXWriter writer){
		
		writer.startElement("table");
		writer.addAttribute("tableName", this.getTableName());
		
		//
		writer.startElement("columns");
		for(Column column : this.getColumns()){
			
			writer.startElement("column");
			
			writer.addAttribute("getColumnName", column.getColumnName());
			writer.addAttribute("typeTypeName", column.getColumnTypeName());
			writer.addAttribute("columnType",String.valueOf(column.getColumnType()));
			writer.addAttribute("type", column.getType().getName());

			writer.endElement("column");
		}
		writer.endElement("columns");
		
		//
		writer.startElement("rows");
		for(IRow row : this){
		
			writer.startElement("row");
			
			for(int i=0;i<this.getColumns().size();i++){
				Column column = this.getColumns().get(i);
				Object value=row.get(column.getColumnName());
				ITypeConvertor convertor=column.getConvertor();
				if(convertor==null){
					System.out.println(this.getTableName()+"["+column.getColumnName()+"]"+column.getType().getName());
				}
				String v=convertor.toXml(value);
				
				writer.addAttribute("c"+i, v);
			}
			writer.endElement("row");
		}
		
		writer.endElement("rows");
		writer.endElement("table");
	}

	public IId getId() {
		return id;
	}

	public void setId(IId id) {
		this.id = id;
	}
}
