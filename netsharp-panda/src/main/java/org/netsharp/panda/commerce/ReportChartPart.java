package org.netsharp.panda.commerce;

import org.netsharp.panda.core.Part;

public class ReportChartPart extends Part
{
    @Override
    public void initialize()
    {
        super.initialize();
//
//        ReportingTemplate grusReporting= this.context.ReportingTemplate;
//
//        if (grusReporting.ChartType == ReportingChartType.Pie)
//        {
//            PieChart pie = new PieChart() 
//            { 
//                Id="pieChart",
//                Style ="width extends 600px;height: 384px; margin: 8px auto;",
//            };
//
//            //
//            IEnumService service = ServiceFactory.Create<IEnumService>();
//            Oql oql = new Oql()
//            {
//                EntityId = this.context.EntityId,
//                Selects = grusReporting.ChartSelectFields,
//                Groupby=grusReporting.ChartGroupField.PropertyName,
//            };
//            ITable table= service.QueryTable(oql);
//
//            //
//            ArrayList<PieData> datas = new ArrayList<PieData>();
//
//            for (IRow row : table)
//            {
//                PieData item=new PieData();
//
//                Object value=row[grusReporting.ChartGroupField.PropertyName];
//                if(value==null)
//                {
//                    item.Name = "未知";
//                }
//                else
//                {
//                    item.Name = value.toString();
//                }
//                
//                value=row[grusReporting.ChartSummaryField.PropertyName];
//                if (value != null)
//                {
//                    item.Value = BigDecimal.Parse(value.toString());
//                }
//                else
//                {
//                    item.Value = 0;
//                }
//
//                datas.add(item);
//            }
//
//            pie.PieDatas = datas.ToArray();
//            this.Controls.add(pie);
//        }
//        else if (grusReporting.ChartType == ReportingChartType.Line)
//        {
//            LineChart pie = new LineChart()
//            {
//                Id = "lineChart",
//                Style = "width extends 600px;height: 384px; margin: 8px auto;",
//            };
//
//            //
//            ArrayList<String> groupbys = new ArrayList<String>();
//            groupbys.add(grusReporting.ChartXField.Expression);
//            if (grusReporting.ChartGroupField != null)
//            {
//                groupbys.add(grusReporting.ChartGroupField.PropertyName);
//            }
//
//            IEnumService service = ServiceFactory.Create<IEnumService>();
//            Oql oql = new Oql()
//            {
//                EntityId = this.context.EntityId,
//                Selects = grusReporting.ChartSelectFields,
//                Groupby = StringManager.join(",",groupbys),
//            };
//
//            ITable table = service.QueryTable(oql);
//
//            //
//            HashMap<String,ArrayList<IRow>> lineRows=new HashMap<String,ArrayList<IRow>>();
//            for (IRow row : table)
//            {
//                String lineText = MtableManager.getMtable(grusReporting.EntityId).Name;
//
//                if (grusReporting.ChartGroupField != null)
//                {
//                    lineText=(String)row[grusReporting.ChartGroupField.PropertyName];
//                }
//
//                if (StringManager.isNullOrEmpty(lineText))
//                {
//                    lineText = "未知";
//                }
//
//                ArrayList<IRow> list = null;
//                if (!lineRows.TryGetValue(lineText,out list))
//                {
//                    list = new ArrayList<IRow>();
//
//                    lineRows.add(lineText, list);
//                }
//
//                list.add(row);
//            }
//
//            pie.LineData = new LineData()
//            { 
//                Lines=new ArrayList<Line>(),
//            };
//            for (KeyValuePair<String, ArrayList<IRow>> kv : lineRows)
//            {
//                Line line = new Line() 
//                { 
//                    Name=kv.Key,
//                    Items=new ArrayList<LineItem>(),
//                };
//
//                pie.LineData.Lines.add(line);
//
//                for (IRow row : kv.Value)
//                {
//                    Object x=row[grusReporting.ChartXField.PropertyName];
//                    Object y=row[grusReporting.ChartSummaryField.PropertyName];
//
//                    
//                    LineItem item = new LineItem(x.toString(),y.toString());
//
//                    line.Items.add(item);
//                }
//            }
//
//            this.Controls.add(pie);
//        }
//        else if (grusReporting.ChartType == ReportingChartType.Histogram)
//        {
//            PieChart pie = new PieChart();
//
//            this.Controls.add(pie);
//        }
//
//        this.addJscript();
    }

    public String QueryUrl;
    public String DefaultFilter;

    protected void addJscript()
    {
//        this.addJscript("        ", JscriptType.Header);
//
//        this.addJscript("        //管理引用类型表格中显示的问题", JscriptType.Header);
//        this.addJscript("        var ReferenceDictionary = new System.Dictionary();", JscriptType.Header);
//
//        this.addJscript("        //", JscriptType.Header);
//
//        this.addJscript("        var " + getJsInstance() + " = new " + JsController + "();", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.Name=\"" + this.Context.Name + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.vid=\"" + this.Context.Id + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.isTip=" + (!StringManager.isNullOrEmpty(this.context.Memoto)).toString().ToLower() + ";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".workspaceId=\"" + this.context.workspaceId + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.EntityId=\"" + this.context.EntityId + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.service=\"" + this.getClass().FullName + "\";", JscriptType.Header);
//        //this.addJscript("        " + getJsInstance() + ".context.Id=\"" + datagrid.Id + "\";", JscriptType.Header);
//        //this.addJscript("        " + getJsInstance() + ".datagrid=$('#" + datagrid.Id + "');", JscriptType.Header);
//        //this.addJscript("        " + getJsInstance() + ".datagrid.controller=" + getJsInstance() + ";", JscriptType.Header);
//
//
//        this.addJscript("        " + getJsInstance() + ".context.queryUrl=\"" + this.QueryUrl + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.defaultFilter=\"" + this.DefaultFilter + "\";", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.FormUrl=\"" + this.Context.Url + "\";", JscriptType.Header);
//
//        //this.addJscript("           var pHeight = $(\"#datagrid\").parent().css(\"height\");", JscriptType.Initialize);
//        //this.addJscript("           $(\".easyui-datagrid\").datagrid({ \"height\": Number(pHeight.subString(0, pHeight.length - 2))-32 });", JscriptType.Initialize);

    }
}
