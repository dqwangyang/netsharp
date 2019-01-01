package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.attachment.Attachment;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

public class AttachmentWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		this.urlList = "/system/attachment/list";
		this.entity = Attachment.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = entity.getSimpleName();
		formPartName = listPartName = meta.getName();
		
		this.listToolbarPath = "panda/datagrid/readonly";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setFilter(listFilter);
			datagrid.setSortName(" id desc");
			datagrid.setReadOnly(true);
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "fileExtend", "扩展名", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "downLoadCount", "下载次数", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "alias", "操作", ControlTypes.TEXT_BOX, 100);
		{
			column.setAlign(DatagridAlign.CENTER);
//			StringBuilder builder = new StringBuilder();
//			{
//
//				builder.append("var display = '';");
//				builder.append("if (row.fileExtend.toLowerCase() == '.jpg' || row.fileExtend.toLowerCase() == '.png'){");
//				builder.append("	display = '<a href=download?name='+row.name+'&path='+row.path+'&display=true target=_blank >查看</a>&nbsp;\";");
//				builder.append("};");
//				builder.append("return display + '<a href=/download?name='+row.name+'&path='+row.path+' onclick='listController.updateDownLoadCount(\"+row.id+\");'>下载</a>\";");
//			}
//			column.setFormatter(builder.toString());
		}
		column = addColumn(datagrid, "creator", "上传人", ControlTypes.TEXT_BOX, 100);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "createTime", "上传时间", ControlTypes.DATETIME_BOX, 130, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		return datagrid;
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.delete);
	}
}
