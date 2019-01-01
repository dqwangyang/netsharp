package org.netsharp.persistence.oql.expression.where;

import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.util.StringManager;

    public class NullExpression extends Expression
    {
        @Override
        public  Boolean isParse()
        {
        	return false;
        }

        private static String keywords = "NULL";

        @Override
        public  Expression match(String source, int startIndex)
        {
            if (source.length() <= (startIndex + keywords.length() - 1))
            {
                return null;
            }

            int endIndex=startIndex+keywords.length();
            String maybe=source.substring(startIndex,endIndex ).toUpperCase();
            if (StringManager.equals(maybe,  keywords))
            {
                Expression exp = (Expression)newInstance();
                exp.Text = keywords;
                exp.OpenIndex = startIndex;
                exp.CloseIndex = startIndex + keywords.length() - 1;
                return exp;
            }

            return null;
        }
    }
