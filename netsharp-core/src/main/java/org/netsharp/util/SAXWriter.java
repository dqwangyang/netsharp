package org.netsharp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SAXWriter {
	
	private Writer writer = null;
	private StringBuffer buffer = new StringBuffer();
	private boolean isRoot = true;
	private boolean closed = false;
	
	public SAXWriter() {
		
	}

	public SAXWriter(String filename) {
		try {
			writer = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startDocument()
    {  
		 buffer.append("<?xml version=\"1.0\"?>");
    	 buffer.append(StringManager.NewLine);
    }

	public void endDocument() {
		
		if(writer == null){
			return;
		}
		
		try {
			if (buffer.length() > 0){
				writer.write(buffer.toString());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startElement(String name) {
		if (isRoot)
			isRoot = false;
		else {
			if (!closed){
				buffer.append(">");
				buffer.append(StringManager.NewLine);
			}
		}

		this.flush();
		
		buffer.append("<" + name);
		closed = false;
	}

	public void endElement(String name) {
		if (!closed){
			buffer.append(">");
		}
			
		buffer.append("</" + name + ">");
		buffer.append(StringManager.NewLine);
		closed = true;
	}

	public void addAttribute(String name, String value)
    {  
         buffer.append( " " + name + "=\"" + value + "\"" );
    }

	public void character(String value) {
		
		if (!closed){
			buffer.append(">");
		}
			
		buffer.append(value);
		closed = true;
	}
	
	public void writeCData(String value){
		if (!closed){
			buffer.append(">");
		}
		
		buffer.append("<![CDATA[");
		buffer.append(value);
		buffer.append("]]>");
		
		closed = true;
	}
	
	private void flush(){
		
		if(writer == null){
			return;
		}
		
		if (buffer.length() > 4096) {
			try {
				writer.write(buffer.toString());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			buffer.delete(0, buffer.length());
		}
	}
	
	@Override
	public String toString(){
		String xml = this.buffer.toString();
		return xml;
	}
}
