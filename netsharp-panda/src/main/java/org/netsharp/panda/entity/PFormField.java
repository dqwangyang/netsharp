package org.netsharp.panda.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

@Table(name="ui_pa_form_field",header="表单字段")
public class PFormField extends PInputField{

	private static final long serialVersionUID = 6445540432796311811L;
	
	@Column(name = "x",header="X坐标")
	protected BigDecimal x;
	
	@Column(name = "y",header="Y坐标")
    protected BigDecimal y;
    
	@Column(name = "height",header="高度")
    protected int height;
    
	@Column(name = "row_span",header="占用行")
    protected int rowSpan;
    
	@Column(name = "column_span",header="占用列")
    protected int columnSpan;
    
	@Column(name = "full_column",header="占所有列")
    protected boolean fullColumn;

    @Column(name="form_id",header="表单Id")
    private Integer formId;
    
    @JsonBackReference
    @Reference(foreignKey="formId")
    private PForm form;

    public BigDecimal getX(){
        return this.x;
    }
    public PFormField setX(BigDecimal x){
        this.x=x;
        return this;
    }
    public BigDecimal getY(){
        return this.y;
    }
    public PFormField setY(BigDecimal y){
        this.y=y;
        return this;
    }
    public int getHeight(){
        return this.height;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public int getRowSpan(){
        return this.rowSpan;
    }
    public PFormField setRowSpan(int rowSpan){
        this.rowSpan=rowSpan;
        return this;
    }
    public int getColumnSpan(){
        return this.columnSpan;
    }
    public PFormField setColumnSpan(int columnSpan){
        this.columnSpan=columnSpan;
        return this;
    }

	public boolean isFullColumn() {
		return fullColumn;
	}
	public void setFullColumn(boolean fullColumn) {
		this.fullColumn = fullColumn;
	}
	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer idForm) {
		this.formId = idForm;
	}
	public PForm getForm() {
		return form;
	}
	
	public void setForm(PForm form) {
		this.form = form;
		if(this.form==null){
			this.formId=null;
		}else{
			this.formId=this.form.getId();
		}
	}
}