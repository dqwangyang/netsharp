package org.netsharp.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.core.id.IId;
import org.netsharp.core.property.EntityProperty;
import org.netsharp.core.property.GuidProperty;
import org.netsharp.core.property.JavaEnumProperty;
import org.netsharp.entity.DataType;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class MtableParser {

	private static final Log logger = LogFactory.getLog(MtableParser.class);

	//
	public static Mtable parse(Class<?> type) {

		Mtable mtable = new Mtable();
		mtable.setType(type);

		Table tableMap = type.getAnnotation(Table.class);
		if (tableMap == null) {
			logger.error("未能加载类型" + type.getName() + "的配置信息");
			// throw new NetsharpException("未能加载类型" + type.getName() + "的配置信息");
			return mtable;
		}

		mtable.setEntityId(type.getName());
		mtable.setTableName(tableMap.name());
		mtable.setCode(type.getSimpleName());
		mtable.setOrderby(tableMap.orderBy());
		mtable.setId((IId) ReflectManager.newInstance(tableMap.idType()));
		mtable.setIsView(tableMap.isView());
		mtable.setName(tableMap.header());
		MtableManager.getSet().getTables().add(mtable);

		Field[] fields = ReflectManager.getDeclaredFields(type);

		for (Field field : fields) {

			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			field.setAccessible(true);

			//
			Reference refObject = field.getAnnotation(Reference.class);
			if (refObject != null) {

				parseReference(mtable, refObject, field);
				continue;
			}

			//
			CompositeOne compositeOneMeta = field.getAnnotation(CompositeOne.class);
			if (compositeOneMeta != null) {

				parseCompositeOne(mtable, compositeOneMeta, field);
				continue;
			}

			//
			Subs subObject = field.getAnnotation(Subs.class);
			if (subObject != null) {
				parseComposite(mtable, subObject, field);
				continue;
			}

			//
			Exclusive exclusive = field.getAnnotation(Exclusive.class);
			if (exclusive == null) {
				parseProperty(mtable, field);
			}

			//
			org.netsharp.core.annotations.Category category = field.getAnnotation(org.netsharp.core.annotations.Category.class);
			if (category != null) {
				parseCategory(mtable, field, category);
			}
		}

		return mtable;
	}

	private static void parseProperty(Mtable mtable, Field field) {

		String fieldName = field.getName();
		DataType dataType = DatatypeManager.ByJavaType(field.getType());

		Column column = new Column();
		{
			column.setColumnName(fieldName);
			column.setFieldName(fieldName);
			column.setType(field.getType());
			column.setPropertyName(fieldName);
			column.setDataType(dataType);
		}

		org.netsharp.core.annotations.Column columnMeta = field.getAnnotation(org.netsharp.core.annotations.Column.class);

		if (columnMeta != null) {

			column.setUnique(columnMeta.unique());
			column.setRequired(columnMeta.required());
			column.setMobiles(columnMeta.mobiles());
			column.setSensitive(columnMeta.sensitive());

			if (!StringManager.isNullOrEmpty(columnMeta.name())) {
				column.setColumnName(columnMeta.name());
				column.setIsNameEquals(false);
			}
			
			if (!StringManager.isNullOrEmpty(columnMeta.groupName())) {
				column.setGroupName(columnMeta.groupName());
			}

			if (!StringManager.isNullOrEmpty(columnMeta.header())) {
				column.setHeader(columnMeta.header());
			}

			if (!StringManager.isNullOrEmpty(columnMeta.memoto())) {
				column.setMemoto(columnMeta.memoto());
			}

			if (!StringManager.isNullOrEmpty(columnMeta.typeName())) {

				dataType = DatatypeManager.ByCode(columnMeta.typeName());
				if (dataType == null) {
					logger.error(mtable.getEntityId() + "[\"" + field.getName() + "\"]的dataType配置不正确！");
				} else {
					column.setDataType(dataType);
				}
			}

			if (columnMeta.size() > 0) {
				column.setSize(columnMeta.size());
			}

			if (columnMeta.precition() > 0) {
				column.setPrecistion(columnMeta.size());
			}
		}

		dataType = column.getDataType();
		if (dataType.isSize() && column.getSize() == null) {
			column.setSize(dataType.getSizeDb());
		}

		if (dataType.isPrecision() && column.getPrecistion() == null) {
			column.setPrecistion(dataType.getPrecisionDb());
		}

		org.netsharp.core.annotations.Id idColumn = field.getAnnotation(org.netsharp.core.annotations.Id.class);
		if (idColumn != null) {
			column.setIsPrimaryKey(true);
			mtable.setKeyColumn(column);
		}
		
		EntityProperty property = null;

		Class<?> type = field.getType();

		if (type == UUID.class) {
			property = new GuidProperty();
		} else if (type.isEnum()) {
			property = new JavaEnumProperty();
		} else {
			property = new EntityProperty();
		}

		property.setPropertyName(fieldName);
		property.setType(type);
		property.setField(field);

		column.setProperty(property);

		Auto auto = field.getAnnotation(Auto.class);
		if (auto != null) {
			column.setAuto(true);
			mtable.setAutoColumn(column);
		}

		mtable.getColumns().add(column);
	}

	private static void parseCompositeOne(Mtable mtable, CompositeOne meta, Field field) {

		TableCompositeOne ref = new TableCompositeOne();

		ref.setFilter("");
		ref.setFromEntityId(mtable.getEntityId());
		ref.setToEntityId(field.getType().getName());
		ref.setForeignProperty(meta.foreignKey());
//		ref.setIsNocopy(meta.isNocopy());
		ref.setReferenceCode(field.getName());
		ref.setReferenceName(meta.header());
		ref.setFromTable(mtable);
		ref.setField(field);
		ref.setConstraint(meta.constraint());
		
		if(StringManager.isNullOrEmpty( meta.primaryKey() )){
			ref.setPriamyKey("id");
		}else {
			ref.setPriamyKey(meta.primaryKey());
		}
		
		if (!StringManager.isNullOrEmpty(meta.groupName())) {
			ref.setGroupName(meta.groupName());
		}

		mtable.getCompositeOnes().put(ref.getReferenceCode(), ref);
	}

	private static void parseReference(Mtable mtable, Reference refObject, Field field) {

		TableReference ref = new TableReference();

		ref.setFilter("");
		ref.setFromEntityId(mtable.getEntityId());
		ref.setToEntityId(field.getType().getName());
		ref.setForeignProperty(refObject.foreignKey());
//		ref.setIsNocopy(refObject.isNocopy());
		ref.setReferenceCode(field.getName());
		ref.setReferenceName(refObject.header());
		ref.setFromTable(mtable);
		ref.setField(field);
		ref.setConstraint(refObject.constraint());
		// ref.IsAuditEdit=refObject.isNocopy()
		// ref.IsQuery=refObject.isNocopy()
		
		if(StringManager.isNullOrEmpty( refObject.primaryKey() )){
			ref.setPriamyKey("id");
		}else {
			ref.setPriamyKey(refObject.primaryKey());
		}
		
		if (!StringManager.isNullOrEmpty(refObject.groupName())) {
			ref.setGroupName(refObject.groupName());
		}

		mtable.getReferences().put(ref.getReferenceCode(), ref);
	}

	private static void parseComposite(Mtable mtable, Subs subObject, Field field) {

		TableSubs tableSubs = new TableSubs();
		tableSubs.setFromEntityId(mtable.getEntityId());
		tableSubs.setToEntityId(subObject.subType().getName());
		tableSubs.setSubCode(field.getName());
		tableSubs.setSubName(subObject.header());
//		tableSubs.setReferenceCode(subObject.referenceCode());
//		tableSubs.setReferenceName(subObject.referenceName());
		tableSubs.setFromTable(mtable);
		tableSubs.setForeignProperty(subObject.foreignKey());
		
		if(!StringManager.isNullOrEmpty(subObject.primaryKey())) {
			tableSubs.setPriamyKey(subObject.primaryKey());
		}else {
			tableSubs.setPriamyKey("id");
		}
		
		tableSubs.setField(field);
		
		if (!StringManager.isNullOrEmpty(subObject.groupName())) {
			tableSubs.setGroupName(subObject.groupName());
		}

		mtable.getSubs().put(tableSubs.getSubCode(), tableSubs);
	}

	private static void parseCategory(Mtable mtable, Field field, org.netsharp.core.annotations.Category meta) {

		Category category = mtable.getCategory();
		if (category != null) {
			throw new NetsharpException("分类实体设置重复！" + mtable.getEntityId() + "[" + category.getPropertyName() + "]与[" + field.getName() + "]");
		}

		category = new Category();
		{
			category.setPropertyName(field.getName());
			category.setName(meta.name());
			category.setCode(meta.code());
			category.setPathCode(meta.pathCode());
			category.setPathName(meta.pathName());
			category.setIdtype(meta.idtype());
			category.setMtable(mtable);
			category.setLeafName(meta.leafName());
			category.setLeafValue(meta.leafValue());
		}

		mtable.setCategory(category);
	}
}
