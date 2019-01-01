//package org.netsharp.rest.tm;
//
//import java.io.IOException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.netsharp.core.MtableManager;
//import org.netsharp.db.DbFactory;
//import org.netsharp.db.IDb;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//import org.netsharp.util.WebClient.HttpMethod;
//
//public class TMTest {
//
//	IPersister<NoticeItem> pm = PersisterFactory.create();
//
//	@Before
//	public void setup() {
//		IDb db = DbFactory.create();
//		db.reCreateTable(MtableManager.getMtable(NoticeItem.class));
//	}
//
//	@Test
//	public void mytest() throws IOException  {
//
//		String url = "http://wsjs.saic.gov.cn/txnDetail.do?y7bRbp=qmMUZajkSdUbP16P5ZFdWospQB77fY1eYWdh6.jGzD__G0WxNTz2XPxme9_O4TICKoq2tkR7roR5Rry4wfJCdAYcItZ90GheQPh8vgEoJMIjfS6XXhIDh73j7VYKPqg2WKWjASiPAdg3uQr27kgE3RiEAcx&c1K5tw0w6_=1VyED7mK75WA2rlfmaGKaFWffZRUU0wPLtJGOomiLtgG16YC3KOVw9B8mSZgqmqFnpsjgCnP7xY0DHkoaB.NWYZNle4WjxQ5LVYBiTr4B5yy6gHQACV.o08qut57DSNezyRDmujYsa48SJxaiatcoV2G68uOivdHokOq5A4WHoPXT.2IFLZCPFqnUBDrrxLRdL1a6zlntCCJGZArfVNHHvywVI1w3wiHzdZqnvfFP5J3";
//
//		Spider spider = new Spider();
//		{
//			this.setRequestProperty(spider);
//		}
//		
//		String notice = spider.downLoadString(url, HttpMethod.GET);
//		System.out.println(notice);
//		
////		try {
////			String notice = spider.downLoadString(url, HttpMethod.GET);
////			System.out.println(notice);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//	}
//
//	private void setRequestProperty(Spider spider) {
//
//		spider.setRequestProperty("contentType", "application/x-www-form-urlencoded");
//		spider.setRequestProperty("charset", "utf-8");
//
//		spider.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//		spider.setRequestProperty("Accept-Encoding", "gzip, deflate");
//		spider.setRequestProperty("Accept-Language", "en-US,en;q=0.9,es;q=0.8,fr;q=0.7");
//		spider.setRequestProperty("Cache-Control", "no-cache");
//		spider.setRequestProperty("Connection", "keep-alive");
//		spider.setRequestProperty("Cookie","FSSBBIl1UgzbN7N80S=B9PATNP4X8lgFwf4J_jZshamd0Ub1FoGUpUvKzsHOQrNtPIzXYj_aO9O_azGITOx; __jsluid=d63582c767cdaa6aebc83aa7b61085d9; tmrpToken=7685FBCC826C1E7601329E0A56C4F4BC; JSESSIONID=E4791C5730297714571F81D132547294; FSSBBIl1UgzbN7N80T=1dHzFJDmn7Y.3MiUSWVGBFbbza4.4LR9kLNCQ9fjb8lcLOKoqV3fDnQAg04JZeGjgt138E2BsYP64fs6fz6lJg6DOR3n18QoYpnz7FyaaJPd873_QrMjpQy_LX6mJblta56xB_3F39yLlMOCJKgPrKI9L9cDJooukTU041a7YiGOwvmPRzwfVMqBZHhYA2W55Z62P.TtXNH3KWrNR8Lz0DxfTBTA_85I_zbk1HZLAabv_gmzDh6uaHu3uDtm5KTqIaDD.bdMUhUHuzRn1YkYJSyz0wy2zugmWbfW5W7TqeMJkjknz..j386AZEFQXtQus_1a");
//		spider.setRequestProperty("Host", "wsjs.saic.gov.cn");
//		spider.setRequestProperty("Pragma", "no-cache");
//		spider.setRequestProperty("Upgrade-Insecure-Requests", "1");
//		spider.setRequestProperty("Referer", "http://wsjs.saic.gov.cn/txnDetail.do?request:tid=TID200611343B4CF36D94FB6088CA2FC820C0CF61F0E07&request:index=1&y7bRbP=Kam1ris9yis9yis9uFtg0kZ6ufSXCe7qFwNpYwl8m8A");
//		spider.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
//	}
//
//}
