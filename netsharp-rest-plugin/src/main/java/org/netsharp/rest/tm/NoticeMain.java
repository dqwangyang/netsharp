package org.netsharp.rest.tm;

import org.netsharp.core.NotImplementException;

public class NoticeMain {
	
//	private static final Log logger = LogFactory.getLog( NoticeMain.class.getName());

	public static void main(String[] args){
		
		throw new NotImplementException();
		
//		NoticeMain.setup();
//		
//		TmPersister.initialize();
//		
//		Integer start = Integer.valueOf( Application.getConfig("com.gongsibao.tm.report.start") );
//		Integer end = Integer.valueOf( Application.getConfig("com.gongsibao.tm.report.end") );
//		
//		for(int i=start ;i<=end;i++) {
//			
//			NoticeBusiness biz = new NoticeBusiness();
//			{
//				biz.setAnnNum(i);
//			}
//			try {
//				biz.run();
//			} catch (Exception e) {
//				logger.error("//--------------------------------------");
//				logger.error("// 异常退出",e);
//				logger.error("//--------------------------------------");
//				break;
//			} 
//		}
	}
	
	public static void setup() {
		
//		IDb db = DbFactory.create();
//		if(!db.isTableExsist(MtableManager.getMtable(NoticeItem.class).getTableName())) {
//			db.createTable(MtableManager.getMtable(NoticeItem.class));
//		}
	}

}
