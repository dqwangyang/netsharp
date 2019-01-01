package org.netsharp.core;

import java.util.ArrayList;

import org.netsharp.util.SAXWriter;
import org.netsharp.util.StringManager;

public class Set {

	protected ArrayList<ITable<?>> tables = new ArrayList<ITable<?>>();
	private String main;

	public ITable<?> getTable(Class<?> type) {

		for (ITable<?> table : this.getTables()) {

			if (table.getType() == type) {
				return table;
			}
		}

		return null;
	}

	public ITable<?> getTable(String entityId) {
		for (ITable<?> table : this.getTables()) {

			if (StringManager.equals(table.getEntityId() ,entityId)) {
				return table;
			}
		}

		return null;
	}

	public ArrayList<ITable<?>> getTables() {
		return tables;
	}

	public void add(ITable<?> table) {
		
		if(table != null){
			this.tables.add(table);
			table.setSet(this);
		}
	}
	

	public ITable<?> main() {
		
		for (ITable<?> table : this.tables) {
			if (table.getEntityId().equals(this.main)) {
				return table;
			}
		}

		return null;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}
	
	public IRow firstItem(){
		ITable<?> table=this.main();
		
		if(table.size()==0){
			return null;
		}
		else{
			return table.get(0);
		}
	}
	
	public void writeXml(String fileName){
		
		SAXWriter writer=new SAXWriter(fileName);
		writer.startDocument();
		
		writer.startElement("set");
		
		for(ITable<?> table : this.getTables()){
			table.write(writer);
		}
		
		writer.endElement("set");
		
		writer.endDocument();
	}
}
