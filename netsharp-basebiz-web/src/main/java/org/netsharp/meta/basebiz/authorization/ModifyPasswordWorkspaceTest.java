package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.web.ModifyPasswordFormPart;

public class ModifyPasswordWorkspaceTest  extends WorkspaceCreationBase {

	@Before
	public void setup() {
		
		formPartName = "修改密码";
		urlForm = "/system/modify/password/form";
//		formToolbarPath = "modify/password/form";
		formToolbarPath = null;
		entity = Employee.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "修改密码";
		resourceNodeCode = "ChangePassword";
		formJsImport = "/panda-bizbase/authorization/js/modify-password-form-part.js";
		formServiceController = ModifyPasswordFormPart.class.getName();
		formJsController  = ModifyPasswordFormPart.class.getName();
	}

//	@Test
//	public void createToolbar() {
//
//		ResourceNode node = this.getResourceNode();
//		PToolbar toolbar = new PToolbar();
//		{
//			toolbar.toNew();
//			toolbar.setPath(formToolbarPath);
//			toolbar.setName("修改密码");
//			toolbar.setResourceNode(node);
//		}
//
//		addToolbarItem(toolbar, "save", "提交", "fa-save", "save()", null, 1);
//		toolbarService.save(toolbar);
//	}
	
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = super.createForm(node);
		form.setColumnCount(1);
		PFormField field = null;
		addFormField(form, "originalPassword", "原始密码", null, ControlTypes.PASSWORDTEXT_BOX, true);
		field = addFormField(form, "newPassword", "新密码", null, ControlTypes.PASSWORDTEXT_BOX, true);{
			field.setTroikaValidation("['minLength[6]']");
		}
		field = addFormField(form, "confirmPassword", "确认密码", null, ControlTypes.PASSWORDTEXT_BOX, true);{
			
			field.setTroikaValidation("['equals[\\'#newPassword\\']']");
		}
		return form;
	}

	protected void doOperation() {
		
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
