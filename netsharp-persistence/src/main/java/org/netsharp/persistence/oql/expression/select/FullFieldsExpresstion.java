package org.netsharp.persistence.oql.expression.select;

import java.util.ArrayList;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.expression.common.AbstractVariableExpression;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.util.StringManager;


public class FullFieldsExpresstion extends AbstractVariableExpression
{
    private static String keywords = "*";

    @Override
    public Expression match(String source, int index)
    {
    	while(true){
    		if(source.charAt(index)==' '){
    			index++;
    		}
    		else{
    			break;
    		}
    	}
        int opIndex = nextOperationIndex(source, index);
        String text = source.substring(index, opIndex);

        String trim = text.trim();

        if (StringManager.equals(trim , keywords))
        {
            throw new OqlParseException("*前必须加上所属对象的引用，如SalesOrder.*");
        }

        if (trim.endsWith("."))
        {
            for (int i = opIndex; i < source.length(); i++)
            {
                String chari = source.substring(i,i+1);

                if (chari.equals(" "))
                {
                    continue;
                }
                else if (chari.equals(keywords) )
                {
                    opIndex = i;

                    text = text + keywords;

                    Expression ex = (Expression)newInstance();
                    ex.Text = text;
                    ex.OpenIndex = index;
                    ex.CloseIndex = opIndex;
                    return ex;
                }
                else
                {
                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public  void parse()
    {
        ArrayList<String> results = new ArrayList<String>();

        //
        String objPath = Text.replace(".*", "");

        FieldNode relation = getRelationNode(objPath.split("\\."));

        //
        Mtable meta = relation.Mtable;
        for (Column column : meta.getColumns())
        {
            results.add(joinRelationAndProperty(relation, column.getSelectName()));

            relation.addSub(column.getColumnName());
        }
        
        this.setResult(StringManager.join(",", results));
    }
}
