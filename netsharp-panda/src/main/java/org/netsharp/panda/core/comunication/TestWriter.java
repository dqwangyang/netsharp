package org.netsharp.panda.core.comunication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestWriter implements IHtmlWriter
{
	protected static Log logger = LogFactory.getLog(TestWriter.class);
	
    public void write(String html)
    {
	    logger.info(html);
    }
    
    @Override
    public void clearWriteHtml(){
    	
    }
    
    @Override
    public String getWriteHtml(){
    	return "";
    }
}