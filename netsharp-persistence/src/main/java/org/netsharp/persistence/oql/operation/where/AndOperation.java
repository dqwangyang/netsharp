package org.netsharp.persistence.oql.operation.where;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.util.StringManager;

    public class AndOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return "AND"; 
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }

        @Override
        public  String getLeftSplit()
        { 
           return StringManager.NewLine;
        }

        @Override
        public  String getRightSplit ()
        { 
        	 return " ";
        }

        @Override
        public  int getPriority()
        {
        	return 10;
        }
    }
