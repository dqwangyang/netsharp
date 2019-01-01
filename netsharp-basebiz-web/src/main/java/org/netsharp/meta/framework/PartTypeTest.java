package org.netsharp.meta.framework;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.panda.base.IPPartTypeService;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.panda.commerce.FormDetailPart;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.panda.commerce.OptionFormPart;
import org.netsharp.panda.commerce.QueryProjectPart;
import org.netsharp.panda.commerce.ReferenceFormPart;
import org.netsharp.panda.commerce.ReportChartPart;
import org.netsharp.panda.commerce.ReportListPart;
import org.netsharp.panda.commerce.SelectVoucherListDetailPart;
import org.netsharp.panda.commerce.SelectVoucherListPart;
import org.netsharp.panda.commerce.TreePart;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.panda.core.Part;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PPartType;

public class PartTypeTest {

	IPPartTypeService partService = ServiceFactory.create(IPPartTypeService.class);

	@Before
	public void setup() {
		Mtable meta = MtableManager.getMtable(PPartType.class);
		IDb db = DbFactory.create();
		db.reCreateTable(meta);
	}

	@Test
	public void add() {

		PPartType part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.FORM_PART.getId());
			part.setCode(PartType.FORM_PART.name());
			part.setName(PartType.FORM_PART.getText());
			part.setJsController(FormPart.class.getName());
			part.setServiceController(FormPart.class.getName());
			part.setToolbar("Panda/Part/Form/Save/Toobar");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.REPORT_OR_CHART.getId());
			part.setCode(PartType.REPORT_OR_CHART.name());
			part.setName(PartType.REPORT_OR_CHART.getText());
			part.setJsController(ReportChartPart.class.getName());
			part.setServiceController(ReportChartPart.class.getName());
			part.setToolbar("Panda/Part/Reporting/Toolbar");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.DATAGRID_PART.getId());
			part.setCode(PartType.DATAGRID_PART.name());
			part.setName(PartType.DATAGRID_PART.getText());
			part.setJsController(ListPart.class.getName());
			part.setServiceController(ListPart.class.getName());
			part.setToolbar("Panda/Part/EntityList/Toolbar");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.REFERENCE_FORM_PART.getId());
			part.setCode(PartType.REFERENCE_FORM_PART.name());
			part.setName(PartType.REFERENCE_FORM_PART.getText());
			part.setJsController(ReferenceFormPart.class.getName());
			part.setServiceController(ReferenceFormPart.class.getName());
			part.setToolbar("Panda/Part/Form/Save/Toobar");
			part.setDockStyle(DockType.BOTTOM);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.TREE_PART.getId());
			part.setCode(PartType.TREE_PART.name());
			part.setName(PartType.TREE_PART.getText());
			part.setJsController(TreePart.class.getName());
			part.setServiceController(TreePart.class.getName());
			part.setStyle("width:250px");
			part.setToolbar("Panda/Part/Tree/Toolbar");
			part.setDockStyle(DockType.LEFT);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.QUERY_SOLUTION.getId());
			part.setCode(PartType.QUERY_SOLUTION.name());
			part.setName(PartType.QUERY_SOLUTION.getText());
			part.setJsController(QueryProjectPart.class.getName());
			part.setServiceController(QueryProjectPart.class.getName());
			part.setStyle("width:250px");
			part.setToolbar("Panda/Part/QuerySolution/Toolbar");
			part.setDockStyle(DockType.RIGHT);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.DETAIL_PART.getId());
			part.setCode(PartType.DETAIL_PART.name());
			part.setName(PartType.DETAIL_PART.getText());
			part.setJsController(DetailPart.class.getName());
			part.setServiceController(DetailPart.class.getName());
			part.setToolbar("Panda/Part/DetailList/Toobar");
			part.setDockStyle(DockType.BOTTOM);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.TREEGRID_PART.getId());
			part.setCode(PartType.TREEGRID_PART.name());
			part.setName(PartType.TREEGRID_PART.getText());
			part.setJsController(TreegridPart.class.getName());
			part.setServiceController(TreegridPart.class.getName());
			part.setToolbar("Panda/Part/DetailList/Toobar");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.TREEGRID_REPORT_PART.getId());
			part.setCode(PartType.TREEGRID_REPORT_PART.name());
			part.setName(PartType.TREEGRID_REPORT_PART.getText());
			part.setJsController(ReportListPart.class.getName());
			part.setServiceController(ReportListPart.class.getName());
			part.setToolbar("Panda/Part/DetailList/Toobar");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.SELECT_VOUCHER_PART.getId());
			part.setCode(PartType.SELECT_VOUCHER_PART.name());
			part.setName(PartType.SELECT_VOUCHER_PART.getText());
			part.setJsController(SelectVoucherListPart.class.getName());
			part.setServiceController(SelectVoucherListPart.class.getName());
			part.setToolbar("Panda/selectVoucher/toolbar");
			part.setDockStyle(DockType.TOP);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.SELECT_VOUCHER_DETAIL_PART.getId());
			part.setCode(PartType.SELECT_VOUCHER_DETAIL_PART.name());
			part.setName(PartType.SELECT_VOUCHER_DETAIL_PART.getText());
			part.setJsController(SelectVoucherListDetailPart.class.getName());
			part.setServiceController(SelectVoucherListDetailPart.class.getName());
			part.setToolbar("");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);
		
		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.OPTION_FORM_PART.getId());
			part.setCode(PartType.OPTION_FORM_PART.name());
			part.setName(PartType.OPTION_FORM_PART.getText());
			part.setJsController(OptionFormPart.class.getName());
			part.setServiceController(OptionFormPart.class.getName());
			part.setToolbar("");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);

		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.CUSTOM.getId());
			part.setCode(PartType.CUSTOM.name());
			part.setName(PartType.CUSTOM.getText());
			part.setServiceController(Part.class.getName());
		}
		partService.save(part);
		
		part = new PPartType();
		{
			part.toNew();
			part.setId(PartType.FORM_DETAIL_PART.getId());
			part.setCode(PartType.FORM_DETAIL_PART.name());
			part.setName(PartType.FORM_DETAIL_PART.getText());
			part.setJsController(FormDetailPart.class.getName());
			part.setServiceController(FormDetailPart.class.getName());
			part.setToolbar("");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		partService.save(part);
	}
}
