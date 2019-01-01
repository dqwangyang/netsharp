package org.netsharp.resourcenode;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

@Service
public class ResourceNodeService extends PersistableService<ResourceNode> implements IResourceNodeService {

	public ResourceNodeService() {
		super();
		this.type = ResourceNode.class;
	}

	@Override
	public ResourceNode save(ResourceNode entity) {
		EntityState state = entity.getEntityState();
		ResourceNode old = this.byCode(entity.getCode());
		if(StringManager.isNullOrEmpty(entity.getName())){
			
			return null;
		}
		if (EntityState.New.equals(state)) {
			if (old != null) {
				throw new BusinessException("资源code重复：" + entity.getCode());
			}

			// 先注释，
			// 新增状态同步下分类路径,
			// Thread t = new Thread() {
			// public void run() {
			// ICatEntityService service =
			// ServiceFactory.create(ICatEntityService.class);
			// service.generatePathCode(ResourceNode.class.getName());
			// }
			// };
			// t.start();

		} else if (EntityState.Persist.equals(state)) {
			if (old != null && !old.getId().equals(entity.getId())) {
				throw new BusinessException("资源code重复" + entity.getCode());
			}
		}

		return super.save(entity);
	}

	public ResourceNode byCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("ResourceNode.*");
			oql.setFilter("code = ?");

			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		ResourceNode node = this.pm.queryFirst(oql);

		return node;
	}

	/* 导出资源相关的元数据脚本 */
	public List<String> export(Integer resourceNodeId) {
		List<String> sqls = new ArrayList<>();

		export(resourceNodeId, sqls);

		return sqls;

	}

	private void export(Integer resourceNodeId, List<String> allSqls) {
		ResourceGenerator generator = new ResourceGenerator();

		List<String> ls = generator.generate(resourceNodeId);
		allSqls.addAll(ls);
		List<ResourceNode> childNodes = this.getChildNodes(resourceNodeId);
		if (childNodes.size() > 0) {
			for (ResourceNode node : childNodes) {

				export(node.getId(), allSqls);
			}
		}

	}

	private List<ResourceNode> getChildNodes(Integer parentId) {
		Oql oql = new Oql();
		{
			oql.setSelects("id");
			oql.setFilter("parentId=?");
			oql.setType(ResourceNode.class);
			oql.setParameters(new QueryParameters());
			{
				oql.getParameters().add("@parentId", parentId, Types.INTEGER);
			}
		}
		List<ResourceNode> resourceNodes = this.queryList(oql);
		return resourceNodes;

	}
}
