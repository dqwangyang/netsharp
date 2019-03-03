package org.netsharp.web;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.id.SnowflakeId;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.tree.TreeNodeState;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.util.ReflectManager;
import org.netsharp.web.dto.FieldNode;

public abstract class PlatformToolDetailPart extends DetailPart {

	private SnowflakeId id = new SnowflakeId();
	
	@SuppressWarnings("unchecked")
	public List<FieldNode> getFileds(String type) throws ClassNotFoundException{

		Class<?> typeClass = ReflectManager.getType(type);
		Field[] fields = ReflectManager.getDeclaredFields(typeClass);
		FieldNode node = null;
		List<FieldNode> rows = new ArrayList<FieldNode>();
		for (Field field : fields) {

			node = createFieldNode(field);
			if(node == null){
				
				continue;
			}
			rows.add(node);
		}
		
		//排序
		Collections.sort(rows, new SortByFiledName());
		return rows;
	}
	
	private FieldNode createFieldNode(Field field) throws ClassNotFoundException{

		Class<?> fieldClazz = field.getType();
		Exclusive exclusiveAnnotation = field.getAnnotation(Exclusive.class);
		// 常量直接跳过：private static final,非持久化字段跳过,List
		if (field.getModifiers() == 26 || exclusiveAnnotation != null || fieldClazz.isAssignableFrom(List.class)) {

			return null;
		}
		

		FieldNode node = new FieldNode();
		String icon = null;
		String text = null;
		String formatter = null;
		ControlTypes controlType = ControlTypes.TEXT_BOX;
		node = new FieldNode();
		Column columnAnnotation = field.getAnnotation(Column.class);
		if (columnAnnotation != null) {

			text = columnAnnotation.header();
		} else {
			text = field.getName();
		}

		Reference referenceAnnotation = field.getAnnotation(Reference.class);
		CompositeOne compositeOneAnnotation = field.getAnnotation(CompositeOne.class);
		Id idAnnotation = field.getAnnotation(Id.class);
		if (referenceAnnotation != null || compositeOneAnnotation != null) {
			
			node.setState(TreeNodeState.closed);
			node.getAttributes().setEntityId(field.getType().getName());

			if(referenceAnnotation != null){
				
				text = referenceAnnotation.header();
			}else if(compositeOneAnnotation != null){
				
				text = compositeOneAnnotation.header();
			}
			controlType = ControlTypes.REFERENCE_BOX;
		}else{
			
			if(field.getType().getName().equals(String.class.getName())){
				
				icon = "icon-string";
				controlType = ControlTypes.TEXT_BOX;
			}else if(field.getType().getName().equals(Date.class.getName())){
				
				icon = "icon-date";
				controlType = ControlTypes.DATE_BOX;
			}else if(field.getType().getName().equals(boolean.class.getName()) || field.getType().getName().equals(Boolean.class.getName())){
				
				icon = "icon-boolean";
				controlType = ControlTypes.CHECK_BOX;
			}else if(field.getType().getName().equals(int.class.getName()) || field.getType().getName().equals(Integer.class.getName())){
				
				icon = "icon-integer";
				controlType = ControlTypes.NUMBER_BOX;
			}else if(field.getType().getName().equals(Integer.class.getName()) || field.getType().getName().equals(Integer.class.getName())){
				
				icon = "icon-Integer";
				controlType = ControlTypes.NUMBER_BOX;
			}else if(field.getType().isEnum()){
				
				icon = "icon-enum";
				controlType = ControlTypes.ENUM_BOX;
				
				@SuppressWarnings("unchecked")
				Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>)Class.forName(field.getType().getName());
				formatter = EnumUtil.getColumnFormatter(enumClass);
				
			}else if(field.getType().getName().equals(BigDecimal.class.getName())){
				
				icon = "icon-decimal";
				controlType = ControlTypes.DECIMAL_BOX;
			}else if(idAnnotation != null){
				
				icon = "icon-key";
				controlType = ControlTypes.NUMBER_BOX;
			}
			
		}
		node.setId((Long)id.newId());
		node.setText(text);
		node.setIconCls(icon);
		node.getAttributes().setFormatter(formatter);
		node.getAttributes().setFiledName(field.getName());
		node.getAttributes().setControlType(controlType.getValue());
		node.getAttributes().setFiledType(field.getType().getSimpleName());
		return node;
	}

	@SuppressWarnings("rawtypes")
	class SortByFiledName implements Comparator {

		public int compare(Object o1, Object o2) {

			FieldNode s1 = (FieldNode) o1;
			FieldNode s2 = (FieldNode) o2;
			return s1.getAttributes().getFiledName().compareTo(s2.getAttributes().getFiledName());
		}
	}
	
	
	protected abstract String getMetaEntity(Long projectId);

	@Override
	protected void addJscript() {

		super.addJscript();
		String id = this.getRequest("id");
		String metaEntity = "";
		if(id != null){

			Long projectId = Long.parseLong(id);
			metaEntity = this.getMetaEntity(projectId);
		}
		this.addJscript("        " + getJsInstance() + ".context.metaEntity='" + metaEntity + "';", JscriptType.Header);
	}
}
