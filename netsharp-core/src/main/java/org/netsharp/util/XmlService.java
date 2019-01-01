package org.netsharp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlService {
	
	private static Log logger = null;
	
	public static boolean ConstinsAttribute(Element node, String attributeName)
    {
		Attr att=node.getAttributeNode(attributeName);
        return att != null;
    }

    public static void AddAttribute(Document doc,Element node, String name, String value)
    {
        if (!ConstinsAttribute(node, name))
        {
            if (!StringManager.isNullOrEmpty(value))
            {
                Attr attr = doc.createAttribute(name);
                attr.setNodeValue(value);
                node.appendChild(attr);
            }
        }
    }

    public static Element CreateSubNode(Document doc,Element node, String subNodeName)
    {
        Element subNode = doc.createElement(subNodeName);

        node.appendChild(subNode);

        return subNode;
    }
    
    public static Document read(InputStream stream){
    	
    	try{
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		Document document = db.parse(stream);
    		
    		return document;
    	}
    	catch(Exception ex){
    		
    		if(logger==null){
    			logger = LogFactory.getLog(XmlService.class);
    		}
    		logger.error("读取xml内容异常",ex);
    		
    		return null;
    	}
    }
    
    public static Document read(String xml){
    	
    	try{
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		Document document = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    		
    		return document;
    	}
    	catch(Exception ex){
    		
    		if(logger==null){
    			logger = LogFactory.getLog(XmlService.class);
    		}
    		logger.error("读取xml内容异常",ex);
    		
    		return null;
    	}
    }
    
    public static Document createDocument(){
    	
    	try{
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		Document doc=db.newDocument();
    		return doc;
    	}
    	catch(Exception ex){
    		
    		if(logger==null){
    			logger = LogFactory.getLog(XmlService.class);
    		}
    		logger.error("创建Xml Document异常",ex);
    		
    		return null;
    	}
    }
}
