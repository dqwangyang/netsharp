package org.netsharp.panda.commerce;

import org.netsharp.core.Category;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.form.PandaForm;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.Part;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.util.StringManager;

/**
 * @ClassName: FormDetailPartBase
 * @Description:TODO 表单只读（点击查看时）
 * @author: 韩伟
 * @date: 2018年1月19日 下午2:49:06
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class FormDetailPart extends Part {

	protected PandaForm form;

	// 实体对象
	protected Object obj;

	@Override
	public void initialize() {
		this.addTip();
		this.render();
		this.addJscript();
		super.initialize();
	}

	protected void render() {

		form = new PandaForm();
		{
			form.setId("form" + this.context.getCode());
			form.obj = obj;
			form.setPForm(this.context.getForm());
		}

		this.getControls().add(form);
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionCss("/panda-res/css/panda.form.css"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.form.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.form.detail.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.detail.js"));
	}

	protected void addJscript() {

		this.addJscript("        ", JscriptType.Header);
		this.addJscript("        //", JscriptType.Header);
		this.addJscript("        var " + getJsInstance() + " = new " + getJsController() + "();", JscriptType.Header);

		this.addJscript("        " + getJsInstance() + ".context.instanceName=\"" + getJsInstance() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.vid=\"" + this.context.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.isTip=" + (!StringManager.isNullOrEmpty(this.context.getMemoto())) + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.formId=\"" + this.context.getFormId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.entityId=\"" + this.context.getEntityId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.service=\"" + this.getClass().getName() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.formName=\"" + form.getId() + "\";", JscriptType.Header);

		Mtable meta = MtableManager.getMtable(this.context.getEntityId());
		Category category = meta.getCategory();
		if (category != null) {
			this.addJscript("        " + getJsInstance() + ".context.parentId=\"" + category.getPropertyName() + "\";", JscriptType.Header);
		}
	}
}
