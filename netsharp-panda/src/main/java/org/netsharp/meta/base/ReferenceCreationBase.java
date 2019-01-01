package org.netsharp.meta.base;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ReferenceCreationBase {

	
	protected String resourceNodeCode =  null;
	
	protected String datagridName =  null;
	
	protected String referenceName = null;
	
	protected String referenceCode = null;
	
	protected String gridFilter = null;
	
	protected String filterBuilder = null;
	
	protected String filter = null;
	
	protected IntelligentMode intelligentMode = IntelligentMode.LIKE;
	
	protected String intelligentFields = "";
	
	protected boolean canNew = false;
	
	protected String popupUrl = "";
	
	protected Integer width = 0;
	
	protected Integer height = 0;
	
    protected Integer panelWidth = 425;

    protected Integer panelHeight = 310;
	
	protected IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
	
	protected IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void run() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PDatagrid pdatagrid = this.createDatagrid(node);
		PReference pr = new PReference();
		{
			pr.toNew();
			pr.setCode(referenceCode);
			pr.setResourceNode(node);
			pr.setName(referenceName);
			pr.setDataGrid(pdatagrid);
			pr.setIntelligentMode(intelligentMode);
			pr.setIntelligentFields(intelligentFields);
			pr.setFilterBuilder(filterBuilder);
			pr.setFilter(filter);
			pr.setCanNew(canNew);
			pr.setPopupUrl(popupUrl);
			pr.setWidth(width);
			pr.setHeight(height);
			pr.setPanelHeight(panelHeight);
			pr.setPanelWidth(panelWidth);
		}

		referenceService.save(pr);
	}
	
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = new PDatagrid();
		{
			datagrid.toNew();
			datagrid.setPagination(true);
			datagrid.setResourceNode(node);
			datagrid.setFilter(gridFilter);
			datagrid.setName(datagridName);
		}
		
		return datagrid;
	}
	
	protected PDatagridColumn addColumn(PDatagrid datagrid, String propertyName, String header, ControlTypes controlType, int width,
			OrderbyMode orderbyMode,boolean frozen) {

		PDatagridColumn column = new PDatagridColumn();
		{
			column.toNew();
			column.setPropertyName(propertyName);
			column.setHeader(header);
			column.setControlType(controlType);
			column.setWidth(width);
			column.setFrozen(frozen);
			column.setOrderbyMode(orderbyMode);
		}
		datagrid.getColumns().add(column);
		return column;
	}
}
