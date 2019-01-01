package org.netsharp.persistence.query;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.property.IProperty;
import org.netsharp.dataccess.DataAccessException;


public class TableSchema {

	public static IProperty[] populate(Mtable table, ResultSetMetaData schema){

		try{
			int columnsCount=schema.getColumnCount();
			IProperty[] properties = new IProperty[columnsCount];

			for(int i=1;i<=columnsCount;i++)
		    {
				Column column=table.getPropertyOrColumn( schema.getColumnName(i) );
				
				if(column!=null){
					
					properties[i-1]=column.getProperty();
					continue;
				}
		    }
			
			return properties;
		}
		catch(SQLException e){
			throw new DataAccessException("TableSchema.populate",null,null,e);
		}
	}
}
