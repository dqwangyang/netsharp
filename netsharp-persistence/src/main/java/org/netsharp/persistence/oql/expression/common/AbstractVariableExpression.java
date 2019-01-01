package org.netsharp.persistence.oql.expression.common;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.persistence.oql.operation.select.OperationType;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.Oqlpart;
import org.netsharp.persistence.oql.parser.Oqlparts;
import org.netsharp.util.StringManager;

public abstract class AbstractVariableExpression extends Expression
{
    protected int nextOperationIndex(String source, int index)
    {
        int opIndex = source.length();

        for (Operation operation : this.getContext().OperationTokens.values())
        {
            String mayOperation = operation.getValue();
            if (operation.getOperationType()==OperationType.Right)
            {
                mayOperation = " " + mayOperation;
            }
            else if (operation.getOperationType() == OperationType.Left)
            {
                mayOperation = mayOperation+" ";
            }
            else
            {
                if (operation.isSpace())
                {
                    mayOperation = " " + mayOperation + " ";
                }
                else
                {
                    mayOperation = operation.getValue();
                }
            }

            int currentOpindex =StringManager.indexOf(source,mayOperation, index, operation.isIgnoreCase());

            if (currentOpindex < 0)
            {
                continue;
            }

            if (currentOpindex < opIndex)
            {
                opIndex = currentOpindex;
            }
        }

        if (opIndex < 0)
        {
            opIndex = source.length();
        }

        return opIndex;
    }

    protected FieldNode getRelationNode(String[] paths)
    {
        int index = 0;
        Mtable meta = MtableManager.getMtable(getOql().getEntityId());
        if (StringManager.equals(paths[0], meta.getCode(),true))
        {
            index = 1;
        }

        String[] realPaths = new String[paths.length - index];
        for (int i = index; i < paths.length; i++)
        {
            realPaths[i - index] = paths[i];
        }

        getOqlpart().FieldNode.add(realPaths);

        return getOqlpart().FieldNode.get(realPaths);
    }

    protected String joinRelationAndProperty(FieldNode relation, String property)
    {
        if (relation.Level == -1)
        {
            return getOqlpart().Mtable.getCode() + "." + property;
        }
        else
        {
            String fullProperty = relation.getPathName() + "." + property;

            if (this.getOqlpart().getOqlparts() == Oqlparts.Select)
            {
                fullProperty += " AS " + relation.getPathName() + "_" + property;
            }

            return fullProperty;
        }
    }

    protected String[] copyArrange(String[] source, int start, int end)
    {
        String[] dest = new String[end - start];

        for (int i = start; i < end; i++)
        {
            dest[i - start] = source[i];
        }

        return dest;
    }

    protected Oql getOql()
    {
    	return this.getContext().GetState(Oql.class.getSimpleName());
    }

    protected Oqlpart getOqlpart()
    {
    	return this.getContext().GetState(Oqlpart.class.getSimpleName());
    }
}
