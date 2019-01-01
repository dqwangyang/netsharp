package org.netsharp.resourcenode;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.resourcenode.entity.IResourceable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.script.ScriptGenerator;
import org.netsharp.util.ReflectManager;

/*导出资源相关的元数据脚本*/
public class ResourceGenerator {
	
	private ScriptGenerator generator = new ScriptGenerator();
	private IPersister<IPersistable> pm = PersisterFactory.create();

	public List<String> generate(Integer resourceNodeId) {
		
		List<String> sqls = new ArrayList<String>();
		
		//resourceNode自身脚本
		IPersistable node = pm.byId(ResourceNode.class.getName(), resourceNodeId);
		if(node==null){
			throw new NetsharpException("资源节点不存在："+ resourceNodeId );
		}
		List<String> nodes = generator.generateComposite(node);
		sqls.addAll(nodes);
		

		IDb db = DbFactory.create();
		List<Class<?>> clss = db.getAllPersistableEntities();

		List<Class<?>> persistableClass = new ArrayList<Class<?>>();

		for (Class<?> cls : clss) {
			if (ReflectManager.isInterface(cls, IResourceable.class)) {

				persistableClass.add(cls);
			}
		}

		
		for (Class<?> cls : persistableClass) {
			List<String> ss = this.generate(cls, resourceNodeId);
			sqls.addAll(ss);
		}

		return sqls;
	}

	private List<String> generate(Class<?> cls, Integer resourceNodeId) {

		Oql oql = new Oql();
		{
			oql.setType(cls);
			oql.setSelects("id");
			oql.setFilter("resourceNodeId=?");
			oql.setOrderby("id");

			oql.getParameters().add("@resourceNodeId", resourceNodeId, Types.INTEGER);
		}
		

		List<IPersistable> entities = pm.queryList(oql);

		List<String> ss = new ArrayList<String>();

		for (IPersistable entity : entities) {
			entity = pm.byId(entity);
			ss.addAll(generator.generateComposite(entity));
		}

		return ss;
	}
}
