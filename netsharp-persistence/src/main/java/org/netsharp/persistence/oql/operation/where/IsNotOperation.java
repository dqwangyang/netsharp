package org.netsharp.persistence.oql.operation.where;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;

    public class IsNotOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return "IS NOT";
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }
    }
