package org.netsharp.dataccess;

import org.netsharp.dic.DatabaseType;
import org.netsharp.util.ReflectManager;

public class PagingGeneratorFactory {
	
    public static IPagingGenerator create(DatabaseType dbt)
    {
    	String dbType=dbt.toString().toLowerCase();
        String type="org.netsharp.dataccess."+dbType+".PagingGenerator";
        IPagingGenerator g=(IPagingGenerator)ReflectManager.newInstance(type);

        return g;
    }

    public static IPagingGenerator Create() 
    {
        DatabaseType dbt = DatabaseProperty.defaultDatabaseProperty().getType();

        return create(dbt);
    }
}
