package org.netsharp.persistence.oql.parser.set;

import java.util.ArrayList;

import org.netsharp.core.Mtable;

    public class OqlStruct
    {
        public OqlStruct()
        {
            Subs = new ArrayList<OqlStruct>();
        }

        //最终执行的SQL
        public String SQL ;
        public String Selects ;
        public String Joins="" ;
        //Orderby对应的Joins，当Set分页查询时，where条件不需要但是Orderby的Joins还是需要
        public String OrderbyJoins ;
        public String Wheres ;
        public String Orderby ;
        //IN外Id字段
        public String OutInId = null;
        //IN内Id字段
        public String InInIdField = null;
        public boolean IsMain ;
        public Mtable Mtable ;
        public int Level ;
        public OqlStruct Parent ;
        public ArrayList<OqlStruct> Subs ;

        @Override
        public  String toString()
        {
            String key = Mtable.getEntityId()
                + " : " + OutInId
                + " : " + InInIdField
                + " : " + this.Parent.Mtable.getEntityId();

            return key;
        }
    }

