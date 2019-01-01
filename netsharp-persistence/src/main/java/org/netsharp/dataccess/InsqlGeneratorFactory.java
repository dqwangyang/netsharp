package org.netsharp.dataccess;


import org.netsharp.dataccess.mysql.InsqlGenerator;
import org.netsharp.dic.DatabaseType;

/// <summary>
/// 
/// </summary>
public class InsqlGeneratorFactory
{
    //private static HashMap<DatabaseType, IInsqlGenerator> generators = new HashMap<DatabaseType, IInsqlGenerator>();

    /// <summary>
    /// 
    /// </summary>
    /// <param name="type"></param>
    /// <returns></returns>
    public static IInsqlGenerator create(DatabaseType type)
    {
    	return new InsqlGenerator();
    	
        //IInsqlGenerator generator = generators.get(type);
        
//        if (!generators.TryGetValue(type, out generator))
//        {
//            string assembly = "Netsharp.Data." + type;
//            string ctype = assembly + ".InsqlGenerator," + assembly;
//
//            generator = Activiator.CreateInstance<IInsqlGenerator>(ctype);
//
//            generators.Add(type, generator);
//        }

        //return generator;
    }
}
