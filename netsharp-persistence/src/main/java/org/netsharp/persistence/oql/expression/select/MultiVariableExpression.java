package org.netsharp.persistence.oql.expression.select;

import java.util.ArrayList;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.expression.common.AbstractVariableExpression;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.util.StringManager;


public class MultiVariableExpression extends AbstractVariableExpression
{
    @Override
    public  Expression match(String source, int index)
    {
        int opIndex = nextOperationIndex(source, index);
        String text = source.substring(index, opIndex);

        if (text.contains("{") && text.endsWith("}"))
        {
            Expression exp = (Expression)this.newInstance();
            exp.Text = text.trim();
            exp.OpenIndex = index;
            exp.CloseIndex = index + exp.Text.length() - 1;

            return exp;
        }
        else if (text.contains("{"))
        {
            opIndex = source.indexOf("}", opIndex);
            text = source.substring(index, opIndex + 1);
            if (opIndex >= 0)
            {
                Expression exp = (Expression)this.newInstance();
                exp.Text = text.trim();
                exp.OpenIndex = index;
                exp.CloseIndex = index + exp.Text.length() - 1;

                return exp;
            }
            else
            {
                throw new OqlParseException("解析发现未闭合的{}");
            }
        }

        return null;
    }

    @Override
    public  void parse()
    {
        ArrayList<String> results = new ArrayList<String>();

        int index = Text.indexOf('{');

        //
        String objPath = Text.substring(0, index - 1);
        FieldNode relation = getRelationNode(objPath.split("\\."));

        //
        //String fieldsStr = Text.substring(index + 1, Text.length() - index - 2);
        String fieldsStr = Text.substring(index + 1, Text.length() - 1);
        for (String property : fieldsStr.split(","))
        {
            results.add(joinRelationAndProperty(relation, property.trim()));

            relation.addSub(property.trim());
        }

        this.setResult(StringManager.join(",", results));
    }
}
