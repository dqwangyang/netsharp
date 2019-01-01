package org.netsharp.panda.controls.chart;


import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class PieChart extends Div
{
    public PieData[] PieDatas;

    @Override
    public void initialize()
    {
        this.setId("pieChart");
        this.setStyle("width : 600px;height： 384px; margin: 8px auto;");

        super.initialize();
    }

    @Override
    public void render(IHtmlWriter writer)
    {
        super.render(writer);

        if (PieDatas == null)
        {
            return;
        }

        writer.write("<script type='text/javascript' src='/Javascript/Flotr2-master/flotr2.min.js'></script>");
        writer.write("    <script type='text/javascript'>");

        writer.write("        (function basic_pie(" + this.getId() + ") {");

        writer.write("            var ");

        for (int i = 0; i < this.PieDatas.length; i++)
        {
            PieData item = this.PieDatas[i];
            writer.write("    d" + i + " = [[0, " + item.Value + "]],");
        }

        writer.write("    graph;");
        writer.write("            graph = Flotr.draw(" + this.getId() + ", [");

        for (int i = 0; i < this.PieDatas.length; i++)
        {
            PieData item = this.PieDatas[i];

            if (item.IsPie)
            {
                writer.write("    { data: d" + i + ", label: '" + item.Name + "',");
                writer.write("        pie: {");
                writer.write("            explode: 50");
                writer.write("        }");
                writer.write("    },");
            }
            else
            {
                writer.write("    { data: d" + i + ", label: '" + item.Name + "' },");
            }
        }

        //writer.Write("    { data: d1, label: 'Comedy' },");
        //writer.Write("    { data: d2, label: 'Action' },");
        //writer.Write("    { data: d3, label: 'Romance',");
        //writer.Write("        pie: {");
        //writer.Write("            explode: 50");
        //writer.Write("        }");
        //writer.Write("    },");
        //writer.Write("    { data: d4, label: 'Drama' }");

        writer.write("  ], {");
        writer.write("      HtmlText: false,");
        writer.write("      grid: {");
        writer.write("          verticalLines: false,");
        writer.write("          horizontalLines: false");
        writer.write("      },");

        writer.write("      xaxis: { showLabels: false },");
        writer.write("      yaxis: { showLabels: false },");
        writer.write("      pie: {");
        writer.write("          show: true,");
        writer.write("          explode: 6");
        writer.write("      },");
        writer.write("      mouse: { track: true },");
        writer.write("      legend: {");
        writer.write("          position: 'se',");
        writer.write("          backgroundColor: '#D2E8FF'");
        writer.write("      }");
        writer.write("  });");
        writer.write("})(document.getElementById('" + this.getId() + "'));");
        writer.write("    </script>");
    }
}
