package org.netsharp.wx.meta.pa.biz;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.Fans;

public class FansWorkspaceTest extends WorkspaceCreationBase{
	
	@Override
	@Before
	public void setup() {

		urlList = "/wx/pa/fans/list";
		entity = Fans.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = Fans.class.getSimpleName();
	}
	
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(10);
			datagrid.setOrderby("createTime desc");
		}

		addColumn(datagrid, "openId", "openId", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "nickname", "昵称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "sex", "性别", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "sceneId", "首次关注参数", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "subscribeDate", "首次关注时间", ControlTypes.DATETIME_BOX, 150, false);
		addColumn(datagrid, "lastSubscribeSceneId", "最近关注参数", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastSubscribeDate", "最后关注时间", ControlTypes.DATETIME_BOX, 150, false);

		addColumn(datagrid, "lastScanSceneId", "最近浏览参数", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastScanDate", "最近浏览时间", ControlTypes.DATETIME_BOX, 150, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false);
		
		return datagrid;
	}
    
	@Override
	protected PQueryProject createQueryProject(ResourceNode node){
    	
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "openId", "openId", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "nickname", "昵称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sceneId", "sceneId", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "lastSceneId", "lastSceneId", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "subscribeDate", "关注时间", ControlTypes.DATE_BOX);
		return queryProject;
    }

	
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
