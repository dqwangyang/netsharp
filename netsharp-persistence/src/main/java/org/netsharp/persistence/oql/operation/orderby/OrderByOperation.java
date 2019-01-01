package org.netsharp.persistence.oql.operation.orderby;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.persistence.oql.operation.select.OperationType;

/// <summary>
    /// 排序操作符
    /// </summary>
    public class OrderByOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return "Order BY";
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }

        @Override
        public  OperationType getOperationType()
        {
        	 return OperationType.Left;
        }
    }
