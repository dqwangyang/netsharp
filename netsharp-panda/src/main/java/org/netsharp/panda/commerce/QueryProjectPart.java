package org.netsharp.panda.commerce;

import org.netsharp.panda.core.Part;

public class QueryProjectPart extends Part
{
//    QueryPanel query;
//
//    @Override
//    public void initialize()
//    {
//        this.initToolbar();
//        this.render();
//        this.addJscript();
//
//        super.initialize();
//    }
//
//    protected void render()
//    {
//        if (this.context.getParent(null) == null)
//        {
//            return;
//        }
//
//        query = new QueryPanel();
//        {
//        	query.QueryProject = this.context.getParent(null).getQuerySolution();
//        };
//
//        this.getControls().add(query);
//    }
//
//    protected void addJscript()
//    {
//    	PQueryProject queryProject =this.context.getParent(null).getQuerySolution().getDefaultProject();
//    	
//        this.addJscript("        ", JscriptType.Header);
//        this.addJscript("        //", JscriptType.Header);
//
//        this.addJscript("        var " + getJsInstance() + " = new " + getJsController() + "();", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.name='" + this.context.getName() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.vid='" + this.context.getId() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.queryProjectId='" + queryProject.getId() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.entityId='" + context.getEntityId() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.service='" + this.getClass().getName() + "';", JscriptType.Header);
//        this.addJscript("        " + getJsInstance() + ".context.id='" + query.Id + "';", JscriptType.Header);
//    }
//
//    @Override
//    protected void importJs(IHtmlWriter writer)
//    {
//        super.importJs(writer);
//        writer.write("  <style>.layout-panel-west .panel-header{border-top:0px;}</style>");
//    }
    
}