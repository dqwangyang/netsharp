package org.netsharp.panda.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.base.IPluginService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.script.ScriptGenerator;
import org.netsharp.service.PersistableService;

@Service
public class PluginService extends PersistableService<Plugin> implements IPluginService {
	
    public PluginService(){
    	super();
    	this.type=Plugin.class;
    }
    
 	/*导出插件sql脚本*/
    public List<String> export(Integer id){
    	
    	List<String> sqls = new ArrayList<String>();
    	
    	//导出插件自身
    	Plugin plugin = this.byId(id);
    	ScriptGenerator generator = new ScriptGenerator();
    	List<String> ps = generator.generateComposite(plugin);
    	sqls.addAll(ps);
    	
    	//查询配置了插件的资源节点：tops
    	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
    	ICatEntityService catService = ServiceFactory.create(ICatEntityService.class);
    	catService.generatePathCode(ResourceNode.class.getName());
    	
    	Oql oql = new Oql();
    	{
    		oql.setType(ResourceNode.class);
    		oql.setSelects("*");
    		oql.setFilter("pluginId=?");
    		oql.getParameters().add("@pluginId",id,Types.INTEGER);
    	}
    	
    	List<ResourceNode> tops = resourceNodeService.queryList(oql);
    	
    	//根据tops的分类码循环查询下级的资源节点：nodes
    	List<ResourceNode> nodes = new ArrayList<ResourceNode>();
    	
    	for(ResourceNode node : tops){
    		
    		oql.setFilter("pathCode like '"+node.getPathCode()+"%'");
    		oql.getParameters().clear();
    		
    		List<ResourceNode> ns = resourceNodeService.queryList(oql);
    		
    		nodes.addAll(ns);
    	}
    	
    	// 生成所有资源节点的脚本
    	for(ResourceNode node : nodes){
    	
    		List<String> ss =resourceNodeService.export(node.getId());
    		
    		sqls.addAll(ss);
    	}
    	
    	return sqls;
    }
}
