package org.netsharp.wx.meta.pa.configuration;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.wx.pa.entity.NArticle;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.PublicAccount;

public class ReplyGraphicWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/wx/reply/graphic/list";
		urlForm = "/wx/reply/graphic/form";
		entity = NGraphicReply.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = NGraphicReply.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);

		// 基本信息
		addColumn(datagrid, "publicAccount.name", "公众号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "keyword", "关键字", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100);

		// 操作信息
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, 120);

		return datagrid;
	}
	
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(NArticle.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "回复内容");
		{
			datagrid.setShowTitle(true);
		}
		
		addColumn(datagrid, "header", "标题", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "description", "描述", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "imageUrl", "图片", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "url", "文章地址", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "createTime", "创建日期", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
	    
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("articles");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("articles");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			
			part.setWindowWidth(800);
			part.setWindowHeight(400);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:200px");
	}

	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("回复内容");
		}
		addFormField(form, "header", "标题",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "imageUrl", "图片",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "url", "文章地址",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "description", "描述",null, ControlTypes.TEXTAREA, true, false);
		return form;
	}
	

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("文本回复");
			form.setColumnCount(3);
		}

		// 基本信息
		addFormFieldRefrence(form, "publicAccount.name", "公众号", null, PublicAccount.class.getSimpleName(), true, false);
		addFormField(form, "keyword", "关键字", ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		
		return form;

	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addRefrenceQueryItem(queryProject, "publicAccount.name", "公众号", PublicAccount.class.getSimpleName());
		return queryProject;
	}
	
	@Override
	public void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}