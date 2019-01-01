//package org.netsharp.rest;
//
//import java.io.IOException;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//
//public class JsoupTest {
//
//	@Test
//	public void tttttt() throws IOException {
//		
//		Document document = Jsoup.connect("http://www.cnblogs.com/netsharp").get();
//		System.out.println(document.title());
//		
//		Elements links = document.select("a[href]");  
//	    for (Element link : links) 
//	    {
//	         System.out.println("link : " + link.attr("href"));  
//	         System.out.println("text : " + link.text());  
//	    }
//	}
//}
