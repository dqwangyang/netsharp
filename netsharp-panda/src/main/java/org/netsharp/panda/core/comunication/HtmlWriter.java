package org.netsharp.panda.core.comunication;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.StringManager;

public class HtmlWriter implements IHtmlWriter
{
	private StringBuilder htmlBuilder = new StringBuilder();

	protected static Log logger = LogFactory.getLog(HtmlWriter.class);
	
    public Writer writer;
    
    public HtmlWriter(Writer writer){
    	this.writer=writer;
    }
    
    @Override
    public void write(String html)
    {
        try {
        	this.htmlBuilder.append(html + StringManager.NewLine);
			this.writer.write(html + StringManager.NewLine);
			
//			logger.info(html);
			
		} catch (IOException e) {
			logger.error("htmlwriter",e);
		}
    }
    
    @Override
    public void clearWriteHtml(){
    	this.htmlBuilder = new StringBuilder();
    }
    
    @Override
    public String getWriteHtml(){
    	return this.htmlBuilder.toString();
    }
}