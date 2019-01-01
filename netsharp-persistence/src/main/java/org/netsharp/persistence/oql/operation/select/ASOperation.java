package org.netsharp.persistence.oql.operation.select;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;

public class ASOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return "AS"; 
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }
    }
