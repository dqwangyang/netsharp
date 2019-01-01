package org.netsharp.plugin.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.sql.full.FullSelectGenerator;
import org.netsharp.plugin.base.IPathServcie;
import org.netsharp.plugin.core.PluginException;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.plugin.entity.Pathable;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

/*插件路径服务*/
@Service
public class PathService implements IPathServcie {
	
	private IPersister<Pathable> pst = PersisterFactory.create();
	
	/*根据插件路径查询插件项*/
	public Pathable byPath(String path,String typeName){
		
		Class<?> type = ReflectManager.getType(typeName);
        
        String filter = "'"+path+"'";
        List<String> allPaths = new ArrayList<String>();{
        	allPaths.add(filter);
        }
        
        this.byPath(filter, type,allPaths);

        FullSelectGenerator generator = new FullSelectGenerator(type);
        String selects = generator.generate();
        
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects(selects);
			oql.setFilter("path in ("+StringManager.join(",", allPaths)+")");
		}
		
		List<Pathable> pathables = pst.queryList(oql);
		
		if(pathables.size() == 0){
			throw new PluginException("未能发现插件扩展点："+path);
		}
		
		Pathable pathable = null;
		for(Pathable p : pathables){
			
			if(p.getPath().equals(path)){
				pathable = p;
				break;
			}
		}
		
		if(pathable == null){
			throw new PluginException("未能发现插件扩展点："+path);
		}
		
		pathables.remove(pathable);
		
		for(Pathable p : pathables){

			//要解决排序问题
			List<Codonable> codons = p.getCodons();
			pathable.getCodons().addAll(codons);
	       
		}
		
		// 插件项目排序
		List<Codonable> codons = pathable.getCodons();
		codons = CodonHelper.sort(codons);
		 
		 // 生成插件项目树
		codons = CodonHelper.generateTree(codons);
		
		return pathable;
	}
	
	private void byPath(String paths,Class<?> type,List<String> allPaths){
		
		Oql oql = new Oql();{
			oql.setType(type);
			oql.setSelects("id,basePath");
			oql.setFilter("path IN ("+ paths +")");
		}
		
		List<Pathable> npaths =  pst.queryList(oql);
		
		if(npaths.size() == 0){
			return;
		}
		
		List<String> ss = new ArrayList<String>();
		for(Pathable path : npaths){
			String p = path.getBasePath();
			if(StringManager.isNullOrEmpty(p)){
				continue;
			}
			p="'"+p+"'";
			ss.add(p);
			
			if(!allPaths.contains(p)){
				allPaths.add(p);
			}
		}
		
		this.byPath(StringManager.join(",", ss), type,allPaths);
	}
}
