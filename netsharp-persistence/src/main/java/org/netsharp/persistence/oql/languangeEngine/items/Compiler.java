package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.operation.LanguangeStep;

public class Compiler extends LanguangeStep
{
    @Override
    public  void execute()
    {
        doCompile(getEngine().getContext().ExpressionTree);
    }

    private void doCompile(Expression expression)
    {
        if (expression.getLeft() != null)
        {
            doCompile(expression.getLeft());
        }

        if (expression.getRight() != null)
        {
            doCompile(expression.getRight());
        }

        if (!expression.hasChild())
        {
            if (expression.isParse())
            {
                expression.parse();
            }
        }
    }
}
