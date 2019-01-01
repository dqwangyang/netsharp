package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.appconfig.Appconfig;
import org.netsharp.appconfig.IAppconfigService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.base.ITraceNoDailyService;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.entity.TraceNoDaily;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class TraceNoDailyService extends PersistableService<TraceNoDaily> implements ITraceNoDailyService {
	
	private static String appconfig_itid="scrum.organization.it.id";
	private static String appconfig_iidnotins="scrum.organization.it.idnotins";
	
	public TraceNoDailyService() {
		this.type = TraceNoDaily.class;
	}
	
	public void generate(int year,int month) {
		String theday = year +"-" + StringManager.padLeft(String.valueOf(month), 2, '0')  +"-01"  ;
		
		Date date = DateManage.parse(theday);
		while(DateManage.getMonth(date)==month && date.before(new Date())) {
			
			this.generate(year, month,DateManage.getDay(date));
			
			date = DateManage.addDays(date, 1);
		}
	}
	
	public void generateToday() {
		Date date = new Date();
		int year = DateManage.getYear(date);
		int month = DateManage.getMonth(date);
		int day = DateManage.getDay(date);
		
		this.generate(year, month,day);
	}
	
	public void generateYestoday() {
		Date date = new Date();
		date = DateManage.addDays(date, -1);
		
		int year = DateManage.getYear(date);
		int month = DateManage.getMonth(date);
		int day = DateManage.getDay(date);
		
		this.generate(year, month,day);
	}
	
	public void generate(int year,int month,int day) {
				
		String theday = year +"-" + StringManager.padLeft(String.valueOf(month), 2, '0')  +"-" + StringManager.padLeft(String.valueOf(day), 2, '0') ;
		
		String tableName = MtableManager.getMtable(this.type).getTableName();
		pm.executeNonQuery("delete from "+tableName+" where day = '"+theday+"'", null);
		
		int count = pm.executeInt("select count(distinct(creator_id)) from  "+ MtableManager.getMtable(StoryTrace.class).getTableName() + " where DATE_FORMAT(create_time,'%Y-%m-%d')='"+ theday +"'", null);
		if(count<5) {
			//少于５个人录入跟进则不统计了
			return;
		}
		
		IAppconfigService appconfigService = ServiceFactory.create(IAppconfigService.class);
		Appconfig itid = appconfigService.byCode(appconfig_itid);
		if(itid == null) {
			throw new NetsharpException("每日未跟进统计需要配置选项:"+appconfig_itid);
		}
		
		Appconfig noids = appconfigService.byCode(appconfig_iidnotins);
		
		String sql = "select path_code from "+MtableManager.getMtable(Organization.class).getTableName()+" where id = " + itid.getValue();
		String pathCode= (String)this.pm.executeScalar(sql, null);
		
		sql = "select distinct e.id,e.name,o.parent_id from sys_permission_organization_employee oe\n" + 
				"left join sys_permission_organization o on oe.organization_id = o.id\n" + 
				"left join sys_permission_employee e on oe.employee_id=e.id\n" + 
				"where o.path_code like '"+pathCode+"%' \n" + 
				"and e.id is not null and e.disabled=0\n" + 
				"and e.id not in (select creator_id from scrum_story_trace where DATE_FORMAT(create_time,'%Y-%m-%d')= '"+theday+"')\n";

		if(noids!=null && !StringManager.isNullOrEmpty(noids.getValue())) {
			sql += "and e.id not in( " + noids.getValue() + " )";
		}
		
		System.out.println(sql);
		
		DataTable table = this.pm.executeTable(sql, null);
		
		for(IRow row : table) {
			
			TraceNoDaily daily = new TraceNoDaily();
			{
				daily.toNew();
				daily.setTracorId(row.getInteger("id"));
				daily.setOrganizationId(row.getInteger("parent_id"));
				daily.setDate(DateManage.parse(theday));
				daily.setDay(theday);
			}
			
			pm.save(daily);
			
		}
		
		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		
		List<String> names = new ArrayList<String>();
		{
			names.add( theday + "未录入跟进人员"+table.size()+"人:" );
		}
		
		int index =1;
		for(IRow row : table) {
			Integer id = row.getInteger("id");
			eMessageService.send2("SCRUM", "提醒:你昨天没有录入任务"+theday+"的跟进！", id.toString());
			
			names.add(index + "." +row.getString("name"));
			index ++ ;
		}
		
		eMessageService.send("SCRUM", StringManager.join(StringManager.NewLine,names), null);
		
	}
}
