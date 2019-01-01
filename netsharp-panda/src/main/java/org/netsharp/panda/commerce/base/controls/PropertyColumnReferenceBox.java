package org.netsharp.panda.commerce.base.controls;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableReference;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.ReferenceBoxColumn;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.util.StringManager;

public class PropertyColumnReferenceBox extends PropertyColumnBase {
	@Override
	public DataGridColumn create(PDatagridColumn dcolumn) {
		PReference pr = dcolumn.getReference();

		IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
		pr = referenceService.byId(dcolumn.getReferenceId());
		dcolumn.setReference(pr);

		if (pr.getDataGrid() == null) {
			IPDatagridService service = ServiceFactory.create(IPDatagridService.class);
			PDatagrid pdatagrid = service.byId(pr.getDatagridId());
			pr.setDataGrid(pdatagrid);
		}

		// String pName = pr.getIntelligentFields();
		// String pCode = pr.getCode();

		String[] propertys = dcolumn.getPropertyName().split("\\.");
		String refProperty = propertys[0];
		String textField = propertys[1];
		String entityId = dcolumn.getDatagrid().getEntityId();
		if (StringManager.isNullOrEmpty(entityId)) {
			throw new PandaException(dcolumn.getPropertyName() + "对应的entityId为空");
		}
		Mtable meta = MtableManager.getMtable(entityId);
		TableReference referenceMeta = meta.getReference(refProperty);
		if(referenceMeta==null){
			referenceMeta = meta.getCompositeOne(refProperty);
		}
		if(referenceMeta==null){
			throw new PandaException("引用不正确："+meta.getEntityId() + "."+refProperty);
		}
		ReferenceBoxColumn referenceBoxColumn = new ReferenceBoxColumn();
		{
			referenceBoxColumn.field = referenceMeta.getForeignProperty();// 使用外键作为easyui的field,但是界面显示的是name怎么办？
			referenceBoxColumn.foreignId = refProperty + "." + referenceMeta.getToTable().getKeyColumn().getPropertyName();// 赋值field使用
			referenceBoxColumn.textField = textField;
			referenceBoxColumn.foreignName = dcolumn.getPropertyName();// 外键的显示字段
			referenceBoxColumn.required = dcolumn.isRequired();
			referenceBoxColumn.setPrefernece(dcolumn.getReference());
			if (!StringManager.isNullOrEmpty(dcolumn.getRefFilter())) {

				referenceBoxColumn.filter = dcolumn.getRefFilter().replaceAll("'", "----").replace("=", "____");
			}
			referenceBoxColumn.formatter = "function(value,rowData,rowIndex){var text = ReferenceDictionary.byKey('" + referenceBoxColumn.field + "'+value);if(text){return text;}else if(rowData." + refProperty + "){return rowData." + refProperty + "." + textField + ";} return '';}";
		}
		;

		this.render(referenceBoxColumn, dcolumn);
		return referenceBoxColumn;
	}
}
