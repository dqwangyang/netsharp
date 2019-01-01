package org.netsharp.autoencoder.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_bd_code_rule",header="编码规则明细")
public class EncoderRule extends Entity implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7672739932390712534L;

	@Column(name = "row_num",header="行号")
	private Integer rowNum;
	
	@Column(name = "encoder_id",header="行号")
	private Integer encoderId;
	
    @Reference(foreignKey="encoderId")
    @JsonIgnore
	private Encoder encoder;

	@Column(name = "row_type", typeName = "char", defaultValue = "1", size = 1,header="类别 A : 固定文本, B：日期 , C：序列")
	private EncoderType rowType;
	
	@Column(name = "rule_format",header="格式")
	private String ruleFormat;
	
	@Column(name = "rule_detail",header="内容")
	private String ruleDetail;
	
	@Column(name = "rule_length",header="长度")
	private Integer ruleLength;

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getEncoderId() {
		return encoderId;
	}

	public void setEncoderId(Integer encoderId) {
		this.encoderId = encoderId;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	public EncoderType getRowType() {
		return rowType;
	}

	public void setRowType(EncoderType rowType) {
		this.rowType = rowType;
	}

	public String getRuleFormat() {
		return ruleFormat;
	}

	public void setRuleFormat(String ruleFormat) {
		this.ruleFormat = ruleFormat;
	}

	public String getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(String ruleDetail) {
		this.ruleDetail = ruleDetail;
		this.setRuleLength(ruleDetail.length());
	}

	public Integer getRuleLength() {
		return ruleLength;
	}

	public void setRuleLength(Integer ruleLength) {
		this.ruleLength = ruleLength;
	}
	
	
		
}
