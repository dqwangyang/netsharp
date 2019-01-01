package org.netsharp.wx.meta.pa.configuration;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.PublicAccount;

public class PublicAccountWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/wx/public/account/list";
		urlForm = "/wx/public/account/form";
		entity = PublicAccount.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = PublicAccount.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);

		// 基本信息
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixinCode", "weixinCode", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "subscribeCode", "subscribeCode", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "unhintCode", "unhintCode", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qrCode", "qrCode", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "publicAccountType", "公众号类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "host", "主机域名", ControlTypes.TEXT_BOX, 100);

		// 配置信息
		addColumn(datagrid, "appId", "appId", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "appSecret", "appSecret", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "token", "token", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "encryptionKey", "encryptionKey", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "originalId", "originalId", ControlTypes.TEXT_BOX, 100);

		// 微信支付
		addColumn(datagrid, "mch_id", "mch_id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "mch_parner_key", "mch_parner_key", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "mch_notify_url", "mch_notify_url", ControlTypes.TEXT_BOX, 100);

		// APP支付
		addColumn(datagrid, "app_id", "app_id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "app_mch_id", "app_mch_id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "app_partner_key", "app_partner_key", ControlTypes.TEXT_BOX, 100);

		addColumn(datagrid, "memoto", "描述", ControlTypes.TEXTAREA, 300);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.TEXT_BOX, 120);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);

		// 基本信息
		addFormField(form, "name", "名称", "基础信息", ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "weixinCode", "weixinCode", "基础信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "subscribeCode", "subscribeCode", "基础信息", "粉丝关注关注公众号以后发送的文字或者图文，此处配置文字回复或者图文回复的关键字",ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "unhintCode", "unhintCode","粉丝如果发送了一个没有被命中的消息，那么配置这个文字或者图文回复的关键字，作为默认的回复", "基础信息", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "qrCode", "qrCode","关注公众号的二维码，录入URL", "基础信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "host", "主机域名","如：www.netsharp.org,如果有二级域名或者二级目录页需要加上", "基础信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "publicAccountType", "公众号类型", "基础信息", ControlTypes.ENUM_BOX, true, false);

		// 配置信息
		addFormField(form, "originalId", "originalId", "配置信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "appId", "appId", "配置信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "appSecret", "appSecret", "配置信息", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "token", "token", "配置信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "encryptionKey", "encryptionKey", "配置信息", ControlTypes.TEXT_BOX, true, false);		

		// 微信支付
		addFormField(form, "mch_id", "mch_id", "服务号才支持微信支付","微信支付", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "mch_parner_key", "mch_parner_key","服务号才支持微信支付", "微信支付",  ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "mch_notify_url", "mch_notify_url", "服务号才支持微信支付", "微信支付", ControlTypes.TEXT_BOX, false, false);

		// APP支付
		addFormField(form, "app_id", "app_id",  "服务号才支持APP微信支付","APP支付", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "app_mch_id", "app_mch_id","服务号才支持APP微信支付", "APP支付", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "app_partner_key", "app_partner_key","服务号才支持APP微信支付", "APP支付", ControlTypes.TEXT_BOX, false, false);

		// 操作信息
		addFormField(form, "creator", "创建人", "操作信息", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", "操作信息", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updator", "最后修改人", "操作信息", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updateTime", "最后修改时间", "操作信息", ControlTypes.TEXT_BOX, false, true);

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
