package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.datagrid.DataGrid;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.Part;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.util.StringManager;

public class ReportSummaryPart extends Part
{
    protected String filter;
    public DataGrid datagrid = null;
    //protected ReportingTemplate reportingTeplate;

    @Override
    public void initialize()
    {
       // this.reportingTeplate = this.context.ReportingTemplate;

        this.addTip();
        this.initToolbar();
        this.Render();
        this.addJscript();
        super.initialize();
    }

    protected String queryUrl;
    public String getQueryUrl()
    {
    	 if (StringManager.isNullOrEmpty(this.queryUrl))
         {
             queryUrl = "datagrid.query?vid=" + context.getId() + "&Method=Query";
         }
         return queryUrl;
    }

    public String DefaultFilter;

    private void Render()
    {
        datagrid = new DataGrid();
        {
        	datagrid.setId("datagrid" + this.context.getCode());
        	datagrid.pageList = "[5,10,20,30,50,100,200,1000]";
        	datagrid.selectOnCheck = true;
        	datagrid.checkOnSelect = true;
        	datagrid.remoteSort = true;
        	datagrid.nowrap = true;
        	datagrid.striped = true;
        	datagrid.rownumbers = true;
        	datagrid.singleSelect = true;
        	datagrid.pagination = true;
        	datagrid.fitColumns = false;
        	datagrid.toolbar = "#";
        	datagrid.pageSize=20;
        };
//
//        for (SummaryField dcolumn : this.reportingTeplate.SummaryFields)
//        {
//            if (dcolumn.IsVisible)
//            {
//                DataGridColumn column = new DataGridColumn()
//                {
//                    Field = dcolumn.PropertyName,
//                    Width = 100,
//                    Value = dcolumn.Name,
//                    IsVisible = dcolumn.IsVisible,
//                    IsOrderby = dcolumn.OrderbyMode != null,
//                };
//
//                if (dcolumn.PropertyName.IndexOf(".") > 0)
//                {
//                    column.Field = dcolumn.PropertyName.Replace(".", "_");
//                }
//
//                datagrid.Columns.add(column);
//            }
//        }
//
//        this.Controls.add(datagrid);
//
//        ArrayList<String> ss = new ArrayList<String>();
//        if (!StringManager.isNullOrEmpty(context.Filter))
//        {
//            ss.add(context.Filter);
//        }
//
//        //从Url中传递过来的过滤条件
//        String urlFilter = getRequest().QueryString["Filter"];
//        if (!StringManager.isNullOrEmpty(urlFilter))
//        {
//            urlFilter = urlFilter.Replace("__|__", "%");
//            urlFilter = urlFilter.Replace("\\'", "'");
//
//            ss.add(urlFilter);
//        }
//
//        String f = this.DefaultFilter;
//        if (!StringManager.isNullOrEmpty(f))
//        {
//            ss.add(f);
//        }
//
//        this.filter = StringManager.join(" AND ", ss);
//
//        if (StringManager.isNullOrEmpty(filter))
//        {
//            datagrid.Url = this.QueryUrl;
//        }
//        else
//        {
//            datagrid.Url = this.QueryUrl + "&Filter=" + filter;
//        }
    }

    protected void addJscript()
    {
//        this.addJscript("        ", JscriptType.Header);
//
//        this.addJscript("        //管理引用类型表格中显示的问题", JscriptType.Header);
//        this.addJscript("        var ReferenceDictionary = new System.Dictionary();", JscriptType.Header);
//
//        this.addJscript("        //", JscriptType.Header);
//
//        this.addJscript("        var " + getJsInstance() + " = new " + getJsController() + "();", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.Name='" + this.context.getCode() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.IdReportingTeplate='" + this.reportingTeplate.Id + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".workspaceId='" + this.context.getIdWorkspace() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.vid='" + this.context.getId() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.isTip=" + (!StringManager.isNullOrEmpty(this.context.getMemoto())) + ";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.EntityId='" + this.context.getEntityId()+ "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.service='" + this.getClass().getName() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.Id='" + datagrid.Id + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".datagrid=$('#" + datagrid.Id + "');", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".datagrid.controller=" + getJsInstance() + ";", JscriptType.Header);
//
//        //
//        this.addJscript("        " + getJsInstance() + ".context.queryUrl='" + this.getQueryUrl() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.defaultFilter='" + this.filter + "';", JscriptType.Header);
    }


    /// <summary>
    /// 查询
    /// </summary>
    /// <returns></returns>
    public String Query()
    {
//        ISummaryReportService service = ServiceFactory.Create<ISummaryReportService>();
//
//        this.reportingTeplate = this.context.ReportingTemplate;
//
//        Paging paging = null;
//
//        String strPageIndex = getRequest().Form["page"];
//        String sort = getRequest().Form["sort"];
//        String order = getRequest().Form["order"];
//        String rows = getRequest().Form["rows"];
//
//        if (!StringManager.isNullOrEmpty(strPageIndex))
//        {
//            int pageInex = StringManager.isNullOrEmpty(strPageIndex) ? 1 extends int.Parse(strPageIndex);
//            int pageCount = StringManager.isNullOrEmpty(rows) ? 30 extends int.Parse(rows);
//
//            paging = new Paging()
//            {
//                PageIndex = pageInex,
//                PageCount = pageCount
//            };
//        }
//
//        //
//        String filter = getRequest().QueryString["Filter"];
//        if (!StringManager.isNullOrEmpty(filter))
//        {
//            filter = filter.Replace("__|__", "%");
//            filter = filter.Replace("\\'", "'");
//        }
//
//        ArrayList<String> filters = new ArrayList<String>();
//        if (!StringManager.isNullOrEmpty(filter))
//        {
//            filters.add(filter);
//        }
//        if (!StringManager.isNullOrEmpty(this.DefaultFilter))
//        {
//            filters.add(this.DefaultFilter);
//        }
//        filter = StringManager.join(" and ", filters.ToArray());
//
//        Oql oql = new Oql() 
//        {
//            Selects = this.reportingTeplate.SummarySelectFields,
//            EntityId = this.context.EntityId,
//            Filter = filter,
//            Paging=paging,
//        };
//
//        NDataTable table= service.QueryDataTable(oql, this.reportingTeplate);
//
//        Mtable meta = MtableManager.getMtable(this.reportingTeplate.EntityId);
//        table.ParseEnumItem(meta);
//
//        DataTableJsonSerializor jsonSerializor = new DataTableJsonSerializor();
//        String json = jsonSerializor.Seralize(table).Replace("～！@", ",");
//
//        return json;
    	
    	return null;
    }
    
	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.summary.report.list.js"));
	}
}