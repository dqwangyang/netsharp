package org.netsharp.panda.commerce;

import org.netsharp.core.EntityState;
import org.netsharp.core.IRow;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.base.DatagridPartBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.form.PandaForm;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class DetailPart extends DatagridPartBase {

	public boolean delete(String ids) {
		String[] arr = ids.split("_");
		String entityId = this.context.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);
		for (String id : arr) {
			IPersistable entity = (IRow) ReflectManager.newInstance(entityId);
			entity.setEntityState(EntityState.Deleted);
			meta.setId(entity, id);
			this.service.save(entity);
		}

		return true;
	}
	
	public IPersistable newInstance(){
		
		return  this.getService().newInstance();
	}

	@Override
	protected void render() {
		this.className = "detail";
		this.initToolbar();
		super.render();

		if(this.toolbar != null){

			datagrid.toolbar = "#" + this.toolbar.getId();
		}
		datagrid.selectOnCheck = true;
		datagrid.checkOnSelect = true;
		datagrid.rownumbers = pdatagrid.isRownumbers();
		datagrid.onLoadSuccess = "function(data){" + getJsInstance() + ".loadSuccess(data,'" + this.datagrid.getId() + "');}";
		datagrid.onRowContextMenu = "function(e, index, row){" + getJsInstance() + ".onRowContextMenu(e, index, row);}";
		datagrid.singleSelect = pdatagrid.isSingleSelect();

		this.createDetailFrom();
	}

	/**
	 * @Title: createDetailFrom
	 * @Description: 创建明细对应的表单
	 * @param:
	 * @return: void
	 * @throws
	 */
	protected void createDetailFrom() {

		Long formId = this.context.getFormId();
		if (formId != null) {

//			Dialog dialog = new Dialog();
//			{
//				dialog.setId(context.getCode() + "_dialog");
//				dialog.width = this.context.getWindowWidth();
//				dialog.height = this.context.getWindowHeight();
//				dialog.title = this.context.getForm().getName();
//
//				String toolbar = String
//						.format("toolbar:[{text:'确定',iconCls:'fa fa-save',handler:function(){%s.save();}}]",
//								getJsInstance());
//				dialog.dataOptions.add(toolbar);
//
//			}
			

			PandaForm form = new PandaForm();
			{
				form.setId(this.context.getCode() + "_form");
				form.setPForm(this.context.getForm());
				form.setStyle("display:none");
			}

			this.getControls().add(form);
		}
	}


	@Override
	protected void importJs(IHtmlWriter writer) {
		//避免重复加载
		
		PForm nform = this.context.getForm();
		if(nform != null){
			
			PFormField field = null;
			for (PFormField x : nform.getFields()) {
				if (x.getControlType() == ControlTypes.EDITOR) {
					field = x;
					break;
				}
			}

			field = null;
			for (PFormField x : nform.getFields()) {
				if (x.getControlType() == ControlTypes.QINIUUPLOAD || x.getControlType() == ControlTypes.OSS_UPLOAD) {
					field = x;
					break;
				}
			}
			
			if (field != null) {
				writer.write(UrlHelper.getVersionScript("/package/qiniu/plupload.full.min.js"));
				writer.write(UrlHelper.getVersionScript("/package/qiniu/qiniu.js"));
			}
		}
	}
	
	@Override
	protected void addJscript() {
		super.addJscript();

		String role = this.context.getRelationRole();
		if (StringManager.isNullOrEmpty(role)) {
			role = this.context.getCode();
		}

		PPart parent = this.context.parent();
		Mtable metaParent = MtableManager.getMtable(parent.getEntityId());
		String foreignKey = metaParent.getSub(role).getForeignProperty();

		this.addJscript("        " + getJsInstance()+ ".context.relationRole='" + role + "';", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.foreignKey='"+ foreignKey + "';", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.formName='"+ this.context.getCode() + "_form';", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.width='"+ this.context.getWindowWidth() + "px';", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.height='"+ this.context.getWindowHeight() + "px';", JscriptType.Header);
		
		if(this.context.getForm() !=null){

			this.addJscript("        " + getJsInstance() + ".context.title='"+ this.context.getForm().getName() + "';", JscriptType.Header);
		}

		
//		IPersistable newEntity =  this.getService().newInstance();
//		newEntity.toNew();
//		String newEntityJson = JsonManage.serialize2(newEntity);
//		this.addJscript("        " + getJsInstance() + ".context.newInstance="+ newEntityJson + ";", JscriptType.Header);
		
		String formUrl = this.getContext().getUrl();
		if (StringManager.isNullOrEmpty(formUrl)) {
			formUrl = "null";
		} else {
			formUrl = "\"" + formUrl + "\"";
		}
		this.addJscript("        " + getJsInstance() + ".context.formUrl="
				+ formUrl + ";", JscriptType.Header);
	}
}
