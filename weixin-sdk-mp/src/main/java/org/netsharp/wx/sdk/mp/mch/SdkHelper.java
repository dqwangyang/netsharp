package org.netsharp.wx.sdk.mp.mch;

import java.lang.reflect.Field;
import java.util.Map;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.SAXWriter;
import org.netsharp.util.XmlManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SdkHelper {
	
    public static String writeXml(Map<String,String> map){
    	
    	SAXWriter writer=new SAXWriter();
		writer.startDocument();
		
		writer.startElement("xml");

		for(String key : map.keySet()){
			writer.startElement(key);
			writer.writeCData(map.get(key));
			writer.endElement(key);
		}
		
		writer.endElement("xml");
		writer.endDocument();
		
		String xml = writer.toString();
		
		return xml;
    }
    
    public static <T extends MchResponse> T  deserialize(Class<?> type,String xml){
    	
    	@SuppressWarnings("unchecked")
		T obj = (T)ReflectManager.newInstance(type);
    	
    	 Document doc= XmlManager.parseXml(xml);
         Element root=doc.getDocumentElement();
         
         NodeList nodes = root.getChildNodes();
         
         for(int i=0;i<nodes.getLength();i++){
        	 Node node = nodes.item(i);
        	 
        	 String fieldName= node.getNodeName();
        	 String fieldValue = node.getTextContent();
        	 
        	 if(fieldName.equals("#text")){
        		 continue;
        	 }
        	 
        	 Field f =ReflectManager.getField(type, fieldName);
        	 if(f==null){
        		 continue;
        	 }
        	 f.setAccessible(true);
        	 
        	 ITypeConvertor tc = TypeConvertorFactory.create(f.getType());
        	 Object value = tc.fromString(fieldValue);
        	 
        	 ReflectManager.set(f, obj, value);
        			 
         }
         
         return obj;
    }
}
