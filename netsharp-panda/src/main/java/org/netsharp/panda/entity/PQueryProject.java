package org.netsharp.panda.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.QueryProjectType;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

@Table(name="ui_pa_query_project",header="查询方案")
public class PQueryProject extends ResourceBizEntity{

	private static final long serialVersionUID = -1085379626518870899L;
	
	@Column(name="column_count",header="栏目数")
	private int columnCount = 3;
	
	@Column(name="label_width",header="标题宽度")
    private int labelWidth  = 100;
	
	@Column(name="type",header="类型")
    private QueryProjectType type  = QueryProjectType.SIMPLE;
	
    @Subs(subType=PQueryItem.class,foreignKey="queryProjectId",header="查询项目")
    protected List<PQueryItem> queryItems;    
    
    public List<PQueryItem> getQueryItems(){
        if(this.queryItems==null){
            this.queryItems=new ArrayList<PQueryItem>();
        }
        return this.queryItems;
    }

	public void setQueryItems(List<PQueryItem> queryItems) {
		this.queryItems = queryItems;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public int getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}

	public QueryProjectType getType() {
		return type;
	}

	public void setType(QueryProjectType type) {
		this.type = type;
	}
}