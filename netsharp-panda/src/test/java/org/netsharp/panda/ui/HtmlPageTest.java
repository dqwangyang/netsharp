package org.netsharp.panda.ui;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.netsharp.panda.core.HtmlPage;
import org.netsharp.panda.core.HtmlPageFactory;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.TestRequest;
import org.netsharp.panda.core.comunication.TestWriter;

public class HtmlPageTest {
	
    @Test
    public void orderlist(){
    	
    	String url="/salesorder/list";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page= HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
        
    }
    
    @Test
    public void cartype(){
    	
    	String url="/cartype/tree";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page=HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
        
    }
    
    @Test
    public void orderForm(){
    	
    	String url="/salesorder/form";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page=HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
        
    }
    @Test
    public void listForm(){
    	
    	String url="/datagrid/form";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page=HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
    }
    @Test
    public void formForm(){
    	
    	String url="/form/form";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page=HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
    }
    
    @Test
    public void treegrid(){
    	
    	String url="/resourceNode/tree2";
    	Map<String,String> pars=new HashMap<String,String>();
    	
		HttpContext ctx=new HttpContext();
		{
			ctx.setRequest(new TestRequest(pars));
			HttpContext.setCurrent(ctx);
		}

		HtmlPage page=HtmlPageFactory.create(url);
		
		page.initialize();
        page.render(new TestWriter());
    }
}
