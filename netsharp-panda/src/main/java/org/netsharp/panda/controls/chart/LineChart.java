package org.netsharp.panda.controls.chart;

import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class LineChart extends Div
{
    public LineData LineData;

    @Override
    public void render(IHtmlWriter writer)
    {
        super.render(writer);

        writer.write("<script type='text/javascript' src='/Javascript/Flotr2-master/flotr2.min.js'></script>");
        writer.write("    <script type='text/javascript'>");
        writer.write("");
        writer.write("      (function () {");
        writer.write("          var container = document.getElementById('"+this.getId()+"');");
        writer.write("          var graph;");
        writer.write("");

        for (int lineIndex = 0; lineIndex < this.LineData.Lines.size(); lineIndex++)
        {
            Line line = this.LineData.Lines.get(lineIndex);
            writer.write("          var data"+lineIndex+" = [];");

            for (int itemIndex = 0; itemIndex < line.Items.size(); itemIndex++)
            {
                LineItem item = line.Items.get(itemIndex);
                writer.write("          data" + lineIndex + ".push([" + item.X + ", " + item .Y+ "]);");
            }

            writer.write("");
        }


        writer.write("          graph = Flotr.draw("+this.getId()+", ");
        writer.write("[");
        for (int lineIndex = 0; lineIndex < this.LineData.Lines.size(); lineIndex++)
        {
            Line line = this.LineData.Lines.get(lineIndex);
            writer.write("    { data: d" + lineIndex + ", label: '" + line.Name + "' },");
        }
        writer.write("], {");

        writer.write("              yaxis: {");
        writer.write("                  max: "+this.LineData.YMax+",");
        writer.write("                  min: "+this.LineData.YMin+"");
        writer.write("              }");
        writer.write("          });");
        writer.write("      })();");
        writer.write("</script>");
    }
}

