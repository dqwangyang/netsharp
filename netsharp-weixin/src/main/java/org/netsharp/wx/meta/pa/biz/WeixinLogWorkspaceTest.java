package org.netsharp.wx.meta.pa.biz;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.WeixinLog;

public class WeixinLogWorkspaceTest extends WorkspaceCreationBase{

	
	@Override
	@Before
	public void setup() {

		urlList = "/wx/pa/wxlog/list";
		entity = WeixinLog.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = WeixinLog.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(10);
			datagrid.setOrderby("createTime desc");
		}

		addColumn(datagrid, "fromUserName", "openId", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "hit", "访问命中", ControlTypes.BOOLCOMBO_BOX, 100, false);
		addColumn(datagrid, "msgType", "消息类型", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "keywords", "消息内容", ControlTypes.TEXT_BOX, 300, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false);

		return datagrid;
	}
    
	@Override
	protected PQueryProject createQueryProject(ResourceNode node){
    	
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "fromUserName", "openId", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sceneId", "sceneId", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "msgType", "消息类型", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "keywords", "消息内容", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "hit", "命中", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "createTime", "访问时间", ControlTypes.DATE_BOX);
		return queryProject;
    }

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
