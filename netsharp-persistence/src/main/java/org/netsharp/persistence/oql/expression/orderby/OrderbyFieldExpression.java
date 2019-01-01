package org.netsharp.persistence.oql.expression.orderby;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.persistence.oql.expression.common.VariableExpression;
import org.netsharp.persistence.oql.parser.FieldNode;

public class OrderbyFieldExpression extends VariableExpression {

	@Override
	public Boolean isParse() {
		return true;
	}

	@Override
	public void parse() {
		 //普通的属性
        String[] ss = Text.split("\\.");
        if (ss.length == 1)
        {
            //无引用前缀
        	Mtable mtable=MtableManager.getMtable(this.getOql().getEntityId());
            this.setResult(mtable.getCode() + "." + mtable.getPropertyOrColumn(Text).getColumnName());
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
	public String generate() {

		return super.generate();
	}
	
	@Override
    protected String joinRelationAndProperty(FieldNode relation, String property)
    {
        if (relation.Level == -1)
        {
            return getOqlpart().Mtable.getCode() + "." + relation.Mtable.getPropertyOrColumn(property).getColumnName();
        }
        else
        {
            String fullProperty = relation.getPathName() + "." + relation.Mtable.getPropertyOrColumn(property).getColumnName();

            return fullProperty;
        }
    }
}
