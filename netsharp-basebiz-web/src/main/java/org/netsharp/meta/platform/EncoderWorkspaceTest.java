package org.netsharp.meta.platform;

import org.netsharp.autoencoder.entity.Encoder;
import org.netsharp.autoencoder.entity.EncoderRule;
import org.netsharp.autoencoder.entity.EncoderType;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class EncoderWorkspaceTest  extends WorkspaceCreationBase{
	@Override
	public void setup() {

		resourceNodeCode = Encoder.class.getSimpleName();
		urlList="/platform/encoder/list";	
		urlForm = "/platform/encoder/form";
		entity = Encoder.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "编码规则";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setReadOnly(true);
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 80,true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150,true);
		addColumn(datagrid, "billType", "单据类型", ControlTypes.TEXT_BOX,100, true);
		addColumn(datagrid, "entityType", "实体类型", ControlTypes.TEXT_BOX,300, true);
		addColumn(datagrid, "expandClassName", "扩展规则类", ControlTypes.TEXT_BOX,100, false);
		addColumn(datagrid, "dateFormat", "日期格式",ControlTypes.TEXT_BOX,80, false);
		addColumn(datagrid, "totalLength", "总长度", ControlTypes.NUMBER_BOX,80, false);
		addColumn(datagrid, "sequenceLength", "序列长度", ControlTypes.NUMBER_BOX,80, false);		
		addColumn(datagrid, "isPolishing", "是否补齐", ControlTypes.BOOLCOMBO_BOX,60, false);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150, false);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "billType", "单据类型", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "entityType", "实体类型", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(EncoderRule.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "编码规则");
		datagrid.setShowTitle(true);
		addColumn(datagrid, "rowNum", "行号", ControlTypes.NUMBER_BOX, 150);
		PDatagridColumn column = addColumn(datagrid, "rowType", "类别", ControlTypes.ENUM_BOX, 80);{
			
			String formatter = EnumUtil.getColumnFormatter(EncoderType.class);
			column.setFormatter(formatter);
		}
		addColumn(datagrid, "ruleFormat", "格式", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "ruleDetail", "内容", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "ruleLength", "长度", ControlTypes.NUMBER_BOX, 80);
		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("encoderRules");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("encoderRules");
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
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:250px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("编码规则");
		}
		
		addFormField(form, "rowNum", "行号",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "rowType", "类别",null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "ruleFormat", "格式",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "ruleDetail", "内容",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "ruleLength", "长度",null, ControlTypes.NUMBER_BOX, true, false);
		
		return form;
	}
	
	
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, "自动编码表单");	
		form.setColumnCount(4);
		addFormField(form, "code", "编码", null,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", null,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "billType", "单据类型", null,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "entityType", "实体类型", null,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "expandClassName", "扩展规则类", null,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "dateFormat", "日期格式", null,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "totalLength", "总长度", null,ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "sequenceLength", "序列长度", null,ControlTypes.NUMBER_BOX, false, false);		
		addFormField(form, "isPolishing", "是否补齐", null,ControlTypes.CHECK_BOX, true, false);
		return form;
	}
	
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
