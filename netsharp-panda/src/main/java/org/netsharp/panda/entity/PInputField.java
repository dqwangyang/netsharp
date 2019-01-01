package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;

public abstract class PInputField extends UiField {

	private static final long serialVersionUID = -4269384722003814274L;

	@Column(name = "group_name", header = "业务分组")
	private String groupName;
	
	@Column(name = "width", header = "宽度")
	private int width = 180;

	@Column(name = "formatter", size = 1000, header = "显示格式")
	private String formatter;

	@Column(name = "precision_", header = "数据精度")
	private Integer precision;

	@Column(name = "min_value", header = "最小值")
	private Integer minValue;

	@Column(name = "max_length", header = "最大长度")
	private Integer maxLength;

	@Column(name = "default_value", header = "默认值")
	private String defaultValue;

	@Column(name = "convertor", header = "转换器")
	private String convertor;

	@Column(name = "troika_codition",size = 500, header = "三驾马车条件")
	private String troikaCodition;

	@Column(name = "troika_validation", header = "三驾马车验证")
	private String troikaValidation;

	@Column(name = "troika_trigger",size = 500, header = "三驾马车触发器")
	private String troikaTrigger;

	@Column(name = "ref_filter", size = 500, header = "参照查询条件")
	private String refFilter;

	@Column(name = "readonly", header = "只读")
	private boolean readonly = false;
	
	@Column(name = "required", header = "必输")
	private boolean required = false;

	@Column(name = "not_persist", header = "非持久化字段")
	private boolean notPersist = false;

	@Column(name = "can_edit_is_visiable", header = "显示可编辑")
	private boolean canEditIsVisiable = false;

	@Column(name = "can_edit_header", header = "标题可编辑")
	private boolean canEditHeader = false;

	@Column(name = "can_edit_is_readonly", header = "只读可编辑")
	private boolean canEditIsReadOnly = false;

	@Column(name = "can_edit_is_required", header = "必输可编辑")
	private boolean canEditIsRequired = false;

	@Column(name = "print", header = "打印字段")
	private boolean print = false;

	@Column(name = "remember", header = "自动记忆")
	private boolean remember = false;

	public String getGroupName() {
		return this.groupName;
	}

	public PInputField setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public int getWidth() {
		return this.width;
	}

	public PInputField setWidth(int width) {
		this.width = width;
		return this;
	}

	public String getFormatter() {
		return formatter;
	}

	public PInputField setFormatter(String formatter) {
		this.formatter = formatter;
		return this;
	}

	public Integer getPrecision() {
		return this.precision;
	}

	public PInputField setPrecision(Integer precision) {
		this.precision = precision;
		return this;
	}

	public Integer getMaxLength() {
		return this.maxLength;
	}

	public PInputField setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
		return this;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public PInputField setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public boolean getCanEditIsVisiable() {
		return this.canEditIsVisiable;
	}

	public PInputField setCanEditIsVisiable(boolean canEditIsVisiable) {
		this.canEditIsVisiable = canEditIsVisiable;
		return this;
	}

	public boolean getCanEditHeader() {
		return this.canEditHeader;
	}

	public PInputField setCanEditHeader(boolean canEditHeader) {
		this.canEditHeader = canEditHeader;
		return this;
	}

	public boolean getCanEditIsReadOnly() {
		return this.canEditIsReadOnly;
	}

	public PInputField setCanEditIsReadOnly(boolean canEditIsReadOnly) {
		this.canEditIsReadOnly = canEditIsReadOnly;
		return this;
	}

	public boolean getCanEditIsRequired() {
		return this.canEditIsRequired;
	}

	public PInputField setCanEditIsRequired(boolean canEditIsRequired) {
		this.canEditIsRequired = canEditIsRequired;
		return this;
	}

	public String getConvertor() {
		return this.convertor;
	}

	public PInputField setConvertor(String convertor) {
		this.convertor = convertor;
		return this;
	}

	public String getTroikaCodition() {
		return this.troikaCodition;
	}

	public PInputField setTroikaCodition(String troikaCodition) {
		this.troikaCodition = troikaCodition;
		return this;
	}

	public String getTroikaValidation() {
		return this.troikaValidation;
	}

	public PInputField setTroikaValidation(String troikaValidation) {
		this.troikaValidation = troikaValidation;
		return this;
	}

	public String getTroikaTrigger() {
		return this.troikaTrigger;
	}

	public PInputField setTroikaTrigger(String troikaTrigger) {
		this.troikaTrigger = troikaTrigger;
		return this;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isNotPersist() {
		return notPersist;
	}

	public void setNotPersist(boolean notPersist) {
		this.notPersist = notPersist;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public String getRefFilter() {
		return refFilter;
	}

	public void setRefFilter(String refFilter) {
		this.refFilter = refFilter;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

}