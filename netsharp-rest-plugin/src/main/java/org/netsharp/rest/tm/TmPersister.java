//package org.netsharp.rest.tm;
//
//import java.sql.Types;
//
//import org.netsharp.core.QueryParameters;
//import org.netsharp.core.id.SnowflakeId;
//import org.netsharp.dataccess.DataAccessFactory;
//import org.netsharp.dataccess.IDataAccess;
//
//public class TmPersister {
//	
//	private static IDataAccess dao = DataAccessFactory.create();
//	private static String sql = "INSERT INTO ged_tm_notice_item(uid,id,rn,page_no,tmname,ann_date,ann_type,ann_num,ann_type_code,reg_num,regname,int_cls) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
//	private static QueryParameters qps = new QueryParameters();
//	private static SnowflakeId id = new SnowflakeId();
//	
//	public static void open() {
//		dao.open();
//	}
//	
//	public static void initialize() {
//		
//		qps.add("@uid",null,Types.BIGINT);
//		qps.add("@id",null,Types.VARCHAR);
//		qps.add("@rn",null,Types.INTEGER);
//		qps.add("@page_no",null,Types.INTEGER);
//		qps.add("@tmname",null,Types.VARCHAR);
//		qps.add("@ann_date",null,Types.DATE);
//		qps.add("@ann_type",null,Types.VARCHAR);
//		qps.add("@ann_num",null,Types.VARCHAR);
//		qps.add("@ann_type_code",null,Types.VARCHAR);
//		qps.add("@reg_num",null,Types.BIGINT);
//		qps.add("@regname",null,Types.VARCHAR);
//		qps.add("@int_cls",null,Types.INTEGER);
//	}
//	
//	public static void save(NoticeItem item) {
//		
//		qps.get(0).setValue(id.newId());
//		qps.get(1).setValue(item.getId());
//		qps.get(2).setValue(item.getRn());
//		qps.get(3).setValue(item.getPage_no());
//	    qps.get(4).setValue(item.getTmname());
//	    qps.get(5).setValue(item.getAnn_date());
//	    qps.get(6).setValue(item.getAnn_type());
//	    qps.get(7).setValue(item.getAnn_num());
//	    qps.get(8).setValue(item.getAnn_type_code());
//	    qps.get(9).setValue(item.getReg_num());
//	    qps.get(10).setValue(item.getRegname());
//	    qps.get(11).setValue(item.getIntCls());
//		
//		dao.executeUpdate(sql, qps);
//	}
//	
//	public static void clean(Integer batch) {
//		
//		String cmdText = "delete from ged_tm_notice_item where ann_num=?";
//		
//		QueryParameters qps = new QueryParameters();
//		qps.add("@ann_num",batch,Types.INTEGER);
//		
//		dao.executeUpdate(cmdText, qps);
//	}
//	
//	public static void close() {
//		dao.close();
//	}
//	
//}
