package org.netsharp.persistence.oql.expression.where;

import org.netsharp.persistence.oql.expression.common.AbstractVariableExpression;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;

public class ParameterExpression extends AbstractVariableExpression
    {
        @Override
        public  Boolean isParse()
        {
        	return false;
        }

        private static String keywords = "?";

        @Override
        public Expression match(String source, int index)
        {
            if (source.length() <= index)
            {
                return null;
            }

            if (source.substring(index,index+1).equals(keywords) )
            {
                int opIndex = this.nextOperationIndex(source, index);

                Expression exp = (Expression)this.newInstance();
                exp.Text = source.substring(index, opIndex);
                exp.OpenIndex = index;
                exp.CloseIndex = index + exp.Text.length() - 1;

                return exp;
            }

            return null;
        }
    }
