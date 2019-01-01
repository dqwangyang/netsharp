package org.netsharp.persistence.oql.operation.where;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;

    public class IsOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return "IS";
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }
    }
