package org.netsharp.persistence.oql.expression.select;

import org.netsharp.persistence.oql.expression.common.AbstractVariableExpression;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.util.StringManager;

public class ASExpresstion extends AbstractVariableExpression
    {
        @Override
        public  Boolean isParse()
        {
        	return false;
        }

        private static String keywords = "AS";

        @Override
        public  Expression match(String source, int startIndex)
        {
            if (source.length() <= (startIndex + keywords.length() - 1))
            {
                return null;
            }

            String maybe=source.substring(startIndex, startIndex+keywords.length()).toUpperCase();
            if (StringManager.equals(maybe, keywords))
            {
                int opIndex = nextOperationIndex(source, startIndex);
                //String text = source.substring(startIndex, opIndex - startIndex);
                String text = source.substring(startIndex, opIndex);

                Expression ex = (Expression)newInstance();
                ex.Text = text;
                ex.OpenIndex = startIndex;
                ex.CloseIndex = startIndex + keywords.length() - 1;

                return ex;
            }
            else
            {
                return null;
            }
        }
    }
