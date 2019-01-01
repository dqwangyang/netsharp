package org.netsharp.autoencoder.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "sys_bd_code_records", header = "编码记录表")
public class EncoderRecords extends Entity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5355680291278605945L;

	@Column(name = "encoder_id",header="编码对象id")
	private Integer encoderId;

	@Column(name = "bill_type",header="单据类型")
	private String billType;

	@Reference(foreignKey = "encoderId")
	@JsonIgnore
	private Encoder encoder;

	@Column(name = "entity_type",header="实体类型",size=200)
	private String entityType;

	@Column(name = "fixation_type",header="固定文本")
	private String fixationType;
	//
	@Column(name = "date_format", defaultValue = "yyyyMM",header="日期格式")
	private String dateFormat = "yyyyMM";

	@Column(name = "current_index", defaultValue = "0",header="当前序号")
	private Integer currentIndex = 0;

	@Column(name = "index_length", defaultValue = "6",header="序号长度")
	private Integer indexLength = 6;

	@Column(name = "is_valid", defaultValue = "1",header="有效")
	private Boolean isValid = true;

	@Column(name = "expand_classname", size = 200,header="扩展规则类")
	private String expandClassName;

	public Integer getEncoderId() {
		return encoderId;
	}

	public void setEncoderId(Integer encoderId) {
		this.encoderId = encoderId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getFixationType() {
		return fixationType;
	}

	public void setFixationType(String fixationType) {
		this.fixationType = fixationType;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	public Integer getIndexLength() {
		return indexLength;
	}

	public void setIndexLength(Integer indexLength) {
		this.indexLength = indexLength;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getExpandClassName() {
		return expandClassName;
	}

	public void setExpandClassName(String expandClassName) {
		this.expandClassName = expandClassName;
	}

}
