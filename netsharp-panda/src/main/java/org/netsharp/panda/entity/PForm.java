package org.netsharp.panda.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.resourcenode.entity.ResourceNode;

@Table(name="ui_pa_form",header="表单")
public class PForm extends UiDescription{

	private static final long serialVersionUID = 1668999229984510352L;
	
	@Column(name = "column_count",header="每行列数")
	protected int columnCount = 3;
	
	
	@Column(name = "label_width",header="标题宽度")
	protected int labelWidth = 120;
	
	
    //表单字段
    @Subs(subType=PFormField.class,foreignKey="formId",header="表单字段")
	private List<PFormField> fields=new ArrayList<PFormField>();

    public int getColumnCount(){
        return this.columnCount;
    }
    public PForm setColumnCount(int columnCount){
        this.columnCount=columnCount;
        return this;
    }

	public List<PFormField> getFields() {
		return fields;
	}

	public void setFields(List<PFormField> fields) {
		this.fields = fields;
	}
	public PForm(){}
	
	public PForm(ResourceNode node,String name)
	{
		this.toNew();
		this.setResourceNode(node);
		this.setName(name);
	}
	public int getLabelWidth() {
		return labelWidth;
	}
	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}
}