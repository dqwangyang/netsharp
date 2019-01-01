package org.netsharp.persistence.oql.operation;

import org.netsharp.core.Oql;
import org.netsharp.persistence.oql.languangeEngine.EngineContext;
import org.netsharp.persistence.oql.languangeEngine.LaguangeStep;
import org.netsharp.persistence.oql.parser.Oqlpart;

public abstract class LanguangeStep extends LaguangeStep
{
    protected Oql getOql()
    {
    	 return getEngine().getContext().GetState(Oql.class.getSimpleName());
    }

    protected Oqlpart getOqlpart()
    {
    	EngineContext context= getEngine().getContext();
    	Oqlpart part=context.GetState(Oqlpart.class.getSimpleName());
    	
    	return part;
    }
}
