package org.netsharp.panda.commerce;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.ICatEntityService;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Category;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.entity.Entity;
import org.netsharp.panda.controls.tree.Tree;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.Part;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.json.TreeResultJson;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class TreePart extends Part {

	public Tree tree;
	
	private String url = "";

	@Override
	public void initialize() {
		this.addTip();
		this.initToolbar();
		this.addTree();
		this.addJscript();
		super.initialize();
	}

	protected void addTree() {
		String entityId = this.context.getEntityId();

		tree = new Tree();
		{
			tree.setId("tree" + context.getCode());
			tree.EntityId = entityId;
			tree.Service = this.context.getService();

			String filter = context.getFilter();
			if (StringManager.isNullOrEmpty(filter)) {
				filter = "";
			}

			url = "/panda/rest/service?vid=" + this.context.getId() + "&method=query&filter=" + filter;
			if(this.context.isAutoQuery()){

				tree.Url = UrlHelper.getUrl(url);
			}
			tree.Filter = null;
		}
		tree.onLoadSuccess = "function(node, data){" + getJsInstance() + ".onLoadSuccess(node,data);}";
		tree.OnClick = "function(node){" + getJsInstance() + ".onClick(node);}";
		tree.OnBeforeExpand = "function(node,param){" + getJsInstance() + ".onBeforeExpand(node,param)}";
		tree.onDrop = "function(target, source, point){" + getJsInstance() + ".onDrop(target, source, point)}";
		tree.onBeforeDrop = "function(target, source, point){" + getJsInstance() + ".onBeforeDrop(target, source, point)}";

		tree.onDragEnter = "function(target, source){" + getJsInstance() + ".onDragEnter(target, source)}";
		tree.onDragOver = "function(target, source){" + getJsInstance() + ".onDragOver(target, source)}";
		tree.onDragLeave = "function(target, source){" + getJsInstance() + ".onDragLeave(target, source)}";
		tree.onStartDrag = "function(node){" + getJsInstance() + ".onStartDrag(node)}";
		tree.onStopDrag = "function(node){" + getJsInstance() + ".onStopDrag(node)}";
		tree.onBeforeDrag = "function(node){" + getJsInstance() + ".onBeforeDrag(node)}";

		this.getControls().add(tree);
	}

	/* 查询 */
	public List<TreeNode> query() throws UnsupportedEncodingException {
		
		String entityId = this.context.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);

		Oql oql = new Oql();
		{
			oql.setEntityId(meta.getEntityId());
			oql.setSelects("*");
			oql.setOrderby(meta.getOrderby());
		}

		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
		}

		ArrayList<String> filters = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter)) {
			filters.add(filter);
		}

		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {
			filters.add(extraFilter);
		}
		
		Category category = meta.getCategory();
		if(category != null){
			
			Column column = meta.getProperty(category.getPropertyName());
			String id = this.getRequest("id");
			if (StringManager.isNullOrEmpty(id)) {
				// 顶级查询
				String keyFilter = meta.getId().getEmptyFilter(meta.getCode() + "." + column.getColumnName());
				filters.add(keyFilter);
			} else {
				// 非顶级查询
				filters.add(meta.getCode() + "." + column.getColumnName() + "= ?");
				Object parentId = column.getConvertor().fromString(id);
				oql.getParameters().add("@" + column.getPropertyName(), parentId, column.getDataType().getJdbcType());
			}
		}

		oql.setFilter(StringManager.join(" and ", filters));

		Class<?> serviceType = ReflectManager.getType(this.context.getService());
		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		List<?> rows = service.queryList(oql);

		List<TreeNode> nodes = this.serialize(rows);
				
		return nodes;
	}
	
	protected List<TreeNode> serialize(List<?> rows){
		
		String entityId = this.context.getEntityId();
		TreeResultJson json = new TreeResultJson(rows, entityId);
		List<TreeNode> nodes = json.parse();
		return nodes;
	}

	/* 删除 */
	public boolean delete(String ids) {
		
		this.getService();
		
		String[] arr = ids.split("_");
		String entityId = this.context.getResourceNode().getEntityId();
		
		for (String id : arr) {
			
			Entity entity = (Entity) ReflectManager.newInstance(entityId);
			{
				entity.toDeleted();
				entity.setId(Long.valueOf(id));
			}
		
			this.service.save(entity);
		}
		return true;
	}

	public boolean changeParent(Integer nodeId, Integer newParentId) {
		
//		orgService.changeParent(nodeId, newParentId);
//		pathCode();
		return true;
	}
	
	
	/* 同步路径 */
	public void pathCode() {
		
		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
		service.generatePathCode(this.context.getEntityId());
		
	}

	public String getExtraFilter() {
		return null;
	}

	protected void addJscript() {
		String entityId = this.context.getEntityId();

		this.addJscript("        ", JscriptType.Header);
		this.addJscript("        //", JscriptType.Header);

		this.addJscript("        var " + getJsInstance() + " = new " + getJsController() + "();", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.name=\"" + this.context.getName() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.vid=\"" + this.context.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".workspaceId=\"" + this.context.getWorkspaceId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.entityId=\"" + entityId + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.service=\"" + this.getClass().getName() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.id=\"" + tree.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.formUrl=\"" + this.context.getUrl() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.autoQuery=" + this.context.isAutoQuery() + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.url=\"" + url + "\";", JscriptType.Header);
		Mtable meta = MtableManager.getMtable(entityId);
		Category category = meta.getCategory();
		if(category != null){
			this.addJscript("        " + getJsInstance() + ".context.parentId=\"" + category.getPropertyName() + "\";", JscriptType.Header);
		}
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.tree.js"));
		writer.write("  <style>.layout-panel-west .panel-header{border-top:0px;}</style>");
	}
}
