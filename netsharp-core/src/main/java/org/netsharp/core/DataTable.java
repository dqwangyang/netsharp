package org.netsharp.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.netsharp.util.SAXWriter;

public class DataTable extends Table<Row> {
	
	private static final long serialVersionUID = 3965876507372809503L;

	public Class<?> getType(){
		return Row.class;
	}
	
	public void setType(Class<?> type){
		
	}
	
	public void writeXml(String fileName){
		
		SAXWriter writer=new SAXWriter(fileName);
		writer.startDocument();
		
		this.write(writer);
		
		writer.endDocument();
	}
	

	public List<Map<String, Object>> getValueMapList(){
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		
	    for(Row row : this.innerList){
	    	Map<String,Object> values= row.getValueMap();
	    	
	    	list.add(values);
	    }
	    
	    return list;
	}
}
