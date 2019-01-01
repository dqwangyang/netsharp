package org.netsharp.web.dto;

import org.netsharp.panda.controls.ControlTypes;


public class FieldNodeAttributes {

	private String entityId;

	private String filedName;
	
	private String filedType;
	
	private String formatter;
	
	private Integer referenceId;
	
	private String referenceName;
	
	private int controlType = ControlTypes.TEXT_BOX.getValue();

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

	public int getControlType() {
		return controlType;
	}

	public void setControlType(int controlType) {
		this.controlType = controlType;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	
}
