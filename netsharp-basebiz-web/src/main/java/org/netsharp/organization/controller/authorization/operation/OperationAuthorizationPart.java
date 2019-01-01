package org.netsharp.organization.controller.authorization.operation;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IPrincipalOperationService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.dic.UiOperationType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Operation;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.treegrid.TreeGrid;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class OperationAuthorizationPart extends TreegridPart {
	
	private List<ResourceNode> resourceNodes;
	private List<ResourceNode> resourceTree;
	private List<Operation> operations;
	private List<Operation> columnOperations = new ArrayList<Operation>();
	private IOperationService operationService = ServiceFactory.create(IOperationService.class);

	public static List<Object> JSON_SCHEMA;

	TreeGrid treegrid;

	@Override
	protected void render() {
		
		datagrid = treegrid = new TreeGrid();
		{
			treegrid.setId("treegrid");
			treegrid.idField = "id";
			treegrid.treeField = "name";
		}

		datagrid.setId("datagrid" + this.context.getCode());
		datagrid.pagination = false;
		datagrid.url = "/panda/rest/service?vid=" + this.context.getId() + "&method=query";

		this.getControls().add(treegrid);

		this.renderTreegrid();
		
		this.setId("datagrid");
		this.setClassName("page-content");
	}

	protected void renderTreegrid() {
		this.treegridSchemaData(false);
		this.treegridSchema();
	}

	protected void treegridSchemaData(Boolean isClassification) {
		// 查询功能资源树
		resourceNodes = operationService.queryOperationResourceNodes(isClassification);

		// 查询可勾选的功能权限
		operations = operationService.querySettingOperations(isClassification);

		// 查询功能权限列
		HashMap<String, Operation> tt = new HashMap<String, Operation>();
		for (Operation operation : operations) {
			if (!tt.containsKey(operation.getCode())) {
				tt.put(operation.getCode(), operation);
			}
		}

		List<Operation> orderbys = new ArrayList<Operation>();
		orderbys.addAll(tt.values());

		Collections.sort(orderbys, new Comparator<Operation>() {
			public int compare(Operation o1, Operation o2) {
				if (o1.getSeq() > o2.getSeq()) {
					return 1;
				} else if (o1.getSeq() < o2.getSeq()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		columnOperations.addAll(orderbys);
	}

	protected void treegridSchema() {
		DataGridColumn column = null;
		column = new DataGridColumn();
		{
			column.width = 250;
			column.field = "name";
			column.value = "功能";
		}
		treegrid.columns.add(column);

		column = new DataGridColumn();
		{
			column.width = 45;
			column.field = "isSelectionColumn";
			column.value = "选择";
		}
		treegrid.columns.add(column);

		// 创建列表界面功能权限的列
		for (Operation row : columnOperations) {
			column = new DataGridColumn();
			{
				column.width = 45;
				column.field = row.getCode();
				column.value = row.getName();
				column.setStyle("align:center");
			}
			treegrid.columns.add(column);
		}

		// 往每个ResourceNode添加可设置的功能权限
		for (ResourceNode resourceNode : resourceNodes) {
			// 添加行头的CheckBox对应的数据源
			OperationCategoryStruct ocs = new OperationCategoryStruct();
			{
				ocs.setResourceNode(resourceNode);
			}
			resourceNode.setTag(ocs);

			if (resourceNode.getResourceType() != ResourceType.VOUCHER) {
				continue;
			}

			// 添加具体的列
			for (Operation operation : operations) {
				if (!operation.getResourceNode().getId().equals(resourceNode.getId())) {
					continue;
				}

				AuthorizationStruct authorizationStruct = new AuthorizationStruct();
				{
					authorizationStruct.setHasAuth(true);
					authorizationStruct.setAuth(true);
					authorizationStruct.setOperation(operation);
					authorizationStruct.setResourceNode(resourceNode);
				}

				resourceNode.set(operation.getCode(), authorizationStruct);
			}
		}

		List<ResourceNode> tempNodes = new ArrayList<ResourceNode>();
		tempNodes.addAll(resourceNodes);

		resourceTree = CatEntity.listToTree(tempNodes);
	}

	@Override
	protected void addJscript() {
		// base.AddJscript();

		this.addJscript("        ", JscriptType.Header);
		this.addJscript("        //", JscriptType.Header);

		this.addJscript("        var " + this.getJsInstance() + " = new " + this.getJsController() + "();", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".context.name=\"" + this.context.getName() + "\";", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".context.vid=\"" + this.context.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".context.service=\"" + this.getClass().getName() + "\";", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".context.id=\"" + datagrid.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".datagrid=$('#" + datagrid.getId() + "');", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".datagrid.controller=" + this.getJsInstance() + ";", JscriptType.Header);
		this.addJscript("        " + this.getJsInstance() + ".context.queryUrl=\"" + datagrid.url + "\";", JscriptType.Header);

		AuthorizationSerializer serialize = new AuthorizationSerializer();

		String[] columns = new String[columnOperations.size()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = columnOperations.get(i).getCode();
		}

		JSON_SCHEMA = serialize.serialize(this.resourceTree, columns);
	}

	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);

		writer.write(UrlHelper.getVersionScript("/panda-bizbase/organization/org-authorization-treelist-part.js"));
	}

	// ------------------------------------------------------------
	// 控制器
	// ------------------------------------------------------------

	@Override
	public List<Object> query() {
		return JSON_SCHEMA;
	}

	// / <summary>
	// / 选中岗位后得到岗位的授权信息
	// / </summary>
	/**
	 * <p>方法名称：authorizationPrincipal</p>
	 * <p>方法描述：</p>
	 * @param id
	 * @return
	 * @author gaomeng
	 * @since  2016年2月25日
	 * <p> history 2016年2月25日 gaomeng  创建   <p>
	 */
	public AuthorizationPrincipal authorizationPrincipal(Object principalId,PrincipalType principalType) {

		IAuthorizationPrincipalService organizationOperationService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		Oql oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("principalOperations.{Id,operationId},AuthorizationPrincipal.Id");
			oql.setFilter("AuthorizationPrincipal.principalType= ? AND AuthorizationPrincipal.principalId= ? ");

			Mtable meta = MtableManager.getMtable(AuthorizationPrincipal.class);
			oql.getParameters().add("@principalType", principalType.getValue(), Types.TINYINT);
			oql.getParameters().add("@principalId", principalId, meta.getKeyColumn().getDataType().getJdbcType());
		}

		AuthorizationPrincipal ap = organizationOperationService.queryFirst(oql);
		if (ap == null) {
			ap = new AuthorizationPrincipal();
			{
				ap.setEntityState(EntityState.New);
				ap.setPrincipalId(Integer.valueOf(principalId.toString()));
				ap.setPrincipalType(PrincipalType.POST);
			}
		}

		return ap;
	}

	// / <summary>
	// / 更新某个操作的权限
	// / </summary>
	public void updatePrincipalOperation(String idPrincipal,PrincipalType principalType, String operationIds, UiOperationType operationType) {
		if (StringManager.isNullOrEmpty(operationIds)) {
			return;
		}
		
		// 更新数据库
		Mtable meta = MtableManager.getMtable(AuthorizationPrincipal.class);
		ITypeConvertor convertor = meta.getKeyColumn().getConvertor();

		Object principalId = convertor.fromString(idPrincipal);

		IPrincipalOperationService service = ServiceFactory.create(IPrincipalOperationService.class);

		service.updatePrincipalOperation(principalId,principalType, operationIds, operationType);
		
		// 更新当前用户的Session ,应该是更新所有的Session，目前只更新当前用户
		PandaSessionPersister.reset();
	}
}
