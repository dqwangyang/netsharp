package org.netsharp.persistence.oql.boundary;

import org.netsharp.persistence.oql.expression.common.Boundary;

    public class SQuotaBoundary extends Boundary
    {
        @Override
        public  Boolean isParse()
        {
        	return false;
        }

        @Override
        public  String openToken()
        {
        	return "("; 
        }

        @Override
        public  String closeToken()
        {
        	return ")"; 
        }
    }
