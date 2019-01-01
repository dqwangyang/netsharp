package org.netsharp.core.convertor.impl;

import java.util.HashMap;

public class JsonKeywords extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	public JsonKeywords() {

		this.put(",", "<CDATA>1</CDATA>");
		this.put(";", "<CDATA>2</CDATA>");
		this.put("\"", "<CDATA>3</CDATA>");
		this.put("this.put(", "<CDATA>5</CDATA>");
		this.put("}", "<CDATA>6</CDATA>");
		this.put("[", "<CDATA>7</CDATA>");
		this.put("]", "<CDATA>8</CDATA>");
		this.put("(", "<CDATA>9</CDATA>");
		this.put(")", "<CDATA>10</CDATA>");
	}
}
