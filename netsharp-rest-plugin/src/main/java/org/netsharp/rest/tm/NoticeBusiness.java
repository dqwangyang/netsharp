//package org.netsharp.rest.tm;
//
//import java.io.IOException;
//import java.util.Date;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.type.TypeReference;
//import org.netsharp.util.DateManage;
//import org.netsharp.util.StopWatch;
//import org.netsharp.util.WebClient.HttpMethod;
//
//public class NoticeBusiness {
//	
//	private static final Log logger = LogFactory.getLog(NoticeBusiness.class.getName());
//	
//	private int pageSize=2000;
//	private int annNum = 1600;
//	private Integer intCls=1;
//	private Spider spider = new Spider();
//	
//	public void run() throws IOException, InterruptedException {
//		
//		this.pageSize = 100;//Integer.parseInt(Application.getConfig("com.gongsibao.tm.report.pageSize"));
//		
//		TmPersister.open();
//		
//		StopWatch watch = new StopWatch();
//		watch.start();
//		
//		TmPersister.clean(this.annNum);
//		
//		logger.info("//--------------------------------------");
//		logger.info("// start 批次:"+this.annNum);
//		logger.info("// "+DateManage.toLongString(new Date()));
//		logger.info("//--------------------------------------");
//		
//		for(int i=1;i<=45;i++) {
//			this.intCls=i;
//			this.spider_cls();
//		}
//		
//		watch.stop();
//		
//		logger.info("//--------------------------------------");
//		logger.info("// end 批次:"+this.annNum);
//		logger.info("// "+DateManage.toLongString(new Date()));
//		logger.info("// "+watch.getEclipsed()/60/1000+" m");
//		logger.info("//--------------------------------------");
//		
//		TmPersister.close();
//		
//	}
//
//	public void spider_cls() throws IOException, InterruptedException {
//		
//		logger.info("商标大类:"+this.intCls+" / "+this.annNum);
//		
//		NoticeParmater par = new NoticeParmater();
//		{
//			par.page = 1;
//			par.rows = 1;
//			par.intCls=this.intCls;
//			par.annNum=getAnnNum();
//		}
//
//		String url = "http://sbgg.saic.gov.cn:9080/tmann/annInfoView/annSearchDG.html" + "?" + par.toParameters();
//		
//		this.setRequestProperty(spider);
//		
//		NoticePaging notice = spider.downLoad(url, HttpMethod.POST, new TypeReference<NoticePaging>() {});
//		
//		int pageCount = notice.getTotal() / getPageSize();
//		pageCount ++;
//		
//		for(int pageIndex =1 ;pageIndex<=pageCount;pageIndex++) {
//			
//			logger.info(DateManage.toLongString(new Date()) + " : " + pageIndex + " / " +pageCount);
//			
//			this.spider_pageing(pageIndex);
//			
//			System.gc();
//		}
//
//	}
//	
//	public void spider_pageing(int pageIndex) throws IOException {
//		
//		NoticeParmater par = new NoticeParmater();
//		{
//			par.page = pageIndex;
//			par.rows = getPageSize();
//			par.intCls=this.intCls;
//			par.annNum=getAnnNum();
//		}
//
//		String url = "http://sbgg.saic.gov.cn:9080/tmann/annInfoView/annSearchDG.html" + "?" + par.toParameters();
//		
//		this.setRequestProperty(spider);
//		
//		NoticePaging notice = spider.downLoad(url, HttpMethod.POST, new TypeReference<NoticePaging>() {});
//		
////		List<NoticeItem> items = new ArrayList<NoticeItem>();
//		
//		for(NoticeItem item : notice.getRows()) {
//			
//			item.setIntCls(this.intCls.toString());
////			items.add(item);
//
//			TmPersister.save(item);
//		}
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
//		spider.setRequestProperty("Cookie", "__jsluid=b70c5b28a4e4940f556a284e0a801093; tmas_cookie=51947.7697.15402.0000; JSESSIONID=00004nziuz42XoPYDalC-bCSibR:1bm104rj3");
////		spider.setRequestProperty("Host", "sbgg.saic.gov.cn:9080");
//		spider.setRequestProperty("Pragma", "no-cache");
//		spider.setRequestProperty("Upgrade-Insecure-Requests", "1");
//		spider.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getAnnNum() {
//		return annNum;
//	}
//
//	public void setAnnNum(int annNum) {
//		this.annNum = annNum;
//	}
//
//}
