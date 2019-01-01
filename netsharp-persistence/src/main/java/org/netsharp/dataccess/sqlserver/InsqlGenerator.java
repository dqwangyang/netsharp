package org.netsharp.dataccess.sqlserver;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.property.IProperty;
import org.netsharp.dataccess.IInsqlGenerator;
import org.netsharp.entity.IPersistable;
import org.netsharp.util.StringManager;

public class InsqlGenerator implements IInsqlGenerator {
	
        public String inSql(Iterable<IPersistable> rows, String propertyName, QueryParameter qp)
        {
            ArrayList<String> ids = new ArrayList<String>();

            IProperty property = null;

            for (IPersistable row : rows)
            {
            	if(property==null){
            		Mtable meta=MtableManager.getMtable(row.getClass());
            		property=meta.getProperty(propertyName).getProperty();
            	}
            	
                Object o = property.field(row);

                if (o == null)
                {
                    continue;
                }

                String id = o.toString();

                if (!ids.contains(id))
                {
                    ids.add(id);
                }
            }
            
            if(ids.size()==0){
           	 return "NULL";
           }

            return inSql(ids, qp);
        }
        
        public String inSql(String[] ids, QueryParameter qp)
        {
        	List<String> list = new ArrayList<String>();
        	for(String item : ids){
        		list.add(item);
        	}
        	
        	return inSql(list,qp);
        }

        public String inSql(Iterable<String> ids, QueryParameter qp)
        {
            StringBuilder builder = new StringBuilder();
            for (String id : ids)
            {
                builder.append("<row>").append(id).append("<row>").append(StringManager.NewLine);
            }

            qp.setValue(builder.toString());
            qp.setDbType(Types.VARCHAR);

            return "select * from tt(" + qp.getName() + ")";
        }

        public String xmlId(Iterable<String> ids)
        {
            StringBuilder builder = new StringBuilder();

            for (String id : ids)
            {
            	 builder.append("<row>").append(id).append("<row>").append(StringManager.NewLine);
            }

            return builder.toString();
        }
        
        public boolean isParameter(){
        	return true;
        }
}
