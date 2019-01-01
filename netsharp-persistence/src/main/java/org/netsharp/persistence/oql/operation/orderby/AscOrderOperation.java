package org.netsharp.persistence.oql.operation.orderby;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.persistence.oql.operation.select.OperationType;

public class AscOrderOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	 return "ASC";
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }

        @Override
        public  OperationType getOperationType()
        {
        	return OperationType.Right;
        }
    }
