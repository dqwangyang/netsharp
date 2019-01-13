package org.netsharp.meta.basebiz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;


public class ToolbarTest {

	IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
	IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);

	@Before
	public void setup() {

	}

	@Test
	public void create() {

		this.datagrid_readonly();
		this.datagrid_edit();
		this.datagrid_row_edit();
		this.datagrid_voucher();

		this.detailPart();

		this.form_readonly();
		this.form_edit();
		this.form_voucher();

		this.tree();
		this.selectVoucherToolBar();
	}

	private void detailPart() {

		ResourceNode node = this.resourceNodeService.byCode("PDatagrid");
		if(node==null) {
			throw new PandaException("未能查询到资源节点："+"PDatagrid");
		}

		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		if(ot1==null) {
			throw new PandaException("未能查询到操作类型");
		}

		OperationType ot2 = operationTypeService.byCode(OperationTypes.update);
		if(ot2==null) {
			throw new PandaException("未能查询到操作类型");
		}

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/datagrid/detail");
			toolbar.setName("子表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);

			toolbar.getItems().add(item);

			//不控制权限
//			item.setOperationType(ot1);
//			item.setOperationType(ot2);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);

			toolbar.getItems().add(item);

			//不控制权限
//			item.setOperationType(ot1);
//			item.setOperationType(ot2);
		}

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			toolbarItem.setSeq((i + 1) * 10);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void datagrid_readonly() {

		ResourceNode node = this.resourceNodeService.byCode("PDatagrid");
		Assert.assertNotNull(node);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/datagrid/readonly");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		// OperationType otView =
		// operationTypeService.byCode(OperationTypes.view);
		// PToolbarItem item = new PToolbarItem();{
		//
		// item.toNew();
		// item.setCode("detail");
		// item.setIcon("fa fa-file-text-o");
		// item.setName("查看");
		// item.setCommand(null);
		// item.setSeq(1);
		// item.setOperationType(otView);
		// toolbar.getItems().add(item);
		// }
		// for (int i = 0; i < toolbar.getItems().size(); i++) {
		// PToolbarItem toolbarItem = toolbar.getItems().get(i);
		// // toolbarItem.setSeq((i+1)*10);
		// toolbarItem.setCommand("{controller}." + toolbarItem.getCode() +
		// "();");
		// }

		service.save(toolbar);
	}

	private void datagrid_row_edit() {

		ResourceNode node = this.resourceNodeService.byCode("PDatagrid");
		Assert.assertNotNull(node);

		OperationType otView = operationTypeService.byCode(OperationTypes.view);
		Assert.assertNotNull(otView);

		OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
		Assert.assertNotNull("", otUpdate);

		OperationType otDelete = operationTypeService.byCode(OperationTypes.delete);
		Assert.assertNotNull("", otDelete);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			//toolbar.setBasePath("panda/datagrid/readonly");
			toolbar.setPath("panda/datagrid/row/edit");
			toolbar.setName("表格操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("detail");
			item.setIcon("fa fa-file-text-o");
			item.setName("查看");
			item.setCommand(null);
			item.setSeq(1);
			item.setOperationType(otView);
			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setName("修改");
			item.setClassName("primary");
			item.setCommand("{controller}.edit()");
			item.setSeq(3);
			toolbar.getItems().add(item);
			item.setOperationType(otUpdate);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setName("删除");
			item.setClassName("normal");
			item.setCommand("{controller}.remove()");
			item.setOperationTypeId(-1L);
			item.setSeq(4);
			toolbar.getItems().add(item);
			item.setOperationType(otDelete);
		}
		service.save(toolbar);
	}

	private void datagrid_edit() {

		ResourceNode node = this.resourceNodeService.byCode("PDatagrid");
		Assert.assertNotNull(node);

		OperationType otAdd = operationTypeService.byCode(OperationTypes.add);
		Assert.assertNotNull("", otAdd);

		OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
		Assert.assertNotNull("", otUpdate);

		OperationType otDelete = operationTypeService.byCode(OperationTypes.delete);
		Assert.assertNotNull("", otDelete);

		OperationType otAttachment = operationTypeService.byCode(OperationTypes.attachment);
		Assert.assertNotNull("", otAttachment);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/datagrid/edit");
			toolbar.setBasePath("panda/datagrid/readonly");
			toolbar.setName("可编辑列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setSeq(2);
			toolbar.getItems().add(item);

			item.setOperationType(otAdd);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("修改");
			item.setCommand(null);
			item.setSeq(3);
			toolbar.getItems().add(item);

			item.setOperationType(otUpdate);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setSeq(4);
			toolbar.getItems().add(item);

			item.setOperationType(otDelete);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("exportExcel");
			item.setIcon("fa fa-download");
			item.setName("导出");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setSeq(5);
			toolbar.getItems().add(item);

			OperationType ot = operationTypeService.byCode(OperationTypes.exportExcel);
			Assert.assertNotNull("", ot);
			item.setOperationType(ot);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("downTemplate");
			item.setIcon("fa fa-file-excel-o");
			item.setName("下载模版");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setSeq(6);
			toolbar.getItems().add(item);

			OperationType ot = operationTypeService.byCode(OperationTypes.importExcel);
			Assert.assertNotNull("", ot);
			item.setOperationType(ot);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("importExcel");
			item.setIcon("fa fa-upload");
			item.setName("导入");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setSeq(7);
			toolbar.getItems().add(item);

			OperationType ot = operationTypeService.byCode(OperationTypes.importExcel);
			Assert.assertNotNull("", ot);
			item.setOperationType(ot);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("attachment");
			item.setIcon("fa fa-link");
			item.setName("附件");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setSeq(99);
			toolbar.getItems().add(item);

			item.setOperationType(otAttachment);
		}
		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			// toolbarItem.setSeq((i+1)*10);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void datagrid_voucher() {

		ResourceNode node = this.resourceNodeService.byCode("PDatagrid");
		Assert.assertNotNull(node);

		OperationType otAudit = operationTypeService.byCode(OperationTypes.audit);
		Assert.assertNotNull("", otAudit);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/datagrid/voucher");
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setName("可编辑列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("audit");
			item.setName("审核");
			item.setIcon("fa fa-check");
			item.setCommand(null);
			item.setSeq(5);
			toolbar.getItems().add(item);
			item.setOperationType(otAudit);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("unaudit");
			item.setIcon("fa fa-close");
			item.setName("弃审");
			item.setCommand(null);
			item.setSeq(6);
			toolbar.getItems().add(item);
			item.setOperationType(otAudit);
		}

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			// toolbarItem.setSeq((i+1)*10);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void form_readonly() {

		ResourceNode node = this.resourceNodeService.byCode("PForm");
		Assert.assertNotNull(node);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/form/readonly");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("first");
			item.setIcon("fa fa-fast-backward");
			item.setName("首张");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setDisabled(true);
			item.setSeq(100);

			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("pre");
			item.setIcon("fa fa-backward");
			item.setName("上张");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setDisabled(true);
			item.setSeq(200);

			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("next");
			item.setIcon("fa fa-forward");
			item.setName("下张");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setDisabled(true);
			item.setSeq(300);

			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("last");
			item.setIcon("fa fa-fast-forward");
			item.setName("末张");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setDisabled(true);
			item.setSeq(400);

			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("excel");
			item.setIcon("fa fa-file-excel-o");
			item.setName("导出");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setDisabled(true);
			item.setSeq(500);

			toolbar.getItems().add(item);

			OperationType otExcel = operationTypeService.byCode(OperationTypes.exportExcel);
			Assert.assertNotNull("", otExcel);
			item.setOperationType(otExcel);
		}

		// item = new PToolbarItem();{
		//
		// item.toNew();
		// item.setCode("set");
		// item.setIcon("fa fa-cog");
		// item.setName("设置");
		// item.setCommand(null);
		// item.setOperationTypeId(-1L);
		// // item.setDisabled(true);
		// item.setSeq(9000);
		//
		// toolbar.getItems().add(item);
		// }

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			// toolbarItem.setSeq((i+1)*10+1000);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void form_edit() {

		ResourceNode node = this.resourceNodeService.byCode("PForm");
		Assert.assertNotNull(node);

		OperationType otAdd = operationTypeService.byCode(OperationTypes.add);
		Assert.assertNotNull("", otAdd);

		OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
		Assert.assertNotNull("", otUpdate);

		OperationType otAttachment = operationTypeService.byCode(OperationTypes.attachment);
		Assert.assertNotNull("", otAttachment);
		// OperationType otDelete =
		// operationTypeService.byCode(OperationTypes.delete);
		// Assert.assertNotNull("", otDelete);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/form/readonly");
			toolbar.setPath("panda/form/edit");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = null;

//		item = new PToolbarItem();
//		{
//
//			item.toNew();
//			item.setCode("add");
//			item.setIcon("fa fa-plus");
//			item.setName("新增");
//			item.setCommand(null);
//			item.setSeq(600);
//			toolbar.getItems().add(item);
//
//			item.setOperationType(otAdd);
//		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("save");
			item.setIcon("fa fa-save");
			item.setName("保存");
			item.setCommand(null);
			item.setSeq(700);
			toolbar.getItems().add(item);

			item.setOperationType(otAdd);
			item.setOperationType2(otUpdate);
		}

		// item = new PToolbarItem();{
		//
		// item.toNew();
		// item.setCode("remove");
		// item.setIcon("icon-remove");
		// item.setName("删除");
		// item.setCommand(null);
		// item.setOperationTypeId(-1L);
		//
		// toolbar.getItems().add(item);
		//
		// item.setOperationType(otDelete);
		// }

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("attachment");
			item.setIcon("fa fa-link");
			item.setName("附件");
			item.setCommand(null);
			item.setSeq(800);
			item.setOperationTypeId(-1L);

			toolbar.getItems().add(item);

			item.setOperationType(otAttachment);
		}
		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			// toolbarItem.setSeq((i+1)*10+2000);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void form_voucher() {

		ResourceNode node = this.resourceNodeService.byCode("PForm");
		Assert.assertNotNull(node);

		OperationType otAudit = operationTypeService.byCode(OperationTypes.audit);
		Assert.assertNotNull("", otAudit);

		OperationType otunAudit = operationTypeService.byCode(OperationTypes.unaudit);
		Assert.assertNotNull("", otunAudit);

		OperationType otPrint = operationTypeService.byCode(OperationTypes.print);
		Assert.assertNotNull("", otPrint);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/form/edit");
			toolbar.setPath("panda/form/voucher");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("audit");
			item.setIcon("fa fa-check");
			item.setName("审核");
			item.setCommand(null);
			item.setSeq(3);
			item.setOperationType(otAudit);
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("unaudit");
			item.setIcon("fa fa-close");
			item.setName("弃审");
			item.setCommand(null);
			item.setSeq(4);
			item.setOperationType(otunAudit);
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setIcon("fa fa-print");
			item.setCode("print");
			item.setName("打印");
			item.setCommand(null);
			item.setSeq(100);
			item.setCommand("{controller}." + item.getCode() + "();");
			item.setOperationType(otPrint);
			toolbar.getItems().add(item);
		}

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			// toolbarItem.setSeq((i+1)*10+3000);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	private void tree() {

		ResourceNode node = this.resourceNodeService.byCode("PForm");
		Assert.assertNotNull(node);

		OperationType otAdd = operationTypeService.byCode(OperationTypes.add);
		Assert.assertNotNull("", otAdd);

		OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
		Assert.assertNotNull("", otUpdate);

		OperationType otDelete = operationTypeService.byCode(OperationTypes.delete);
		Assert.assertNotNull("", otDelete);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/tree");
			toolbar.setName("树部件");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(otAdd);
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("open");
			item.setIcon("fa fa-edit");
			item.setName("修改");
			item.setCommand(null);
			item.setOperationType(otUpdate);
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(otDelete);
			toolbar.getItems().add(item);
		}

		// item = new PToolbarItem();{
		//
		// item.toNew();
		// item.setCode("pathCode");
		// item.setIcon("icon-tree");
		// item.setName("分类路径");
		// item.setCommand(null);
		// item.setOperationTypeId(-1L);
		// toolbar.getItems().add(item);
		// item.setOperationType(otUpdate);
		// }

		for (int i = 0; i < toolbar.getItems().size(); i++) {
			PToolbarItem toolbarItem = toolbar.getItems().get(i);
			toolbarItem.setSeq((i + 1) * 10 + 3000);
			toolbarItem.setCommand("{controller}." + toolbarItem.getCode() + "();");
		}

		service.save(toolbar);
	}

	/**
	 * @Title: createSelectVoucherToolBar
	 * @Description: 创建选单界面工具栏
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void selectVoucherToolBar() {

		ResourceNode node = this.resourceNodeService.byCode("PForm");
		Assert.assertNotNull(node);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("");
			toolbar.setPath("Panda/selectVoucher/toolbar");
			toolbar.setName("选单界面工具栏");
			toolbar.setToolbarType(ToolbarType.BASE);
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("select");
			item.setIcon("fa fa-check");
			item.setName("选择");
			item.setCommand("{controller}.select();");
			item.setOperationTypeId(-1L);

			toolbar.getItems().add(item);
		}
		service.save(toolbar);
	}
}
