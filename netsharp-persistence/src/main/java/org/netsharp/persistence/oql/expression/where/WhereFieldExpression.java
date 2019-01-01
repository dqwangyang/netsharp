package org.netsharp.persistence.oql.expression.where;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.expression.common.VariableExpression;
import org.netsharp.persistence.oql.parser.FieldNode;

public class WhereFieldExpression extends VariableExpression {
	
    @Override
    public void parse()
    {
        //普通的属性
        String[] ss = Text.split("\\.");
        if (ss.length == 1)
        {
            //无引用前缀
        	Mtable mtable=MtableManager.getMtable(this.getOql().getEntityId());
        	Column column = mtable.getPropertyOrColumn(this.Text);
        	if(column == null){
        		throw new OqlParseException("解析列异常:"+mtable.getEntityId()+"["+this.Text+"]");
        	}
            this.setResult(mtable.getCode() + "." + column.getColumnName());
        }
        else
        {
            //有引用前缀
            String[] paths = copyArrange(ss, 0, ss.length - 1);
            FieldNode relation = getRelationNode(paths);
            
            String res=joinRelationAndProperty(relation, ss[ss.length - 1]);
            this.setResult(res);
        }

        getRelationNode(ss);
    }
	
	@Override
    protected String joinRelationAndProperty(FieldNode relation, String property)
    {
		Column column = relation.Mtable.getPropertyOrColumn(property);
		
        if (relation.Level == -1)
        {
            return getOqlpart().Mtable.getCode() + "." + column.getColumnName();
        }
        else
        {
            String fullProperty = relation.getPathName() + "." + column.getColumnName();

            return fullProperty;
        }
    }
}
