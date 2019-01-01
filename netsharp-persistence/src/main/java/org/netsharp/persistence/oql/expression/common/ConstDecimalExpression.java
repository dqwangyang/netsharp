package org.netsharp.persistence.oql.expression.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;


public class ConstDecimalExpression extends Expression
{
    @Override
    public  Boolean isParse()
    {
    	 return false;
    }

    public static String[] Numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." };

    @Override
    public  Expression match(String source, int index)
    {
        int currentIndex = index;

        while (currentIndex < source.length())
        {
            String s = source.substring(currentIndex, currentIndex+1);
            
            if (this.contains(s))
            {
                currentIndex++;
            }
            else
            {
                break;
            }
        }

        if (currentIndex > index)
        {
            String text = source.substring(index, currentIndex);
            
            Pattern pattern = Pattern.compile(text); 
            Matcher matcher = pattern.matcher("\\.");
            
            if (matcher.groupCount()>1)
            {
                throw new OqlParseException(text + "中出现了两个小数点。");
            }

            Expression exp = (Expression)this.newInstance();
            exp.OpenIndex = index;
            exp.CloseIndex = currentIndex - 1;
            exp.Text = text;

            return exp;
        }
        else
        {
            return null;
        }
    }
    
    private boolean contains(String s){
    	
    	for(String item : Numbers){
    		if(item.equals(s)){
    			return true;
    		}
    	}
    	
    	return false;
    }
}
