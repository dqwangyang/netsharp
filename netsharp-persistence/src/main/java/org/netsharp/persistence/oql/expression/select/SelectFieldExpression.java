package org.netsharp.persistence.oql.expression.select;

import org.netsharp.persistence.oql.expression.common.VariableExpression;
import org.netsharp.persistence.oql.parser.FieldNode;

public class SelectFieldExpression extends VariableExpression {
	
	@Override
    protected String joinRelationAndProperty(FieldNode relation, String property)
    {
        if (relation.Level == -1)
        {
            return getOqlpart().Mtable.getCode() + "." + property;
        }
        else
        {
            String fullProperty = relation.getPathName() + "." + property;
            fullProperty += " AS " + relation.getPathName() + "_" + property;

            return fullProperty;
        }
    }
}
