package org.netsharp.panda.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.service.PersistableService;

@Service
public class PWorkspaceService extends PersistableService<PWorkspace> implements IPWorkspaceService {

	public PWorkspaceService() {
		super();
		this.type = PWorkspace.class;
	}

	public PWorkspace byUrl(String url) {

		String selects = "PWorkspace.*," 
						+ "ResourceNode.*," 
						+ "operationType.{id,code,name},"
						+ "Parts.*," 
						+ "Parts.ResourceNode.*," 
						+ "Parts.PartType.*," 
						+ "Parts.Datagrid.ResourceNode.*," 
						+ "Parts.Datagrid.*," 
						+ "Parts.Datagrid.Columns.*," 
						+ "Parts.Datagrid.stylers.*," 
						+ "Parts.Datagrid.Columns.reference.*," 
						+ "Parts.Datagrid.Columns.reference.dataGrid.*," 
						+ "Parts.Datagrid.Columns.reference.dataGrid.Columns.*," 
						+ "Parts.Datagrid.QueryProject.*," 
						+ "Parts.Datagrid.QueryProject.queryItems.*," 
						+ "Parts.Datagrid.QueryProject.queryItems.reference.*," 
						+ "Parts.Datagrid.QueryProject.queryItems.reference.dataGrid.*," 
						+ "Parts.Datagrid.QueryProject.queryItems.reference.dataGrid.Columns.*," 
						+ "Parts.Form.*," 
						+ "Parts.Form.ResourceNode.*," 
						+ "Parts.Form.fields.*," 
						+ "Parts.Form.fields.reference.*,"
						+ "Parts.Form.fields.reference.dataGrid.*," 
						+ "Parts.Form.fields.reference.dataGrid.Columns.*";

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects(selects);
			oql.setFilter("url=?");

			oql.getParameters().add("@url", url, Types.VARCHAR);
		}

		PWorkspace workspace = this.pm.queryFirst(oql);

		return workspace;
	}

	@Override
	public PWorkspace save(PWorkspace entity) {
		
		for(PPart part : entity.getParts()) {
			PDatagrid datagrid = part.getDatagrid();
			
			if(datagrid==null) {
				continue;
			}
			
			if(datagrid.getResourceNode()==null && datagrid.getResourceNodeId()==null){
				throw new NetsharpException("列表必须设置resourceNode:"+datagrid.getName());
			}
		}

		return super.save(entity);
	}

	public int removeByResourceCode(String code) {

		IResourceNodeService rservice = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = rservice.byCode(code);

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("id");
			oql.setFilter("resourceNodeId = " + node.getId());
		}

		List<PWorkspace> ws = this.pm.queryList(oql);

		for (PWorkspace w : ws) {
			w = this.byId(w);

			w.toDeleted();

			pm.save(w);
		}
		
		
		IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
		
		oql = new Oql();
		{
			oql.setType(PToolbar.class);
			oql.setSelects("PToolbar.*,items.*");
			oql.setFilter("resourceNodeId = " + node.getId());
		}
		
		List<PToolbar> toolbars = service.queryList(oql);
		for(PToolbar toolbar : toolbars){
			toolbar.toDeleted();
			service.save(toolbar);
		}

		return ws.size();
	}

	public int queryCount() {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("id");
		}

		int count = this.pm.queryCount(oql);

		return count;
	}
}
