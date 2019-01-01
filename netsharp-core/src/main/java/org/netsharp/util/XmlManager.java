package org.netsharp.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

public class XmlManager {
	private static final Log logger = LogFactory.getLog(XmlManager.class);

	//
	public static Document openXml(String fileName) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));

			return doc;
		} catch (Exception ex) {
			logger.error(ex);
		}

		return null;
	}

	public static Document openXml(InputStream stream) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(stream);

			return doc;
		} catch (Exception ex) {
			logger.error(ex);
		}

		return null;
	}

	public static Document parseXml(String xml) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setIgnoringElementContentWhitespace(true);
			factory.setIgnoringComments(true);
			factory.setValidating(false);

			DocumentBuilder db = factory.newDocumentBuilder();
			
			ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes(Encodings.UTF8));  
			Document doc = db.parse(stream);
			
			stream.close();
			
			

			return doc;
		} catch (Exception ex) {
			logger.error(ex);
		}

		return null;
	}

	public static Document createXml() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder db = factory.newDocumentBuilder();

			Document doc = db.newDocument();

			return doc;
		} catch (Exception ex) {
			logger.error(ex);
		}

		return null;
	}

	public static Document createXml(String rootName) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.newDocument();

			return doc;
		} catch (Exception ex) {
			logger.error(ex);
		}

		return null;
	}
}
