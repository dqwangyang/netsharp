package org.netsharp.persistence.oql.operation.select;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.util.StringManager;

public class FieldJoinOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	return ","; 
        }

        @Override
        public  Boolean isSpace()
        {
        	return false;
        }

        @Override
        public  String getLeftSplit() { return StringManager.NewLine; }

        @Override
        public  String getRightSplit() { return ""; }

        @Override
        public  int getPriority()
        {
        	return 10;
        }
    }
