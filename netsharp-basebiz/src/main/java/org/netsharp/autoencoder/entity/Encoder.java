package org.netsharp.autoencoder.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name = "sys_bd_code", header = "编码注册表")
public class Encoder extends BizEntity implements Serializable {

	private static final long serialVersionUID = -2681724846750430425L;

	@Column(name = "bill_type",header="单据类型")
	private String billType;

	@Column(name = "date_format",header="日期格式")
	private String dateFormat;

	@Column(name = "total_length",header="总长度")
	private Integer totalLength;

	@Column(name = "sequence_length",header="序列长度")
	private Integer sequenceLength;

	@Column(name = "entity_type",header="实体类型",size=200)
	private String entityType;

	@Column(name = "expand_classname",header="扩展规则类", size = 200)
	private String expandClassName;

	@Column(name = "is_polishing",header="补齐")
	private Boolean isPolishing;

	@Subs(subType = EncoderRule.class, foreignKey = "encoderId", header="编码规则明细")
	private List<EncoderRule> encoderRules = new ArrayList<EncoderRule>();

	@Subs(subType = EncoderRecords.class, foreignKey = "encoderId", header="编码规则记录明细")
	private List<EncoderRecords> encoderRecords = new ArrayList<EncoderRecords>();

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

	public Integer getSequenceLength() {
		return sequenceLength;
	}

	public void setSequenceLength(Integer sequenceLength) {
		this.sequenceLength = sequenceLength;
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

	public Boolean getIsPolishing() {
		return isPolishing;
	}

	public void setIsPolishing(Boolean isPolishing) {
		this.isPolishing = isPolishing;
	}

	public List<EncoderRule> getEncoderRules() {
		return encoderRules;
	}

	public void setEncoderRules(List<EncoderRule> encoderRules) {
		this.encoderRules = encoderRules;
	}

	public List<EncoderRecords> getEncoderRecords() {
		return encoderRecords;
	}

	public void setEncoderRecords(List<EncoderRecords> encoderRecords) {
		this.encoderRecords = encoderRecords;
	}

}
